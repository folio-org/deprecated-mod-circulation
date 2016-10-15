package org.folio.rest.impl;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import org.folio.rest.annotations.Validate;
import org.folio.rest.jaxrs.model.Fine;
import org.folio.rest.jaxrs.model.Fines;
import org.folio.rest.jaxrs.model.Item;
import org.folio.rest.jaxrs.model.ItemRequest;
import org.folio.rest.jaxrs.model.ItemRequests;
import org.folio.rest.jaxrs.model.Items;
import org.folio.rest.jaxrs.resource.ItemsResource;
import org.folio.rest.persist.MongoCRUD;
import org.folio.utils.Consts;
import org.folio.rest.tools.utils.OutStream;
import org.folio.rest.tools.messages.MessageConsts;
import org.folio.rest.tools.messages.Messages;

public class ItemsAPI implements ItemsResource {

  private static final Logger log            = LoggerFactory.getLogger(ItemsAPI.class);
  private static final String ITEM_ID        = "item_id";
  private final Messages messages            = Messages.getInstance();

  @Validate
  @Override
  public void getItems(String query, String orderBy, Order order, int offset, int limit, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      System.out.println("sending... getItems");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Item.class.getName(), Consts.ITEM_COLLECTION, query, orderBy, order, offset, limit),
            reply -> {
              try {
                List<Item> items = (List<Item>)reply.result();
                Items itemList = new Items();
                itemList.setItems(items);
                itemList.setTotalRecords(items.size());
                // TODO verify response
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsResponse.withJsonOK(itemList)));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsResponse
                  .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsResponse.withPlainInternalServerError(
        messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void postItems(String lang, Item entity, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler,
      Context context) throws Exception {

    try {
      System.out.println("sending... postItems");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner())
            .save(Consts.ITEM_COLLECTION, entity,
                reply -> {
                  try {
                    Item item = entity;
                    OutStream stream = new OutStream();
                    stream.setData(item);
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsResponse.withJsonCreated(reply.result(),
                        stream)));
                  } catch (Exception e) {
                    log.error(e);
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsResponse
                      .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));

                  }
                });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsResponse.withPlainInternalServerError(
        messages.getMessage(lang, MessageConsts.InternalServerError))));

    }
  }

  @Validate
  @Override
  public void getItemsByItemId(String itemId, String view, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("_id", itemId);
      System.out.println("sending... getItemsByItemId");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Item.class.getName(), Consts.ITEM_COLLECTION, q),
            reply -> {
              try {
                List<Item> items = (List<Item>)reply.result();
                if (items.isEmpty()) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdResponse.withPlainNotFound("Item "
                      + messages.getMessage(lang, "10008"))));
                  return;
                }
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdResponse.withJsonOK(items.get(0))));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdResponse.withPlainInternalServerError(messages
          .getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void deleteItemsByItemId(String itemId, String lang, Map<String, String>okapiHeaders,
      Handler<AsyncResult<Response>> asyncResultHandler,
      Context context) throws Exception {

    try {
      System.out.println("sending... deleteItemsByItemId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).delete(Consts.ITEM_COLLECTION, itemId,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteItemsByItemIdResponse.withNoContent()));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteItemsByItemIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteItemsByItemIdResponse.withPlainInternalServerError(
        messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void putItemsByItemId(String itemId, String lang, Item entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("_id", itemId);

      System.out.println("sending... putItemsByItemId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).update(Consts.ITEM_COLLECTION, entity, q, true,
            reply -> {
              if(reply.succeeded() && reply.result().getDocMatched() == 0){
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdResponse.
                  withPlainNotFound(itemId)));
              }
              else{
                try {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdResponse.withNoContent()));
                } catch (Exception e) {
                  log.error(e);
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdResponse
                      .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
                }
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdResponse.withPlainInternalServerError(messages
          .getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getItemsByItemIdFines(String itemId, String query, String orderBy, Order order,
      int offset, int limit, String lang, Map<String, String>okapiHeaders,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {

      System.out.println("sending... getItemsByItemIdFines");
      context.runOnContext(v -> {
        JsonObject q = new JsonObject();
        if(query != null){
          q = new JsonObject(query);
        }
        q.put(ITEM_ID, itemId);
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Fine.class.getName(), Consts.FINES_COLLECTION, q),
            reply -> {
              try {
                Fines fineList = new Fines();
                List<Fine> fines = (List<Fine>)reply.result();
                fineList.setFines(fines);
                fineList.setTotalRecords(fines.size());
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdFinesResponse.withJsonOK(fineList)));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdFinesResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdFinesResponse
        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void postItemsByItemIdFines(String itemId, String lang, Fine entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      System.out.println("sending... postItemsByItemIdFines");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).save(Consts.FINES_COLLECTION, entity,
            reply -> {
              try {

                Fine fine = entity;
                OutStream stream = new OutStream();
                stream.setData(fine);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesResponse
                  .withJsonCreated(reply.result(),stream)));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesResponse
        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }

  }

  @Validate
  @Override
  public void postItemsByItemIdFinesByFineId(String fineId, String itemId, Op op, String amount,
      String paymentMethod, String comment, String lang, Map<String, String>okapiHeaders,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context)
      throws Exception {

    if (op == null) {
      op = Op.pay;
    }

    final Op operation = op;

    if (amount == null) {
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesByFineIdResponse
        .withPlainBadRequest(messages.getMessage(lang, "20002"))));
      return;
    }
    System.out.println("sending... postItemsByItemIdFinesByFineId");

    JsonObject q = new JsonObject();
    q.put(ITEM_ID, itemId);
    q.put("_id", fineId);
    try {
      // get the fine object to operate on
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Fine.class.getName(), Consts.FINES_COLLECTION, q),
            reply -> {
              try {
                List<Fine> fines = (List<Fine>)reply.result();
                if (fines.isEmpty()) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesByFineIdResponse
                      .withPlainBadRequest("Fine " + messages.getMessage(lang, "10008"))));
                  return;
                }
                Fine fine = fines.get(0);
                Double fineOutstanding = fine.getFineOutstanding();
                if(comment != null){
                  fine.setFineNote(comment);
                }
                switch (operation.toString()) {
                  case "pay":
                    Double newOutstanding = fineOutstanding - Integer.valueOf(amount);
                    if (newOutstanding < 0) {
                      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesByFineIdResponse
                          .withPlainBadRequest(messages.getMessage(lang, "20003"))));
                      return;
                    }
                    else{
                      fine.setFineOutstanding(newOutstanding);

                      if (newOutstanding == 0) {
                        fine.setFinePayInFull(true);
                        fine.setFinePayInPartial(false);
                      } else {
                        fine.setFinePayInFull(false);
                        fine.setFinePayInPartial(true);
                      }
                    }
                    break;
                  case "waive":
                    fine.setFinePayInFull(true);
                    fine.setFinePayInPartial(false);
                    fine.setFineOutstanding(0.0);

                    break;
                  case "dispute":
                    // TODO
                    break;
                  default:
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesByFineIdResponse
                        .withPlainBadRequest(messages.getMessage(lang, "10002"))));
                    return;
                }
                // update the fine object with the new amounts

                MongoCRUD.getInstance(context.owner()).update(Consts.FINES_COLLECTION, fine, q,
                    reply2 -> {
                      try {
                        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesByFineIdResponse
                            .withJsonCreated(fine.getId(), fine)));
                      } catch (Exception e) {
                        log.error(e);
                        asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesByFineIdResponse
                            .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
                      }
                    });
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesByFineIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdFinesByFineIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void getItemsByItemIdFinesByFineId(String fineId, String itemId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put(ITEM_ID, itemId);
      q.put("_id", fineId);

      System.out.println("sending... getItemsByItemIdFinesByFineId");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner())
            .get(
              MongoCRUD.buildJson(Fine.class.getName(), Consts.FINES_COLLECTION, q),
                reply -> {
                  try {
                    List<Fine> fine = (List<Fine>)reply.result();
                    if (fine.isEmpty()) {
                      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdFinesByFineIdResponse
                          .withPlainNotFound("Fine " + messages.getMessage(lang, "10008"))));
                      return;
                    }
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdFinesByFineIdResponse
                      .withJsonOK(fine.get(0))));
                  } catch (Exception e) {
                    log.error(e);
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdFinesByFineIdResponse
                        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
                  }
                });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdFinesByFineIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void deleteItemsByItemIdFinesByFineId(String fineId, String itemId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put(ITEM_ID, itemId);
      q.put("_id", fineId);
      System.out.println("sending... deleteItemsByItemIdFinesByFineId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).delete(Consts.FINES_COLLECTION, q,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteItemsByItemIdFinesByFineIdResponse
                  .withNoContent()));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteItemsByItemIdFinesByFineIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteItemsByItemIdFinesByFineIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void putItemsByItemIdFinesByFineId(String fineId, String itemId, String lang, Fine entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put(ITEM_ID, itemId);
      q.put("_id", fineId);
      System.out.println("sending... putItemsByItemIdFinesByFineId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).update(Consts.FINES_COLLECTION, entity, q,
            reply -> {
              if(reply.succeeded() && reply.result().getDocMatched() == 0){
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdFinesByFineIdResponse.
                  withPlainNotFound(itemId + " " + fineId)));
              }
              else{
                try {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdFinesByFineIdResponse
                    .withNoContent()));
                } catch (Exception e) {
                  log.error(e);
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdFinesByFineIdResponse
                      .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
                }               
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdFinesByFineIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void postItemsByItemIdRequests(String itemId, String lang, ItemRequest entity,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... postItemsByItemIdRequests");
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).save(Consts.REQUEST_COLLECTION, entity,
            reply -> {
              try {
                ItemRequest ir = entity;
                OutStream stream = new OutStream();
                stream.setData(ir);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdRequestsResponse.withJsonCreated(
                    reply.result(), stream)));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdRequestsResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostItemsByItemIdRequestsResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }

  }

  @Validate
  @Override
  public void putItemsByItemIdRequestsByRequestId(String requestId, String itemId, String lang,
      ItemRequest entity, Map<String, String>okapiHeaders,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put(ITEM_ID, itemId);
      q.put("_id", requestId);
      System.out.println("sending... putItemsByItemIdRequestsByRequestId");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner())
            .update(Consts.REQUEST_COLLECTION, entity, q,
                reply -> {
                  if(reply.succeeded() && reply.result().getDocMatched() == 0){
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdRequestsByRequestIdResponse.
                      withPlainNotFound(itemId + " " + requestId)));
                  }
                  else{
                    try {
                      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdRequestsByRequestIdResponse
                          .withNoContent()));
                    } catch (Exception e) {
                      log.error(e);
                      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdRequestsByRequestIdResponse
                          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
                    } 
                  }
                });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutItemsByItemIdRequestsByRequestIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Override
  public void getItemsByItemIdRequests(String itemId, Status status, RequestType requestType,
      int offset, int limit, String lang, Map<String, String>okapiHeaders,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... getItemsByItemIdRequests");

    JsonObject q = new JsonObject();
    q.put(ITEM_ID, itemId);
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
                ItemRequests ir = new ItemRequests();
                List<ItemRequest> itemRequests = (List<ItemRequest>)reply.result();
                ir.setItemRequests(itemRequests);
                ir.setTotalRecords(itemRequests.size());
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdRequestsResponse.withJsonOK(ir)));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdRequestsResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdRequestsResponse
        .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }

  }

  @Validate
  @Override
  public void getItemsByItemIdRequestsByRequestId(String requestId, String itemId, String lang,
      Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... getItemsByItemIdRequestsByRequestId");
    try {
      JsonObject q = new JsonObject();
      q.put(ITEM_ID, itemId);
      q.put("_id", requestId);
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(ItemRequest.class.getName(), Consts.REQUEST_COLLECTION, q),
            reply -> {
              try {
                List<ItemRequest> ir = (List<ItemRequest>)reply.result();
                if (ir.isEmpty()) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdRequestsByRequestIdResponse
                      .withPlainNotFound("Request " + messages.getMessage(lang, "10008"))));
                  return;
                }
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdRequestsByRequestIdResponse
                  .withJsonOK(ir.get(0))));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdRequestsByRequestIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetItemsByItemIdRequestsByRequestIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }
  }

  @Validate
  @Override
  public void deleteItemsByItemIdRequestsByRequestId(String requestId, String itemId, String reason,
      String comment, String notify, String lang, Map<String, String>okapiHeaders, Handler<AsyncResult<Response>> asyncResultHandler,
      Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put(ITEM_ID, itemId);
      q.put("_id", requestId);
      System.out.println("sending... deleteItemsByItemIdRequestsByRequestId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).delete(Consts.REQUEST_COLLECTION, q,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteItemsByItemIdRequestsByRequestIdResponse
                    .withNoContent()));
              } catch (Exception e) {
                log.error(e);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteItemsByItemIdRequestsByRequestIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
              }
            });
      });
    } catch (Exception e) {
      log.error(e);
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteItemsByItemIdRequestsByRequestIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, MessageConsts.InternalServerError))));
    }

  }

}
