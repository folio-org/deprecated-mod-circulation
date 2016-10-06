package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringEscapeUtils;
import org.folio.rest.persist.MongoCRUD;
import org.folio.rest.resource.interfaces.InitAPI;
import org.folio.rest.dao.ConfigObj;
import org.folio.rest.tools.messages.MessageConsts;
import org.folio.rest.tools.messages.Messages;
import org.folio.utils.Consts;
import org.folio.utils.FileDataHandler;
import org.folio.rest.tools.ReturnStatusConsts;

public class ProcessUploads implements InitAPI {

  public static final String  GENERAL_UPLOAD_ADDR  = "circ.uploaded.files";
  public static final String  IMPORT_ITEMS_ADDR    = "circ.uploads.items.imports";

  private static final String LOG_LANG             = "en";

  private int                 concurrentImports    = 2;

  private final Messages      messages             = Messages.getInstance();

  private static final Logger log                  = LoggerFactory.getLogger(ProcessUploads.class);

  private Vertx               vertx;

  @Override
  public void init(Vertx vertx, Context context, Handler<AsyncResult<Boolean>> handler) {
    /**
     * register event listeners triggered by the upload process
     * 
     * check if there are processes stuck in running at startup by checking name=import_items and 
     * field=status and value=running if so check
     * if the files are still on disk - if so run them in incremental mode - delete then add
     */
    this.vertx = vertx;

    try {
      MessageConsumer<Object> consumer1 = vertx.eventBus().consumer(GENERAL_UPLOAD_ADDR);
      consumer1.handler(message -> {
        //nothing implemented here yet!
        log.debug("Received a message to " + GENERAL_UPLOAD_ADDR + ": " + message.body());
        // the upload api expects a reply
        message.reply(ReturnStatusConsts.OK_PROCESSING_STATUS);
      });
      MessageConsumer<Object> consumer2 = vertx.eventBus().consumer(IMPORT_ITEMS_ADDR);
      consumer2.handler(message -> {
        log.debug("Received a message to " + IMPORT_ITEMS_ADDR + ": " + message.body());
        // add to db an entry of the file to import in a pending state
        save2DB(String.valueOf(message.body()), message);
      });
      // set periodic to query db for pending states and run them if there is an open slot
      // this is a terrible hack and should be in the periodicAPI hook TODO
      vertx.setPeriodic(60000, todo -> {
        try {
          //kick off the running of the import file process
          process();
        } catch (Exception e) {
          log.error(e);
        }
      });
      handler.handle(io.vertx.core.Future.succeededFuture(true));
    } catch (Exception e) {
      log.error(e);
      handler.handle(io.vertx.core.Future.failedFuture(e.getMessage()));
    }
  }

  /**
   * Save an entry of the path to the uploaded file to mongo in pending state
   * @param file - file path
   * @param message - message to return to the runtime environment with status of the db save
   */
  private void save2DB(String file, Message<Object> message) {
    final ConfigObj fileConf = createConfObj(file);

    MongoCRUD.getInstance(vertx).save(Consts.CIRCULATION_CONFIG_COLLECTION, fileConf, reply2 -> {
      if (reply2.failed()) {
        log.error("Unable to save uploaded file to queue, it will not be run, " + file);
        message.reply(ReturnStatusConsts.ERROR_PROCESSING_STATUS);
      }
      else{
        message.reply(ReturnStatusConsts.OK_PROCESSING_STATUS);
      }
    });
  }
  
  /**
   * update the conf object in mongo with the object passed in using the code as the key
   * @param conf
   */
  private void updateStatusDB(ConfigObj conf) {

    String query = "{\"code\":\""+conf.getCode()+"\"}";    
    MongoCRUD.getInstance(vertx).update(Consts.CIRCULATION_CONFIG_COLLECTION, conf, new JsonObject(query), reply2 -> {
      if (reply2.failed()) {
        log.error("Unable to save uploaded file to queue, it will not be run, " + conf.getCode());
      }
    });
  }

