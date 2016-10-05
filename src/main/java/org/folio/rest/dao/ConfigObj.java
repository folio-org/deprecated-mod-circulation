package org.folio.rest.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ConfigObj {

  private String  module     = null;
  private String  name       = null;
  private String  updateBy   = null;
  private String  updateDate = null;
  private String  instId     = null;
  private String  libId      = null;
  private String  code;
  private String  description;
  private boolean iSDefault;
  private boolean enabled;
  private String  value;
  private int     succeeded;
  private int     errors;

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }

  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

  public String getInstId() {
    return instId;
  }

  public void setInstId(String instId) {
    this.instId = instId;
  }

  public String getLibId() {
    return libId;
  }

  public void setLibId(String libId) {
    this.libId = libId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isiSDefault() {
    return iSDefault;
  }

  public void setiSDefault(boolean iSDefault) {
    this.iSDefault = iSDefault;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public int getSucceeded() {
    return succeeded;
  }

  public void setSucceeded(int succeeded) {
    this.succeeded = succeeded;
  }

  public int getErrors() {
    return errors;
  }

  public void setErrors(int errors) {
    this.errors = errors;
  }

}
