package org.folio.rest.impl;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import org.folio.rest.jaxrs.model.Item;
import org.folio.rest.jaxrs.model.ItemStatus;
import org.folio.rest.jaxrs.model.Location;
import org.folio.rest.jaxrs.model.MaterialType;
import org.folio.rest.jaxrs.model.ShelfLocation;
import org.folio.rest.resource.interfaces.Importer;
import org.folio.rulez.Rules;
import org.folio.utils.Consts;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;


public class ImportItems implements Importer {

  private static final Logger log = LoggerFactory.getLogger(ImportItems.class);
  private static KieSession droolsSession  = null;

  static {
    try {
      droolsSession = new Rules().buildSession();
    } catch (Exception e) {
      log.error("Unable to create Drools session - no validations will be used during import " , e);
    }
  }

  @Override
  public String getLineDelimiter() {
    return Importer.LINE_SEPERATOR;
  }


  @Override
  public String[] getFailOnExists() {
    return null;
  }

/*  @Override
  public int getBulkSize() {
    return 100;
  }

  @Override
  public double getFailPercent() {
    return 3.00;
  }*/

  @Override
  public String getImportAddress() {
    return "uploads.import.items";
  }

  @Override
  public Object processLine(String lineFromFile) {
    String []cols = lineFromFile.split("\t+");
    Item item = new Item();
      //assume 8 columns mandatory per line
      if(cols.length == 7){
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
          log.debug("processed item with barcode " + item.getBarcode());
          return item;
        }
        catch (Exception e) {
          log.error("Import validation error while persisting item with barcode " + " - " + e.getMessage());
          return null;
        }
      }
      else{
        //this should never happen unless body is multi part with boundaries
        log.warn(">>row contains incorrect amount of columns - expected 6 and got " + cols.length);
        return null;
      }
  }

  @Override
  public String getCollection() {
    return Consts.ITEM_COLLECTION;
  }
}
