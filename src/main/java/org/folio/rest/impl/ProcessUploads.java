package org.folio.rest.impl;

import java.util.Arrays;
import java.util.stream.Stream;


import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.parsetools.RecordParser;

import org.folio.rest.jaxrs.model.Item;
import org.folio.rest.jaxrs.model.ItemStatus;
import org.folio.rest.jaxrs.model.Location;
import org.folio.rest.jaxrs.model.MaterialType;
import org.folio.rest.jaxrs.model.ShelfLocation;
import org.folio.rest.persist.MongoCRUD;
import org.folio.rest.resource.interfaces.InitAPI;
import org.folio.utils.Consts;


public class ProcessUploads implements InitAPI {

  public static final String GENERAL_UPLOAD_ADDR = "circ.uploaded.files";
  public static final String IMPORT_ITEMS_ADDR   = "circ.uploads.items.imports";
  private static final String LINE_SEPS          = System.getProperty("line.separator");

  private static final Logger log = LoggerFactory.getLogger(ProcessUploads.class);


  @Override
  public void init(Vertx vertx, Context context, Handler<AsyncResult<Boolean>> handler) {
    /**
     * register event listeners triggered by the upload process
     */
    try{
      MessageConsumer<Object> consumer1 = vertx.eventBus().consumer(GENERAL_UPLOAD_ADDR);
      consumer1.handler(message -> {
        System.out.println("I have received a message to "+GENERAL_UPLOAD_ADDR+": " + message.body());
        //the upload api expects a reply - so send one
        message.reply("how interesting!");
        readFile(vertx, String.valueOf(message.body()));
      });
      MessageConsumer<Object> consumer2 = vertx.eventBus().consumer(IMPORT_ITEMS_ADDR);
      consumer2.handler(message -> {
        System.out.println("I have received a message to "+IMPORT_ITEMS_ADDR+": " + message.body());
        //the upload api expects a reply - so send one
        message.reply("how interesting!");
        readFile(vertx, String.valueOf(message.body()));
      });
      handler.handle(io.vertx.core.Future.succeededFuture(true));
    }
    catch(Exception e){
      handler.handle(io.vertx.core.Future.failedFuture(e.getMessage()));
    }
  }

  /**
   * read a tab delimited file containing 6 columns representing a basic item
   * and push them into mongo
   * reading the file is async
   */
  private void readFile(Vertx vertx, String file){
    RecordParser parser = RecordParser.newDelimited(LINE_SEPS,  buf -> {
      String []cols = buf.toString("UTF8").split("\t");
        //assume 6 columns mandatory per line
        if(cols.length == 6){
          Item i = new Item();
          MaterialType mt = new MaterialType();
          mt.setValue(cols[0]);
          i.setMaterialType(mt);
          i.setBarcode(cols[1]);
          i.setLibraryId(cols[2]);
          Location loc = new Location();
          loc.setValue(cols[3]);
          i.setLocation(loc);
          ShelfLocation sl = new ShelfLocation();
          sl.setClassificationNumber(cols[4]);
          i.setShelfLocation(sl);
          ItemStatus  is = new ItemStatus();
          is.setValue(cols[5]);
          i.setItemStatus(is);
          MongoCRUD.getInstance(vertx).save(Consts.ITEM_COLLECTION, i, reply -> {
            if(reply.failed()){
              log.error("Error saving item with barcode " + i.getBarcode() + ", error is " + reply.cause().getMessage());
            }
            else{
              log.debug("Saved item with barcode " + i.getBarcode());
            }
          });
        }
        else if(cols.length > 6){
          log.warn(">>>>>>>>>>>>>>row contains incorrect amount of columns - expected 6 and got " + cols.length);
        }
        else{
          //this should never happen
          log.warn("<<<<<<<<<<<<<<row contains incorrect amount of columns - expected 6 and got " + cols.length);
        }
    });
    vertx.fileSystem().open(file, new OpenOptions(), ar -> {
      if (ar.succeeded()) {
        AsyncFile rs = ar.result();
        rs.handler(parser);
        rs.exceptionHandler(t -> {
          log.error("Error reading from file " + file);
          }
        );
        rs.endHandler(v -> {
          ar.result().close(ar2 -> {
            if (ar2.failed()) {
              log.error("Error closing file " + file);
            }
          });
        });
      } else {
        log.error("Error opening file " + file);
      }
    });
  }
}
