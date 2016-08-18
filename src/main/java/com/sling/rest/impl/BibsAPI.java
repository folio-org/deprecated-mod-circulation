package com.sling.rest.impl;

import java.util.List;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import javax.ws.rs.core.Response;

import com.sling.rest.annotations.Validate;
import com.sling.rest.jaxrs.model.Bib;
import com.sling.rest.jaxrs.model.Bibs;
import com.sling.rest.jaxrs.model.Item;
import com.sling.rest.jaxrs.model.ItemRequest;
import com.sling.rest.jaxrs.model.ItemRequests;
import com.sling.rest.jaxrs.model.Items;
import com.sling.rest.jaxrs.resource.BibsResource;
import com.sling.rest.persist.MongoCRUD;
import com.sling.rest.resource.utils.Consts;
import com.sling.rest.resource.utils.OutStream;
import com.sling.rest.tools.Messages;

public class BibsAPI implements BibsResource {

  private final Messages            messages = Messages.getInstance();

  @Validate
  @Override
  public void getBibs(String authorization, String query, String orderBy, Order order, int offset, int limit, String view, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      System.out.println("sending... getBibs");
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Bib.class.getName(), Consts.BIB_COLLECTION, query, orderBy, order, offset,
            limit),
            reply -> {
              try {
                Bibs ps = new Bibs();
                // this is wasteful!!!
                List<Bib> bibs = (List<Bib>)reply.result();
                ps.setBibs(bibs);
                ps.setTotalRecords(bibs.size());
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsResponse.withJsonOK(ps)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsResponse.withPlainInternalServerError(messages
                    .getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsResponse.withPlainInternalServerError(messages.getMessage(lang,
          "10001"))));

    }
  }

  @Validate
  @Override
  public void postBibs(String authorization, String lang, Bib entity, Handler<AsyncResult<Response>> asyncResultHandler, Context context)
      throws Exception {

    System.out.println("sending... postBibs");
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).save(Consts.BIB_COLLECTION, entity,
            reply -> {
              try {
                Bib p = new Bib();
                p = entity;
                //p.setBibId(reply.result());
                OutStream stream = new OutStream();
                stream.setData(p);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostBibsResponse.withJsonCreated(reply.result(), stream)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostBibsResponse.withPlainInternalServerError(messages
                    .getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostBibsResponse.withPlainInternalServerError(messages.getMessage(
          lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void getBibsByBibId(String bibId, String authorization, String view, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    JsonObject q = new JsonObject();
    q.put("_id", bibId);
    System.out.println("sending... getBibsByBibId");
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Bib.class.getName(), Consts.BIB_COLLECTION, q),
            reply -> {
              try {
                List<Bib> bib = (List<Bib>)reply.result();
                if (bib.size() == 0) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdResponse.withPlainNotFound("Bib "
                      + messages.getMessage(lang, "10008"))));
                  return;
                }
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdResponse.withJsonOK(bib.get(0))));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdResponse.withPlainInternalServerError(messages
                    .getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdResponse.withPlainInternalServerError(messages
          .getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void deleteBibsByBibId(String bibId, String authorization, String lang, Handler<AsyncResult<Response>> asyncResultHandler,
      Context context) throws Exception {

    System.out.println("sending... deleteBibsByBibId");

    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).delete(Consts.BIB_COLLECTION, bibId,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteBibsByBibIdResponse.withNoContent()));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteBibsByBibIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteBibsByBibIdResponse.withPlainInternalServerError(messages
          .getMessage(lang, "10001"))));
    }
  }

  @Validate
  @Override
  public void putBibsByBibId(String bibId, String authorization, String lang, Bib entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {
    
    JsonObject q = new JsonObject();
    q.put("_id", bibId);
    System.out.println("sending... putBibsByBibId");

    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).update(Consts.BIB_COLLECTION, entity, q,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutBibsByBibIdResponse.withNoContent()));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutBibsByBibIdResponse.withPlainInternalServerError(messages
                    .getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutBibsByBibIdResponse.withPlainInternalServerError(messages
          .getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void getBibsByBibIdItems(String bibId, String authorization, int offset, int limit, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {
    System.out.println("sending... getBibsByBibIdItems");

    JsonObject q = new JsonObject();
    q.put("bib_id", bibId);

    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(Item.class.getName(), Consts.ITEM_COLLECTION, q, null, null, offset, limit),
            reply -> {
              try {

                Items itemList = new Items();
                List<Item> items = (List<Item>)reply.result();
                itemList.setItems(items);
                itemList.setTotalRecords(items.size());

                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdItemsResponse.withJsonOK(itemList)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdItemsResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdItemsResponse.withPlainInternalServerError(messages
          .getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void postBibsByBibIdItems(String bibId, String authorization, String lang, Item entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... postBibsByBibIdItems");
    try {
      entity.setBibId(bibId);
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).save(Consts.ITEM_COLLECTION, entity,
            reply -> {
              try {
                Item item = entity;
                //item.setItemId(reply.result());
                OutStream stream = new OutStream();
                stream.setData(item);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostBibsByBibIdItemsResponse.withJsonCreated(
                    reply.result(), stream)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostBibsByBibIdItemsResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostBibsByBibIdItemsResponse.withPlainInternalServerError(messages
          .getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void getBibsByBibIdItemsByItemId(String itemId, String bibId, String authorization, String view, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    // view not implemented

    System.out.println("sending... getBibsByBibIdItemsByItemId");

    JsonObject q = new JsonObject();
    q.put("bib_id", bibId);
    q.put("_id", itemId);
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner())
            .get( MongoCRUD.buildJson(Item.class.getName(), Consts.ITEM_COLLECTION, q),
                reply -> {
                  try {
                    List<Item> items = (List<Item>)reply.result();
                    if (items.size() == 0) {
                      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdItemsByItemIdResponse
                          .withPlainNotFound("Bib " + messages.getMessage(lang, "10008"))));
                      return;
                    }
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdItemsByItemIdResponse.withJsonOK(items
                        .get(0))));
                  } catch (Exception e) {
                    e.printStackTrace();
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdItemsByItemIdResponse
                        .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
                  }
                });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdItemsByItemIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void deleteBibsByBibIdItemsByItemId(String itemId, String bibId, String authorization, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    JsonObject q = new JsonObject();
    q.put("bib_id", bibId);
    q.put("_id", itemId);

    System.out.println("sending... deleteBibsByBibIdItemsByItemId");

    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).delete(Consts.ITEM_COLLECTION, q,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteBibsByBibIdItemsByItemIdResponse.withNoContent()));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteBibsByBibIdItemsByItemIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteBibsByBibIdItemsByItemIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
    }
  }

  @Validate
  @Override
  public void putBibsByBibIdItemsByItemId(String itemId, String bibId, String authorization, String lang, Item entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {
    JsonObject q = new JsonObject();
    q.put("bib_id", bibId);
    q.put("_id", itemId);
    System.out.println("sending... putBibsByBibIdItemsByItemId");

    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).update(Consts.ITEM_COLLECTION, entity, q,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutBibsByBibIdItemsByItemIdResponse.withNoContent()));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutBibsByBibIdItemsByItemIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutBibsByBibIdItemsByItemIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void postBibsByBibIdRequests(String bibId, String authorization, String lang, ItemRequest entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... postBibsByBibIdRequests");

    // bibId id currently in the json passed in the body

    try {

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).save(Consts.REQUEST_COLLECTION, entity,
            reply -> {
              try {
                ItemRequest ir = entity;
                //ir.setRequestId(reply.result());
                OutStream stream = new OutStream();
                stream.setData(ir);
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostBibsByBibIdRequestsResponse.withJsonCreated(
                    reply.result(), stream)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostBibsByBibIdRequestsResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PostBibsByBibIdRequestsResponse.withPlainInternalServerError(messages
          .getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void getBibsByBibIdRequestsByRequestId(String requestId, String bibId, String authorization, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... getBibsByBibIdRequestsByRequestId");
    try {
      JsonObject q = new JsonObject();
      q.put("bib_id", bibId);
      q.put("_id", requestId);
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(ItemRequest.class.getName(), Consts.REQUEST_COLLECTION, q),
            reply -> {
              try {
                List<ItemRequest> ir = (List<ItemRequest>)reply.result();
                if (ir.size() == 0) {
                  asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdRequestsByRequestIdResponse
                      .withPlainNotFound("Request " + messages.getMessage(lang, "10008"))));
                  return;
                }
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdRequestsByRequestIdResponse.withJsonOK(ir
                    .get(0))));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdRequestsByRequestIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdRequestsByRequestIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void deleteBibsByBibIdRequestsByRequestId(String requestId, String bibId, String authorization, String lang,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("bib_id", bibId);
      q.put("_id", requestId);

      System.out.println("sending... deleteBibsByBibIdRequestsByRequestId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner())
            .delete(Consts.REQUEST_COLLECTION, q,
                reply -> {
                  try {
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteBibsByBibIdRequestsByRequestIdResponse
                        .withNoContent()));
                  } catch (Exception e) {
                    e.printStackTrace();
                    asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteBibsByBibIdRequestsByRequestIdResponse
                        .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
                  }
                });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(DeleteBibsByBibIdRequestsByRequestIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void putBibsByBibIdRequestsByRequestId(String requestId, String bibId, String authorization, String lang, ItemRequest entity,
      Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    try {
      JsonObject q = new JsonObject();
      q.put("bib_id", bibId);
      q.put("_id", requestId);

      System.out.println("sending... putBibsByBibIdRequestsByRequestId");

      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).update(Consts.REQUEST_COLLECTION, entity, q,
            reply -> {
              try {
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutBibsByBibIdRequestsByRequestIdResponse.withNoContent()));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutBibsByBibIdRequestsByRequestIdResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(PutBibsByBibIdRequestsByRequestIdResponse
          .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
    }

  }

  @Validate
  @Override
  public void getBibsByBibIdRequests(String bibId, String authorization, Status status, RequestType requestType, int offset, int limit,
      String lang, Handler<AsyncResult<Response>> asyncResultHandler, Context context) throws Exception {

    System.out.println("sending... getBibsByBibIdRequests");
    JsonObject q = new JsonObject();
    q.put("bib_id", bibId);
    if (requestType != null) {
      q.put("request_type", requestType.toString());
    }
    if (status != null) {
      q.put("request_status", status.toString());
    }
    try {
      context.runOnContext(v -> {
        MongoCRUD.getInstance(context.owner()).get(
          MongoCRUD.buildJson(ItemRequest.class.getName(), Consts.REQUEST_COLLECTION, q, 
            null, null, offset, limit),
            reply -> {
              try {
                ItemRequests ir = new ItemRequests();
                List<ItemRequest> requests = (List<ItemRequest>)reply.result();
                ir.setItemRequests(requests);
                ir.setTotalRecords(requests.size());
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdRequestsResponse.withJsonOK(ir)));
              } catch (Exception e) {
                e.printStackTrace();
                asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdRequestsResponse
                    .withPlainInternalServerError(messages.getMessage(lang, "10001"))));
              }
            });
      });
    } catch (Exception e) {
      e.printStackTrace();
      asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetBibsByBibIdRequestsResponse.withPlainInternalServerError(messages
          .getMessage(lang, "10001"))));
    }
  }

}