  /**
   * check how many import processes are active - if less then threshold - then go to the mongo queue and
   * pull pending jobs and run them.
   * read a tab delimited file containing 6 columns representing a basic item and push them into mongo 
   * reading the file is async if this is * uploaded from a form and contains boundaries - then those 
   * rows should be filtered out by the cols.length==6 - if not for some reason -
   * the validation on the item will filter them out
   */
  private void process() throws Exception {

    long start = System.nanoTime();

    String pendingOrRunningEntries = "{\"$and\": [ { \"module\": \"CIRCULATION\"}, { \"name\": \""
        + IMPORT_ITEMS_ADDR + "\"}, "
        + "{\"$or\" : [{ \"value\": \"PENDING\"},{ \"value\": \"RUNNING\"}]} ]}";

    JsonObject j = new JsonObject(pendingOrRunningEntries);

    //get running and pending jobs
    MongoCRUD.getInstance(vertx).get(
      MongoCRUD.buildJson(ConfigObj.class.getName(), Consts.CIRCULATION_CONFIG_COLLECTION, j,
        "code", "asc"), reply -> {
        if (reply.succeeded()) {
          int runningCounter = 0;
          List<ConfigObj> runCandidates = new ArrayList<>();
          List<ConfigObj> conf = (List<ConfigObj>) reply.result();
          //check how many jobs in running and collect pending jobs to run in case
          //there is a slot open
          for (int i = 0; i < conf.size(); i++) {
            if (Consts.STATUS_RUNNING.equals(conf.get(i).getValue())) {
              runningCounter++;
            } 
            else {
               // pending state - it is a run candidate - note the asc sort so we deal with earlier uploads before later ones
               runCandidates.add(conf.get(i));
            }
          }
          
          if(runCandidates.isEmpty()){
            return;
          }
          // for every available slot set status to running and start handling
          for (int i = 0; i < Math.min(concurrentImports-runningCounter,runCandidates.size()); i++) {
            ConfigObj torun = runCandidates.get(i);
            if (torun != null) {
              torun.setValue(Consts.STATUS_RUNNING);
              torun.setUpdateDate(new Date().getTime()+"");
              // save                            
              MongoCRUD.getInstance(vertx).update(
                Consts.CIRCULATION_CONFIG_COLLECTION,
                torun, new JsonObject("{\"code\":\""+StringEscapeUtils.escapeJava(torun.getCode())+"\"}"),
                reply2 -> {
                  if (reply2.failed()) {
                    log.error("Unable to save uploaded file to queue, it will not be run, "
                        + torun.getCode());
                  } else {
                    vertx.fileSystem().props(torun.getCode(),
                      reply3 -> {
                        if (reply3.result() != null) {
                          long fileSize = reply3.result().size();
                          parseFile(fileSize, torun);
                        } else {
                          log.error("Unable to get properties of uploaded file, it will not be run, "
                              + torun.getCode());
                        }
                      });
                  }
                });
            }
          }
    } else {
      log.error("Unable to get uploaded file queue, nothing will not be run, ", reply.cause());
    }
    long end = System.nanoTime();
    log.debug(messages.getMessage(LOG_LANG, MessageConsts.Timer, "Reading configs for import process",
      end - start));
    });
    
  }

  private ConfigObj createConfObj(String file) {
    ConfigObj cObj = new ConfigObj();
    cObj.setCode(file);
    cObj.setValue(Consts.STATUS_PENDING);
    cObj.setModule(Consts.CIRCULATION_MODULE);
    cObj.setName(IMPORT_ITEMS_ADDR);
    cObj.setDescription("waiting to run");
    cObj.setUpdateDate(new Date().getTime() + "");
    return cObj;
  }

  private void parseFile(long fileSize, ConfigObj conf) {
    String file = conf.getCode();
    vertx.fileSystem().open(file, new OpenOptions(), ar -> {
      if (ar.succeeded()) {
        AsyncFile rs = ar.result();
        rs.handler(new FileDataHandler(vertx, conf, fileSize));
        rs.exceptionHandler(t -> {
          log.error("Error reading from file " + file, t);
          conf.setValue(Consts.STATUS_ERROR);
          updateStatusDB(conf);
        });
        rs.endHandler(v -> {
          rs.close(ar2 -> {
            if (ar2.failed()) {
              log.error("Error closing file " + file, ar2.cause());
            }
          });
          /*
           * log.info(messages.getMessage(LOG_LANG, MessageConsts.Timer, "reading from file " + file + ", imported record count: " +
           * successCount[0], ((double) elapsedTime / 1000000000.0) + " seconds. Error count: " + errorCount[0]));
           */
        });
      } else {
        log.error("Error opening file " + file, ar.cause());
        conf.setValue(Consts.STATUS_ERROR);
        updateStatusDB(conf);
      }
    });
  }

  private void readFromConfigService() {
    /*
     * new ConfigFacade().readConfigs(vertx, "import_items", res -> { List<ConfigObj> conf = res.result(); if(conf == null){ //unable to
     * read configurations - fail job log.error("Unable to read configurations for import job while processing file: " + file +
     * ", skipping..."); return; } List<ConfigObj.Row> rows = conf.get(0).getRows(); int confEntries = rows.size(); if(confEntries < 2){
     * 
     * } for (int i = 0; i < ; i++) {
     * 
     * } });
     */
  }

}
