package org.folio.rest.impl;

import io.vertx.core.Context;
import io.vertx.core.Vertx;

import org.folio.rest.resource.interfaces.PeriodicAPI;

public class ScanOverDue implements PeriodicAPI {

  long interval = 1000*60*60*4; //every four hours
  
  @Override
  public long runEvery() {
    return interval;
  }

  
  @Override
  public void run(Vertx vertx, Context context) {
    //TODO query loans placeholder
  }

}
