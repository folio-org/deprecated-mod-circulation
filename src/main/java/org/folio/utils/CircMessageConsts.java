package org.folio.utils;

import org.folio.rest.tools.messages.MessageEnum;


public enum CircMessageConsts implements MessageEnum {

  OperationOnNullAmount("20002"),
  FinePaidTooMuch("20003"),
  NonRenewable("20004"),
  LoanPeriodError("20005");

  private String code;

  private CircMessageConsts(String code){
    this.code = code;
  }

  public String getCode(){
    return code;
  }

}

