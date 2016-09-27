package org.folio.rest.impl;

import java.util.List;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

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
import org.folio.utils.CircMessageConsts;
import org.folio.utils.Consts;
import org.folio.rest.tools.utils.OutStream;
import org.folio.rest.tools.messages.*;

@Path("apis/patrons")
public class PatronAPI implements PatronsResource {

  // private final Logger log = LoggerFactory.getLogger(getClass());
  private final Messages            messages = Messages.getInstance();
  private static final Logger log = LoggerFactory.getLogger(PatronAPI.class);

  // @TODO if revert back to hibernate ogm - uncomment this! two examples of
  // usasge in two of the functions below
  // private final static EntityManagerFactory entityManagerFactory =
  // Persistence.createEntityManagerFactory( "mongoStore" );

  @Validate
  @Override
  public void getPatrons(String authorization, String query, String orderBy, Order order, int offset, int limit, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    log.debug("sending... getPatrons");
    /*JsonObject jObj = RestUtils.createMongoObject(Consts.PATRONS_COLLECTION, Consts.METHOD_GET, authorization, q, orderBy, order, offset,
        limit, null, null);*/
    context.runOnContext(v -> {
      try {
        MongoCRUD.getInstance(context.owner()).get(
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
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withPlainInternalServerError(messages
                    .getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      } catch (Exception e) {
        e.printStackTrace();
        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withPlainInternalServerError(messages.getMessage(
            lang,  MessageConsts.InternalServerError))));
      }
    });
  }

