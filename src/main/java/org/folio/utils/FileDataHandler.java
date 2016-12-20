package org.folio.utils;

import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.StringEscapeUtils;
import org.folio.rest.impl.ProcessUploads;
import org.folio.rest.jaxrs.model.Item;
import org.folio.rest.jaxrs.model.ItemStatus;
import org.folio.rest.jaxrs.model.Location;
import org.folio.rest.jaxrs.model.MaterialType;
import org.folio.rest.jaxrs.model.ShelfLocation;
import org.folio.rest.persist.MongoCRUD;
import org.folio.rest.dao.ConfigObj;
import org.folio.rulez.Rules;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;


public class FileDataHandler implements io.vertx.core.Handler<Buffer> {

  private static final Logger log = LoggerFactory.getLogger(ProcessUploads.class);
  private static final String LINE_SEPS = System.getProperty("line.separator");

  private static KieSession droolsSession  = null;

  private Vertx vertx = null;
  private int []successCount = new int[]{0};
  private int []errorCount = new int[]{0};

  private String lastRowFromPreviousBuffer = null;

  private long fileSize = 0;
  private long bytesRead = 0;

  private ConfigObj conf;

  /**
   * 0 - more bytes to read, 1 - reading last buffer in file, 2 - read last row in last buffer
   */
  private int status = 0;
  static {
    try {
      droolsSession = new Rules().buildSession();
    } catch (Exception e) {
      log.error("Unable to create Drools session - no validations will be used during import " , e);
    }
  }

  public FileDataHandler(Vertx vertx, ConfigObj conf, long fileSize){
    this.vertx = vertx;
    this.fileSize = fileSize;
    this.conf = conf;
  }

  @Override
  public void handle(Buffer event) {

    //keep track of bytes read to know if we are finished reading
    bytesRead = bytesRead + event.getBytes().length;
    if(fileSize <= bytesRead){
      status = 1;
    }

    //split buffer read into new line delimited rows - keeping the delimiters
    //so that we can keep track if we read a complete row or if it was cut off
    String []rows = event.toString("UTF8").split("(?<="+LINE_SEPS+")");

    //iterate over the read rows
    for (int i = 0; i < rows.length; i++) {
      if(lastRowFromPreviousBuffer != null && i==0){
        //previous buffer had a partial row - add its content to the first row of the
        //next buffer
        rows[i] = lastRowFromPreviousBuffer + rows[i];
        lastRowFromPreviousBuffer = null;
      }
      if(i == rows.length-1){

        if(status == 1){
          //we are in the last buffer and in the last line of that buffer -
          //once this completes we can update status in DB of job
          status = 2;
        }

        //the last row of a buffer may not be complete - if it does not end with a new line
        //it has partial content
        if(!rows[i].endsWith(LINE_SEPS)){
          //we have a partial row in hand in buffer
          lastRowFromPreviousBuffer = rows[i];
        }
      }

      String []cols = rows[i].split("\t+");
      Item item = new Item();
      boolean isValid = true;
        //assume 8 columns mandatory per line
        if(cols.length == 8){
          for (int j = 0; j < cols.length; j++) {
            //assume 8 columns mandatory per line
            MaterialType mt = new MaterialType();
            mt.setValue(cols[1]);
            item.setMaterialType(mt);
            item.setBarcode(cols[0]);
            item.setLibraryId(cols[2]);
            Location loc = new Location();
            loc.setValue(cols[3]);
            item.setLocation(loc);
            ShelfLocation sl = new ShelfLocation();
            sl.setClassificationNumber(cols[4]);
            item.setShelfLocation(sl);
            ItemStatus  is = new ItemStatus();
            is.setValue(cols[5]);
            item.setItemStatus(is);
            item.setTitle(cols[6]);
          }
          try{
            if(droolsSession != null){
            // add object to validate to session
            FactHandle handle = droolsSession.insert(item);
            // run all rules in session on object
            droolsSession.fireAllRules();
            // remove the object from the session
            droolsSession.delete(handle);
            }
          }
          catch (Exception e) {
            errorCount[0]++;
            isValid = false;
            log.error("Import validation error while persisting item with barcode " + " - " + e.getMessage());
          }
        }
        else{
          errorCount[0]++;
          isValid = false;
          //this should never happen unless body is multi part with boundaries
          log.warn(">>row contains incorrect amount of columns - expected 6 and got " + cols.length);
          if(status == 2){
            updateStatus(conf);
          }
          continue;
        }
        if(isValid){
          MongoCRUD.getInstance(vertx).save(Consts.ITEM_COLLECTION, item, reply -> {
            if(reply.failed()){
              errorCount[0]++;
              log.error("Error saving item with barcode " + item.getBarcode() + ", error is " + reply.cause().getMessage());
            }
            else{
              successCount[0]++;
              log.debug("#" +successCount[0]+ " Saved item with barcode " + item.getBarcode());
            }
            if(status == 2){
              updateStatus(conf);
            }
          });
        }
      }
  }

  private void updateStatus(ConfigObj conf){
    //set this job to completed in DB
    String query = "{ \"code\": \""+StringEscapeUtils.escapeJava(conf.getCode())+"\"}";

    conf.setValue(Consts.STATUS_COMPLETED);
    conf.setSucceeded(successCount[0]);
    conf.setErrors(errorCount[0]);
    conf.setUpdateDate(new Date().getTime()+"");
    MongoCRUD.getInstance(vertx).update(Consts.CIRCULATION_CONFIG_COLLECTION, conf, new JsonObject(query), rep -> {

    });
  }
}
