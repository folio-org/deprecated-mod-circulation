package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.folio.rest.annotations.Validate;
import org.folio.rest.jaxrs.model.Fine;
import org.folio.rest.jaxrs.model.Fines;
import org.folio.rest.jaxrs.model.ItemRequest;
import org.folio.rest.jaxrs.model.ItemRequests;
import org.folio.rest.jaxrs.model.Loan;
import org.folio.rest.jaxrs.model.Loans;
import org.folio.rest.jaxrs.model.Patron;
import org.folio.rest.jaxrs.model.Patrons;
import org.folio.rest.jaxrs.resource.PatronsResource;
import org.folio.rest.persist.MongoCRUD;
import org.folio.rest.tools.messages.MessageConsts;
import org.folio.rest.tools.messages.Messages;
import org.folio.rest.tools.utils.OutStream;
import org.folio.utils.CircMessageConsts;
import org.folio.utils.Consts;
import static org.folio.rest.RestVerticle.OKAPI_HEADER_TENANT;

@Path("patrons")
public class PatronAPI implements PatronsResource {

  private static final Logger log = LoggerFactory.getLogger(PatronAPI.class);

  private static final String PATRON_ID_FIELD = "patron_id";
  private static final String ID_FIELD        = "_id";
  private final Messages messages             = Messages.getInstance();

  // @TODO if revert back to hibernate ogm - uncomment this! two examples of
  // usasge in two of the functions below
  // private final static EntityManagerFactory entityManagerFactory =
  // Persistence.createEntityManagerFactory( "mongoStore" );

