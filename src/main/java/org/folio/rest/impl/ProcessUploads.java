package org.folio.rest.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;

import org.folio.rest.RestVerticle;
import org.folio.rest.resource.interfaces.InitAPI;


public class ProcessUploads implements InitAPI {
  
  @Override
  public void init(Vertx vertx, Context context, Handler<AsyncResult<Boolean>> handler) {

    try{
      MessageConsumer<Object> consumer = vertx.eventBus().consumer("circ.uploaded.files");
      consumer.handler(message -> {
        System.out.println("I have received a message: " + message.body());
        message.reply("how interesting!");
      });
      handler.handle(io.vertx.core.Future.succeededFuture(true));
    }
    catch(Exception e){
      handler.handle(io.vertx.core.Future.failedFuture(e.getMessage()));
    }
  }

}