  @Validate
  @Override
  public void postPatrons(String authorization, String lang, Patron entity, Handler<AsyncResult<Response>> asyncResultHandler,
      Context context) throws Exception {

    try {
      System.out.println("sending... postPatrons");

      context.runOnContext(v -> {

        try {
          MongoCRUD.getInstance(context.owner())
              .save(Consts.PATRONS_COLLECTION, entity,
                  reply -> {
                    try {
                      Patron p = new Patron();
                      p = entity;
                      p.setId(reply.result());
                      OutStream stream = new OutStream();
                      stream.setData(p);
                      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsResponse.withJsonCreated(reply.result(),
                          stream)));
                    } catch (Exception e) {
                      e.printStackTrace();
                      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsResponse
                          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
                    }
                  });
        } catch (Exception e) {
          e.printStackTrace();
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
         * catch(RuntimeException e){ e.printStackTrace();
         * asyncResultHandler.handle
         * (io.vertx.core.Future.succeededFuture(PostPatronsResponse
         * .withPlainInternalServerError( messages.getMessage(lang, "10001")
         * ))); } finally{ if(entityManager != null){ entityManager.close(); } }
         */
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsResponse.withPlainInternalServerError(messages.getMessage(
          lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsFines(String authorization, String query, String orderBy, Order order, int offset, int limit, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... getPatronsFines");
    try {
      context.runOnContext(v -> {
/*        MongoCRUD.getInstance(context.owner()).get(
            jObj,
            reply -> {
              try {
                Fines fs = new Fines();
                List<Fine> fines = mapper.readValue(reply.result().toString(),
                    mapper.getTypeFactory().constructCollectionType(List.class, Fine.class));
                fs.setFines(fines);
                fs.setTotalRecords(fines.size());
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsFinesResponse.withJsonOK(fs)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsFinesResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });*/
          asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsFinesResponse
            .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsFinesResponse.withPlainInternalServerError(messages
          .getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsByPatronId(String patronId, String authorization, String lang, Handler<AsyncResult<Response>> asyncResultHandler,
      Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("_id", patronId);

      System.out.println("sending... getPatronsByPatronId");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Patron.class.getName(),Consts.PATRONS_COLLECTION, q.encode()),
            reply -> {
              try {
                List<Patron> patrons = (List<Patron>)reply.result();
                if (patrons.size() == 0) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse.withPlainNotFound("Patron"
                      + messages.getMessage(lang,  MessageConsts.ObjectDoesNotExist))));
                } else {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse.withJsonOK(patrons.get(0))));
                }
              } catch (Exception e) {
                e.printStackTrace();
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
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse.withPlainInternalServerError(messages
          .getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void deletePatronsByPatronId(String patronId, String authorization, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

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

      System.out.println("sending... deletePatronsByPatronId");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).delete(Consts.PATRONS_COLLECTION, patronId,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdResponse.withNoContent()));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdResponse.withPlainInternalServerError(messages
          .getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void putPatronsByPatronId(String patronId, String authorization, String lang, Patron entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("_id", patronId);
      System.out.println("sending... putPatronsByPatronId");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).update(Consts.PATRONS_COLLECTION,
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
                  e.printStackTrace();
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdResponse
                      .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
                }
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdResponse.withPlainInternalServerError(messages
          .getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsByPatronIdFines(String patronId, String authorization, String query, String orderBy, Order order, int offset,
      int limit, String lang, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    // q.put("fine_data.patron_id", patronId);

    System.out.println("sending... getPatronsByPatronIdFines");

    try {
      context.runOnContext(v -> {
        JsonObject q = new JsonObject();
        if(query != null){
          q = new JsonObject(query);
        }
        q.put("patron_id", patronId);
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Fine.class.getName(),Consts.FINES_COLLECTION, q, orderBy, order, offset, limit),
            reply -> {
              try {

                Fines fs = new Fines();
                List<Fine> fines = (List<Fine>)reply.result();
                fs.setFines(fines);
                fs.setTotalRecords(fines.size());

                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesResponse.withJsonOK(fs)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesResponse
                    .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesResponse
          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void postPatronsByPatronIdFines(String patronId, String authorization, String lang, Fine entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    // TODO - patron id is in the fines object as it is a required field in the fines object
    //therefore the framework will not pass the call here without that value
    System.out.println("sending... postPatronsByPatronIdFines");

    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).save(Consts.FINES_COLLECTION,
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
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesResponse
                    .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
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
   * http://<host>:<port>/apis/patrons/<patron_id>/fines/<fine_id>?amount=5
   */
  @Validate
  @Override
  public void postPatronsByPatronIdFinesByFineId(String fineId, String patronId, String authorization, Op op, String amount,
      String paymentMethod, String comment, String lang, Handler<AsyncResult<Response>> asyncResultHandler, Context context)
      throws Exception {

    final Op operation = op;

    if (amount == null) {
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesResponse.withPlainBadRequest(messages
          .getMessage(lang, CircMessageConsts.OperationOnNullAmount))));
      return;
    }
    System.out.println("sending... postPatronsByPatronIdFinesByFineId");

    JsonObject q = new JsonObject();
    q.put("patron_id", patronId);
    q.put("_id", fineId);

    try {
      // get the fine object to operate on
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Fine.class.getName(),Consts.FINES_COLLECTION, q),
            reply -> {
              try {

                final List<Fine> fine = (List<Fine>)reply.result();
                if (fine.size() == 0) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdResponse.withPlainNotFound("Fine"
                      + messages.getMessage(lang, MessageConsts.ObjectDoesNotExist))));
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
                MongoCRUD.getInstance(context.owner()).update(Consts.FINES_COLLECTION,
                  fines, q, true,
                    reply2 -> {
                      try {
                        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
                            .withJsonCreated(fineId, fines)));
                      } catch (Exception e) {
                        e.printStackTrace();
                        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
                            .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
                      }
                    });

              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdFinesByFineIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }

  }

  @Validate
  @Override
  public void getPatronsByPatronIdFinesByFineId(String fineId, String patronId, String authorization, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("patron_id", patronId);
      q.put("_id", fineId);

      System.out.println("sending... getPatronsByPatronIdFinesByFineId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Fine.class.getName(),Consts.FINES_COLLECTION, q),
            reply -> {
              try {
                List<Fine> fine = (List<Fine>)reply.result();

                if (fine.size() == 0) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesByFineIdResponse
                      .withPlainNotFound("Fine " + messages.getMessage(lang, MessageConsts.ObjectDoesNotExist))));
                } else {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesByFineIdResponse.withJsonOK(fine
                      .get(0))));
                }
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesByFineIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdFinesByFineIdResponse
          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void deletePatronsByPatronIdFinesByFineId(String fineId, String patronId, String authorization, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("patron_id", patronId);
      q.put("_id", fineId);

      System.out.println("sending... deletePatronsByPatronIdFinesByFineId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner())
            .delete(Consts.FINES_COLLECTION, q,
                reply -> {
                  try {

                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdFinesByFineIdResponse
                        .withNoContent()));
                  } catch (Exception e) {
                    e.printStackTrace();
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdFinesByFineIdResponse
                        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
                  }
                });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdFinesByFineIdResponse
          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void putPatronsByPatronIdFinesByFineId(String fineId, String patronId, String authorization, String lang, Fine entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("patron_id", patronId);
      q.put("_id", fineId);

      System.out.println("sending... putPatronsByPatronIdFinesByFineId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).update(Consts.FINES_COLLECTION, entity, q, true,
            reply -> {
              if(reply.succeeded() && reply.result().getDocMatched() == 0){
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdFinesByFineIdResponse.
                  withPlainNotFound(patronId + " " + fineId)));
              }
              else{
                try {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdFinesByFineIdResponse.withNoContent()));
                } catch (Exception e) {
                  e.printStackTrace();
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdFinesByFineIdResponse
                      .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
                }
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdFinesByFineIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsByPatronIdLoans(String patronId, String authorization, int offset, int limit, String orderBy, Order order,
      String lang, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... getPatronsByPatronIdLoans");

    JsonObject q = new JsonObject();
    q.put("patron_id", patronId);

    context.runOnContext(v -> {
      MongoCRUD.getInstance(context.owner()).get(
        MongoCRUD.buildJson(Loan.class.getName(), Consts.LOANS_COLLECTION, q, orderBy, order, offset, limit),
          reply -> {
            try {
              Loans loanList = new Loans();
              List<Loan> loans = (List<Loan>)reply.result();
              loanList.setLoans(loans);
              loanList.setTotalRecords(loans.size());
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansResponse.withJsonOK(loanList)));
            } catch (Exception e) {
              e.printStackTrace();
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansResponse
                  .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

            }
          });
    });
  }

  @Validate
  @Override
  public void postPatronsByPatronIdLoans(String patronId, String authorization, String lang, Loan entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... postPatronsByPatronIdLoans");
    context.runOnContext(v -> {
      MongoCRUD.getInstance(context.owner()).save(Consts.LOANS_COLLECTION, entity,
          reply -> {
            try {

              Loan l = entity;
              //l.setLoanId(reply.result());
              OutStream stream = new OutStream();
              l.setId(reply.result());
              l.getId();
              stream.setData(l);
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansResponse.withJsonCreated(
                  reply.result(), stream)));
            } catch (Exception e) {
              e.printStackTrace();
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
   * http://<host>:<port>/apis/patrons/<patron_id>/loans/<loan_id>?operation=renew&period=7
   */
  @Validate
  @Override
  public void postPatronsByPatronIdLoansByLoanId(String loanId, String patronId, String authorization, Operation operation, int period,
      String periodType, String lang, Handler<AsyncResult<Response>> asyncResultHandler, Context vertxContext) throws Exception {

    System.out.println("sending... postPatronsByPatronIdLoansByLoanId");

    try {
      JsonObject q = new JsonObject();
      q.put("patron_id", patronId);
      q.put("_id", loanId);

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
        MongoCRUD.getInstance(vertxContext.owner()).get(
          MongoCRUD.buildJson(Loan.class.getName(), Consts.LOANS_COLLECTION, q) ,
            reply -> {
              try {
                switch (op.toString()) {
                  case "renew":

                    List<Loan> loans = (List<Loan>)reply.result();
                    if (loans.size() == 0) {
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
                    long dayInMilli = 24*60*60*1000;
                    if(periodType.equals("weeks")){
                      dueDate = new Long (System.currentTimeMillis() + period*7*dayInMilli).doubleValue();
                    }
                    else if(periodType.equals("months")){
                      dueDate = new Long (System.currentTimeMillis() + period*30*7*dayInMilli).doubleValue();
                    }
                    else{
                      //default days
                      dueDate = new Long (System.currentTimeMillis() + period*dayInMilli).doubleValue();
                    }
                    loan.setDueDate(dueDate);

                    loan.setRenewCount(loan.getRenewCount()+1);

                    MongoCRUD.getInstance(vertxContext.owner()).update(Consts.LOANS_COLLECTION, loan, q, reply2 -> {
                      try {
                        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
                            .withJsonCreated(loanId, loan)));
                      } catch (Exception e) {
                        e.printStackTrace();
                        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
                            .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
                      }
                    });
                }
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdLoansByLoanIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

    }
  }

  @Validate
  @Override
  public void getPatronsByPatronIdLoansByLoanId(String loanId, String patronId, String authorization, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... getPatronsByPatronIdLoansByLoanId");

    JsonObject q = new JsonObject();
    q.put("patron_id", patronId);
    q.put("_id", loanId);
    context.runOnContext(v -> {
      MongoCRUD.getInstance(context.owner()).get(
        MongoCRUD.buildJson(Loan.class.getName(), Consts.LOANS_COLLECTION, q),
          reply -> {
            try {
              List<Loan> loan = (List<Loan>)reply.result();

              if (loan.size() == 0) {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansByLoanIdResponse
                    .withPlainNotFound("Loan " + messages.getMessage(lang,  MessageConsts.ObjectDoesNotExist))));
              } else {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansByLoanIdResponse.withJsonOK(loan
                    .get(0))));
              }
            } catch (Exception e) {
              e.printStackTrace();
              asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdLoansByLoanIdResponse
                  .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
            }
          });
    });
  }

  @Validate
  @Override
  public void deletePatronsByPatronIdLoansByLoanId(String loanId, String patronId, String authorization, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("patron_id", patronId);
      q.put("_id", loanId);

      System.out.println("sending... deletePatronsByPatronIdLoansByLoanId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner())
            .delete(Consts.LOANS_COLLECTION, q,
                reply -> {
                  try {
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdLoansByLoanIdResponse
                        .withNoContent()));
                  } catch (Exception e) {
                    e.printStackTrace();
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdLoansByLoanIdResponse
                        .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

                  }
                });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdLoansByLoanIdResponse
          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

    }
  }

  @Validate
  @Override
  public void putPatronsByPatronIdLoansByLoanId(String loanId, String patronId, String authorization, String lang, Loan entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    JsonObject q = new JsonObject();
    q.put("patron_id", patronId);
    q.put("_id", loanId);
    try {

      System.out.println("sending... putPatronsByPatronIdLoansByLoanId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).update(Consts.LOANS_COLLECTION, entity, q, true,
            reply -> {
              if(reply.succeeded() && reply.result().getDocMatched() == 0){
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdLoansByLoanIdResponse.
                  withPlainNotFound(patronId + " " + loanId)));
              }
              else{
                try {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdLoansByLoanIdResponse.withNoContent()));
                } catch (Exception e) {
                  e.printStackTrace();
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdLoansByLoanIdResponse
                      .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
                }  
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdLoansByLoanIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

    }
  }

  @Override
  public void getPatronsByPatronIdRequests(String patronId, String authorization, Status status, RequestType requestType, int offset,
      int limit, String lang, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... getPatronsByPatronIdRequests");

    JsonObject q = new JsonObject();
    q.put("patron_id", patronId);
    if (requestType != null) {
      q.put("request_type", requestType.toString());
    }
    if (status != null) {
      q.put("request_status", status.toString());
    }
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(ItemRequest.class.getName(), Consts.REQUEST_COLLECTION, q, null, null, offset, limit),
            reply -> {
              try {
                ItemRequests ps = new ItemRequests();
                List<ItemRequest> requests = (List<ItemRequest>)reply.result();
                ps.setItemRequests(requests);
                ps.setTotalRecords(requests.size());

                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse.withJsonOK(ps)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse
                    .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse
          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

    }
  }

  @Validate
  @Override
  public void postPatronsByPatronIdRequests(String patronId, String authorization, String itemId, String lang, ItemRequest entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... postPatronsByPatronIdRequests");

    // patron id and item id currently in the json passed in the body
    entity.setPatronId(patronId);
    entity.setItemId(itemId);
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).save(Consts.REQUEST_COLLECTION, entity,
            reply -> {
              try {
                ItemRequest ir = entity;
                OutStream stream = new OutStream();
                stream.setData(ir);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdRequestsResponse.withJsonCreated(
                    reply.result(), stream)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdRequestsResponse
                    .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostPatronsByPatronIdRequestsResponse
          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getPatronsByPatronIdRequestsByRequestId(String requestId, String patronId, String authorization, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... getPatronsByPatronIdRequestsByRequestId");
    OutStream os;
    try {
      JsonObject q = new JsonObject();
      q.put("patron_id", patronId);
      q.put("_id", requestId);
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(ItemRequest.class.getName(), Consts.REQUEST_COLLECTION, q),
            reply -> {
              try {
                List<ItemRequest> requests = (List<ItemRequest>)reply.result();
                if (requests.size() == 0) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsByRequestIdResponse
                      .withPlainNotFound("Item request " + messages.getMessage(lang,  MessageConsts.ObjectDoesNotExist))));
                  return;
                }
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsByRequestIdResponse
                    .withJsonOK(requests.get(0))));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse
                    .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsByPatronIdRequestsResponse
          .withPlainInternalServerError(messages.getMessage(lang,  MessageConsts.InternalServerError))));

    }
  }

  @Validate
  @Override
  public void deletePatronsByPatronIdRequestsByRequestId(String requestId, String patronId, String authorization, String reason,
      String comment, String notify, String lang, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("patron_id", patronId);
      q.put("_id", requestId);

      System.out.println("sending... deletePatronsByPatronIdRequestsByRequestId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).delete(Consts.REQUEST_COLLECTION, q,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdRequestsByRequestIdResponse
                    .withNoContent()));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdRequestsByRequestIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeletePatronsByPatronIdRequestsByRequestIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void putPatronsByPatronIdRequestsByRequestId(String requestId, String patronId, String authorization, String lang,
      ItemRequest entity, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("patron_id", patronId);
      q.put("_id", requestId);

      System.out.println("sending... putPatronsByPatronIdRequestsByRequestId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).update(Consts.REQUEST_COLLECTION, entity, q, true,
            reply -> {
              if(reply.succeeded() && reply.result().getDocMatched() == 0){
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdRequestsByRequestIdResponse.
                  withPlainNotFound(patronId + " " + requestId)));
              }
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdRequestsByRequestIdResponse
                    .withNoContent()));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdRequestsByRequestIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutPatronsByPatronIdRequestsByRequestIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

    }
  }


}