  @Validate
  @Override
  public void getPatrons(String query, String orderBy, Order order, int offset, int limit, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);
    log.debug("sending... getPatrons, tenant id = " + tenantId);
    context.runOnContext(v -> {
      try {
        MongoCRUD.getInstance(vertx, tenantId).get(
          MongoCRUD.buildJson(Patron.class.getName(),Consts.PATRONS_COLLECTION, query, orderBy, order, offset, limit),
          reply -> {
            try {
              Patrons ps = new Patrons();
              // this is wasteful!!!
              List<Patron> patrons = (List<Patron>)reply.result();
              ps.setPatrons(patrons);
              ps.setTotalRecords(patrons.size());
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK(ps)));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse
                .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
            }
          });
      } catch (Exception e) {
        log.error(e);
        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse
          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
      }
    });
  }

  @Validate
  @Override
  public void postPatrons(String lang, Patron entity, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler,
      Context context) throws Exception {

    try {
      log.debug("sending... postPatrons");
      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);
      context.runOnContext(v -> {
        try {
          MongoCRUD.getInstance(vertx, tenantId)
          .save(Consts.PATRONS_COLLECTION, entity,
            reply -> {
              try {
                Patron p = new Patron();
                p = entity;
                p.setId(reply.result());
                OutStream stream = new OutStream();
                stream.setData(p);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsResponse
                  .withJsonCreated(reply.result(),stream)));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsResponse
                  .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
              }
            });
        } catch (Exception e) {
          log.error(e);
          asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsResponse.withPlainInternalServerError(messages
            .getMessage(lang,  MessageConsts.InternalServerError))));
        }
        /*
         * EntityManager entityManager = null; String id = null; try {
         * entityManager = entityManagerFactory.createEntityManager();
         * entityManager.getTransaction().begin();
         * entityManager.persist(entity);
         * entityManager.getTransaction().commit(); id = entity.getId(); }
         *
         * catch(RuntimeException e){ log.error(e);
         * asyncResultHandler.handle
         * (io.vertx.core.Future.succeededFuture(PostPatronsResponse
         * .withPlainInternalServerError( messages.getMessage(lang, "10001")
         * ))); } finally{ if(entityManager != null){ entityManager.close(); } }
         */
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsFines(String query, String orderBy, Order order, int offset, int limit, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    log.debug("sending... getPatronsFines");
    try {
      context.runOnContext(v -> {
        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsFinesResponse
          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsFinesResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsByPatronId(String patronId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler,Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put(ID_FIELD, patronId);
      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);
      log.debug("sending... getPatronsByPatronId");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).get(
          MongoCRUD.buildJson(Patron.class.getName(),Consts.PATRONS_COLLECTION, q.encode()),
          reply -> {
            try {
              List<Patron> patrons = (List<Patron>)reply.result();
              if (patrons.isEmpty()) {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse
                  .withPlainNotFound("Patron" + messages.getMessage(lang,  MessageConsts.ObjectDoesNotExist))));
              } else {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse
                  .withJsonOK(patrons.get(0))));
              }
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
            }
          });
      });
      // context.runOnContext( v -> {
      /*
       * EntityManager entityManager = null; try { entityManager =
       * entityManagerFactory.createEntityManager(); Patron patron =
       * entityManager.find(Patron.class, patronId, Collections.<String,
       * Object>emptyMap());
       * asyncResultHandler.handle(io.vertx.core.Future.succeededFuture
       * (GetPatronsByPatronIdResponse.withJsonOK( patron ))); }
       * catch(RuntimeException e){ e.printStackTrace();
       * asyncResultHandler.handle
       * (io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse
       * .withPlainInternalServerError( messages.getMessage(lang, "10001") )));
       * } finally{ if(entityManager != null){ entityManager.close(); } }
       */
      // });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void deletePatronsByPatronId(String patronId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      // context.runOnContext( v -> {
      /*
       * EntityManager entityManager = null; try { entityManager =
       * entityManagerFactory.createEntityManager(); Patron patron = new
       * Patron(); patron.setId(patronId); entityManager.merge(patron);
       * entityManager.remove(patron);
       * asyncResultHandler.handle(io.vertx.core.Future
       * .succeededFuture(DeletePatronsByPatronIdResponse.withNoContent())); }
       * catch(RuntimeException e){ e.printStackTrace();
       * asyncResultHandler.handle
       * (io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdResponse
       * .withPlainInternalServerError( messages.getMessage(lang, "10001") )));
       * } finally{ if(entityManager != null){ entityManager.close(); } }
       */
      // });

      log.debug("sending... deletePatronsByPatronId");
      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);
      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).delete(Consts.PATRONS_COLLECTION, patronId,
          reply -> {
            if(reply.succeeded()){
              if(reply.result().getRemovedCount() == 1){
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdResponse.withNoContent()));
              }
              else{
                String message = messages.getMessage(lang, MessageConsts.DeletedCountError, 1,reply.result().getRemovedCount());
                log.error(message);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdResponse
                  .withPlainNotFound(message)));
              }
            }
            else{
              log.error(reply.cause());
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void putPatronsByPatronId(String patronId, String lang, Patron entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put(ID_FIELD, patronId);
      log.debug("sending... putPatronsByPatronId");
      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);
      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).update(Consts.PATRONS_COLLECTION,
          entity, q, true,
          reply -> {
            if(reply.result().getDocModified() == 0){
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdResponse.
                withPlainNotFound(patronId)));
            }
            else{
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdResponse.withNoContent()));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdResponse
                  .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
              }
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsByPatronIdFines(String patronId, String query, String orderBy, Order order, int offset,
      int limit, String lang, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    log.debug("sending... getPatronsByPatronIdFines");
    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);
    try {
      context.runOnContext(v -> {
        JsonObject q = new JsonObject();
        if(query != null){
          q = new JsonObject(query);
        }
        q.put(PATRON_ID_FIELD, patronId);
        MongoCRUD.getInstance(vertx, tenantId).get(
          MongoCRUD.buildJson(Fine.class.getName(),Consts.FINES_COLLECTION, q, orderBy, order, offset, limit),
          reply -> {
            try {

              Fines fs = new Fines();
              List<Fine> fines = (List<Fine>)reply.result();
              fs.setFines(fines);
              fs.setTotalRecords(fines.size());

              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesResponse.withJsonOK(fs)));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void postPatronsByPatronIdFines(String patronId, String lang, Fine entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    // TODO - patron id is in the fines object as it is a required field in the fines object
    //therefore the framework will not pass the call here without that value
    log.debug("sending... postPatronsByPatronIdFines");
    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).save(Consts.FINES_COLLECTION,
          entity,
          reply -> {
            try {
              Fine f = entity;
              f.setId(reply.result());
              OutStream stream = new OutStream();
              stream.setData(f);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesResponse.withJsonCreated(
                reply.result(), stream)));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  /**
   * pay, waive or dispute a fine
   * currently:
   * pay - decrements amount indicated from fine
   * waive - sets the fine balance to zero
   * dispute - not supported
   * http://<host>:<port>/patrons/<patron_id>/fines/<fine_id>?amount=5
   */
  @Validate
  @Override
  public void postPatronsByPatronIdFinesByFineId(String fineId, String patronId, Op op, String amount,
      String paymentMethod, String comment, String lang, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context)
          throws Exception {

    final Op operation = op;
    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);
    if (amount == null) {
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesResponse
        .withPlainBadRequest(messages.getMessage(lang, CircMessageConsts.OperationOnNullAmount))));
      return;
    }
    log.debug("sending... postPatronsByPatronIdFinesByFineId");

    JsonObject q = new JsonObject();
    q.put(PATRON_ID_FIELD, patronId);
    q.put(ID_FIELD, fineId);

    try {
      // get the fine object to operate on
      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).get(
          MongoCRUD.buildJson(Fine.class.getName(),Consts.FINES_COLLECTION, q),
          reply -> {
            try {

              final List<Fine> fine = (List<Fine>)reply.result();
              if (fine.isEmpty()) {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse
                  .withPlainNotFound("Fine"  + messages.getMessage(lang, MessageConsts.ObjectDoesNotExist))));
                return;
              }
              Fine fines = fine.get(0);
              Double fineOutstanding = fines.getFineOutstanding();
              if(comment != null){
                fines.setFineNote(comment);
              }
              switch (operation.toString()) {
                case "pay":
                  Double newOutstanding = fineOutstanding - Integer.valueOf(amount);
                  if (newOutstanding < 0) {
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
                      .withPlainBadRequest(messages.getMessage(lang, CircMessageConsts.FinePaidTooMuch))));
                    return;
                  }
                  else{
                    fines.setFineOutstanding(newOutstanding);
                    if (newOutstanding == 0) {
                      fines.setFinePayInFull(true);
                      fines.setFinePayInPartial(false);
                    } else {
                      fines.setFinePayInFull(false);
                      fines.setFinePayInPartial(true);
                    }
                  }
                  break;
                case "waive":
                  fines.setFinePayInFull(true);
                  fines.setFinePayInPartial(false);
                  fines.setFineOutstanding(0.0);
                  break;
                case "dispute":
                  // TODO
                  break;
                default:
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
                    .withPlainBadRequest(messages.getMessage(lang, MessageConsts.OperationNotSupported))));
                  return;
              }
              // update the fine object with the new amounts
              MongoCRUD.getInstance(vertx, tenantId).update(Consts.FINES_COLLECTION,
                fines, q, true,
                reply2 -> {
                  try {
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
                      .withJsonCreated(fineId, fines)));
                  } catch (Exception e) {
                    log.error(e);
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
                      .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
                  }
                });

            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
                .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }

  }

  @Validate
  @Override
  public void getPatronsByPatronIdFinesByFineId(String fineId, String patronId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

      JsonObject q = new JsonObject();
      q.put(PATRON_ID_FIELD, patronId);
      q.put(ID_FIELD, fineId);

      log.debug("sending... getPatronsByPatronIdFinesByFineId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).get(
          MongoCRUD.buildJson(Fine.class.getName(),Consts.FINES_COLLECTION, q),
          reply -> {
            try {
              List<Fine> fine = (List<Fine>)reply.result();

              if (fine.isEmpty()) {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesByFineIdResponse
                  .withPlainNotFound("Fine " + messages.getMessage(lang, MessageConsts.ObjectDoesNotExist))));
              } else {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesByFineIdResponse
                  .withJsonOK(fine.get(0))));
              }
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesByFineIdResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesByFineIdResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void deletePatronsByPatronIdFinesByFineId(String fineId, String patronId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

      JsonObject q = new JsonObject();
      q.put(PATRON_ID_FIELD, patronId);
      q.put(ID_FIELD, fineId);

      log.debug("sending... deletePatronsByPatronIdFinesByFineId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId)
        .delete(Consts.FINES_COLLECTION, q,
          reply -> {
            try {

              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdFinesByFineIdResponse
                .withNoContent()));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdFinesByFineIdResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdFinesByFineIdResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void putPatronsByPatronIdFinesByFineId(String fineId, String patronId, String lang, Fine entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

      JsonObject q = new JsonObject();
      q.put(PATRON_ID_FIELD, patronId);
      q.put(ID_FIELD, fineId);

      log.debug("sending... putPatronsByPatronIdFinesByFineId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).update(Consts.FINES_COLLECTION, entity, q, true,
          reply -> {
            if(reply.succeeded() && reply.result().getDocMatched() == 0){
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdFinesByFineIdResponse.
                withPlainNotFound(patronId + " " + fineId)));
            }
            else{
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdFinesByFineIdResponse
                  .withNoContent()));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdFinesByFineIdResponse
                  .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
              }
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdFinesByFineIdResponse
        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsByPatronIdLoans(String patronId, int offset, int limit, String orderBy, Order order,
      String lang, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    log.debug("sending... getPatronsByPatronIdLoans");

    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

    JsonObject q = new JsonObject();
    q.put(PATRON_ID_FIELD, patronId);

    context.runOnContext(v -> {
      MongoCRUD.getInstance(vertx, tenantId).get(
        MongoCRUD.buildJson(Loan.class.getName(), Consts.LOANS_COLLECTION, q, orderBy, order, offset, limit),
        reply -> {
          try {
            Loans loanList = new Loans();
            List<Loan> loans = (List<Loan>)reply.result();
            loanList.setLoans(loans);
            loanList.setTotalRecords(loans.size());
            asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansResponse
              .withJsonOK(loanList)));
          } catch (Exception e) {
            log.error(e);
            asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansResponse
              .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

          }
        });
    });
  }

  @Validate
  @Override
  public void postPatronsByPatronIdLoans(String patronId, String lang, Loan entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    log.debug("sending... postPatronsByPatronIdLoans");
    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

    context.runOnContext(v -> {
      MongoCRUD.getInstance(vertx, tenantId).save(Consts.LOANS_COLLECTION, entity,
        reply -> {
          try {

            Loan l = entity;
            OutStream stream = new OutStream();
            l.setId(reply.result());
            l.getId();
            stream.setData(l);
            asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansResponse.withJsonCreated(
              reply.result(), stream)));
          } catch (Exception e) {
            log.error(e);
            asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansResponse
              .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

          }
        });
    });
  }

  /**
   * renew a loan
   * periodType can be days, weeks, months
   * period is a number indicating how many days, weeks, months to extend the loan for,
   *  i.e periodType=days and period=7 means extend the loan for another 7 days from today
   *  defaults to days
   * http://<host>:<port>/patrons/<patron_id>/loans/<loan_id>?operation=renew&period=7
   */
  @Validate
  @Override
  public void postPatronsByPatronIdLoansByLoanId(String loanId, String patronId, Operation operation,
      int period, String periodType, String lang, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler,
      Context vertxContext) throws Exception {

    log.debug("sending... postPatronsByPatronIdLoansByLoanId");
    Vertx vertx = vertxContext.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);
    try {
      JsonObject q = new JsonObject();
      q.put(PATRON_ID_FIELD, patronId);
      q.put(ID_FIELD, loanId);

      if(period < 1){
        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
          .withPlainBadRequest("Loan " + messages.getMessage(lang, CircMessageConsts.LoanPeriodError, period))));
        return;
      }

      if (operation == null || operation.ordinal() == -1) {
        operation = Operation.renew;
      }

      final Operation op = operation;

      vertxContext.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).get(
          MongoCRUD.buildJson(Loan.class.getName(), Consts.LOANS_COLLECTION, q) ,
          reply -> {
            try {
              if("renew".equals(op.toString())){
                List<Loan> loans = (List<Loan>)reply.result();
                if (loans.isEmpty()) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
                    .withPlainBadRequest("Loan " + messages.getMessage(lang,MessageConsts.ObjectDoesNotExist))));
                  return;
                }

                Loan loan = loans.get(0);

                if(!loan.getRenewable()){
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
                    .withPlainBadRequest(messages.getMessage(lang, CircMessageConsts.NonRenewable, loan.getItemId()))));
                  return;
                }

                Double dueDate = loan.getDueDate();
                long dayInMilli = 24*60*60*1000L;
                if("weeks".equals(periodType)){
                  dueDate = (double)System.currentTimeMillis() + period*7*dayInMilli;
                }
                else if("months".equals(periodType)){
                  dueDate = (double)System.currentTimeMillis() + period*30*7*dayInMilli;
                }
                else{
                  //default days
                  dueDate = (double)System.currentTimeMillis() + period*dayInMilli;
                }
                loan.setDueDate(dueDate);

                loan.setRenewCount(loan.getRenewCount()+1);

                MongoCRUD.getInstance(vertx, tenantId).update(Consts.LOANS_COLLECTION, loan, q, reply2 -> {
                  try {
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
                      .withJsonCreated(loanId, loan)));
                  } catch (Exception e) {
                    log.error(e);
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
                      .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
                  }
                });
              }
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
                .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsByPatronIdLoansByLoanId(String loanId, String patronId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    log.debug("sending... getPatronsByPatronIdLoansByLoanId");
    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

    JsonObject q = new JsonObject();
    q.put(PATRON_ID_FIELD, patronId);
    q.put(ID_FIELD, loanId);
    context.runOnContext(v -> {
      MongoCRUD.getInstance(vertx, tenantId).get(
        MongoCRUD.buildJson(Loan.class.getName(), Consts.LOANS_COLLECTION, q),
        reply -> {
          try {
            List<Loan> loan = (List<Loan>)reply.result();

            if (loan.isEmpty()) {
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansByLoanIdResponse
                .withPlainNotFound("Loan " + messages.getMessage(lang,  MessageConsts.ObjectDoesNotExist))));
            } else {
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansByLoanIdResponse
                .withJsonOK(loan.get(0))));
            }
          } catch (Exception e) {
            log.error(e);
            asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansByLoanIdResponse
              .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
          }
        });
    });
  }

  @Validate
  @Override
  public void deletePatronsByPatronIdLoansByLoanId(String loanId, String patronId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

      JsonObject q = new JsonObject();
      q.put(PATRON_ID_FIELD, patronId);
      q.put(ID_FIELD, loanId);

      log.debug("sending... deletePatronsByPatronIdLoansByLoanId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId)
        .delete(Consts.LOANS_COLLECTION, q,
          reply -> {
            try {
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdLoansByLoanIdResponse
                .withNoContent()));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdLoansByLoanIdResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdLoansByLoanIdResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

    }
  }

  @Validate
  @Override
  public void putPatronsByPatronIdLoansByLoanId(String loanId, String patronId, String lang, Loan entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    JsonObject q = new JsonObject();
    q.put(PATRON_ID_FIELD, patronId);
    q.put(ID_FIELD, loanId);
    try {

      log.debug("sending... putPatronsByPatronIdLoansByLoanId");

      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).update(Consts.LOANS_COLLECTION, entity, q, true,
          reply -> {
            if(reply.succeeded() && reply.result().getDocMatched() == 0){
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdLoansByLoanIdResponse.
                withPlainNotFound(patronId + " " + loanId)));
            }
            else{
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdLoansByLoanIdResponse
                  .withNoContent()));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdLoansByLoanIdResponse
                  .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
              }
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdLoansByLoanIdResponse
        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

    }
  }

  @Override
  public void getPatronsByPatronIdRequests(String patronId, Status status, RequestType requestType,
      int offset, int limit, String lang, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    log.debug("sending... getPatronsByPatronIdRequests");

    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

    JsonObject q = new JsonObject();
    q.put(PATRON_ID_FIELD, patronId);
    if (requestType != null) {
      q.put("request_type", requestType.toString());
    }
    if (status != null) {
      q.put("request_status", status.toString());
    }
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).get(
          MongoCRUD.buildJson(ItemRequest.class.getName(), Consts.REQUEST_COLLECTION, q, null, null, offset, limit),
          reply -> {
            try {
              ItemRequests ps = new ItemRequests();
              List<ItemRequest> requests = (List<ItemRequest>)reply.result();
              ps.setItemRequests(requests);
              ps.setTotalRecords(requests.size());

              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse.withJsonOK(ps)));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

    }
  }

  @Validate
  @Override
  public void postPatronsByPatronIdRequests(String patronId, String itemId, String lang, ItemRequest entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    log.debug("sending... postPatronsByPatronIdRequests");

    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

    // patron id and item id currently in the json passed in the body
    entity.setPatronId(patronId);
    entity.setItemId(itemId);
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).save(Consts.REQUEST_COLLECTION, entity,
          reply -> {
            try {
              ItemRequest ir = entity;
              OutStream stream = new OutStream();
              stream.setData(ir);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdRequestsResponse.withJsonCreated(
                reply.result(), stream)));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdRequestsResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdRequestsResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsByPatronIdRequestsByRequestId(String requestId, String patronId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    log.debug("sending... getPatronsByPatronIdRequestsByRequestId");
    Vertx vertx = context.owner();
    String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

    try {
      JsonObject q = new JsonObject();
      q.put(PATRON_ID_FIELD, patronId);
      q.put(ID_FIELD, requestId);
      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).get(
          MongoCRUD.buildJson(ItemRequest.class.getName(), Consts.REQUEST_COLLECTION, q),
          reply -> {
            try {
              List<ItemRequest> requests = (List<ItemRequest>)reply.result();
              if (requests.isEmpty()) {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsByRequestIdResponse
                  .withPlainNotFound("Item request " + messages.getMessage(lang,  MessageConsts.ObjectDoesNotExist))));
                return;
              }
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsByRequestIdResponse
                .withJsonOK(requests.get(0))));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse
                .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse
        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

    }
  }

  @Validate
  @Override
  public void deletePatronsByPatronIdRequestsByRequestId(String requestId, String patronId, String reason,
      String comment, String notify, String lang, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put(PATRON_ID_FIELD, patronId);
      q.put(ID_FIELD, requestId);

      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

      log.debug("sending... deletePatronsByPatronIdRequestsByRequestId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).delete(Consts.REQUEST_COLLECTION, q,
          reply -> {
            try {
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdRequestsByRequestIdResponse
                .withNoContent()));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdRequestsByRequestIdResponse
                .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdRequestsByRequestIdResponse
        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void putPatronsByPatronIdRequestsByRequestId(String requestId, String patronId, String lang,
      ItemRequest entity, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put(PATRON_ID_FIELD, patronId);
      q.put(ID_FIELD, requestId);

      log.debug("sending... putPatronsByPatronIdRequestsByRequestId");

      Vertx vertx = context.owner();
      String tenantId = okapiHeaders.get(OKAPI_HEADER_TENANT);

      context.runOnContext(v -> {
        MongoCRUD.getInstance(vertx, tenantId).update(Consts.REQUEST_COLLECTION, entity, q, true,
          reply -> {
            if(reply.succeeded() && reply.result().getDocMatched() == 0){
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdRequestsByRequestIdResponse.
                withPlainNotFound(patronId + " " + requestId)));
            }
            try {
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdRequestsByRequestIdResponse
                .withNoContent()));
            } catch (Exception e) {
              log.error(e);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdRequestsByRequestIdResponse
                .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
            }
          });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdRequestsByRequestIdResponse
        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

    }
  }


}
