
package com.sling.rest.jaxrs.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "fine_amount",
    "fine_outstanding",
    "fine_date",
    "_id",
    "item_id",
    "fine_pay_in_full",
    "fine_pay_in_partial",
    "fine_note",
    "patron_id",
    "fine_forgiven"
})
public class Fine {

    @JsonProperty("fine_amount")
    private Integer fineAmount;
    @JsonProperty("fine_outstanding")
    private Integer fineOutstanding;
    @JsonProperty("fine_date")
    private Double fineDate;
    @JsonProperty("_id")
    private String Id;
    @JsonProperty("item_id")
    private String itemId;
    @JsonProperty("fine_pay_in_full")
    private Boolean finePayInFull;
    @JsonProperty("fine_pay_in_partial")
    private Boolean finePayInPartial;
    @JsonProperty("fine_note")
    private String fineNote;
    @JsonProperty("patron_id")
    private String patronId;
    @JsonProperty("fine_forgiven")
    @Valid
    private FineForgiven fineForgiven;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The fineAmount
     */
    @JsonProperty("fine_amount")
    public Integer getFineAmount() {
        return fineAmount;
    }

    /**
     * 
     * @param fineAmount
     *     The fine_amount
     */
    @JsonProperty("fine_amount")
    public void setFineAmount(Integer fineAmount) {
        this.fineAmount = fineAmount;
    }

    public Fine withFineAmount(Integer fineAmount) {
        this.fineAmount = fineAmount;
        return this;
    }

    /**
     * 
     * @return
     *     The fineOutstanding
     */
    @JsonProperty("fine_outstanding")
    public Integer getFineOutstanding() {
        return fineOutstanding;
    }

    /**
     * 
     * @param fineOutstanding
     *     The fine_outstanding
     */
    @JsonProperty("fine_outstanding")
    public void setFineOutstanding(Integer fineOutstanding) {
        this.fineOutstanding = fineOutstanding;
    }

    public Fine withFineOutstanding(Integer fineOutstanding) {
        this.fineOutstanding = fineOutstanding;
        return this;
    }

    /**
     * 
     * @return
     *     The fineDate
     */
    @JsonProperty("fine_date")
    public Double getFineDate() {
        return fineDate;
    }

    /**
     * 
     * @param fineDate
     *     The fine_date
     */
    @JsonProperty("fine_date")
    public void setFineDate(Double fineDate) {
        this.fineDate = fineDate;
    }

    public Fine withFineDate(Double fineDate) {
        this.fineDate = fineDate;
        return this;
    }

    /**
     * 
     * @return
     *     The Id
     */
    @JsonProperty("_id")
    public String getId() {
        return Id;
    }

    /**
     * 
     * @param Id
     *     The _id
     */
    @JsonProperty("_id")
    public void setId(String Id) {
        this.Id = Id;
    }

    public Fine withId(String Id) {
        this.Id = Id;
        return this;
    }

    /**
     * 
     * @return
     *     The itemId
     */
    @JsonProperty("item_id")
    public String getItemId() {
        return itemId;
    }

    /**
     * 
     * @param itemId
     *     The item_id
     */
    @JsonProperty("item_id")
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Fine withItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    /**
     * 
     * @return
     *     The finePayInFull
     */
    @JsonProperty("fine_pay_in_full")
    public Boolean getFinePayInFull() {
        return finePayInFull;
    }

    /**
     * 
     * @param finePayInFull
     *     The fine_pay_in_full
     */
    @JsonProperty("fine_pay_in_full")
    public void setFinePayInFull(Boolean finePayInFull) {
        this.finePayInFull = finePayInFull;
    }

    public Fine withFinePayInFull(Boolean finePayInFull) {
        this.finePayInFull = finePayInFull;
        return this;
    }

    /**
     * 
     * @return
     *     The finePayInPartial
     */
    @JsonProperty("fine_pay_in_partial")
    public Boolean getFinePayInPartial() {
        return finePayInPartial;
    }

    /**
     * 
     * @param finePayInPartial
     *     The fine_pay_in_partial
     */
    @JsonProperty("fine_pay_in_partial")
    public void setFinePayInPartial(Boolean finePayInPartial) {
        this.finePayInPartial = finePayInPartial;
    }

    public Fine withFinePayInPartial(Boolean finePayInPartial) {
        this.finePayInPartial = finePayInPartial;
        return this;
    }

    /**
     * 
     * @return
     *     The fineNote
     */
    @JsonProperty("fine_note")
    public String getFineNote() {
        return fineNote;
    }

    /**
     * 
     * @param fineNote
     *     The fine_note
     */
    @JsonProperty("fine_note")
    public void setFineNote(String fineNote) {
        this.fineNote = fineNote;
    }

    public Fine withFineNote(String fineNote) {
        this.fineNote = fineNote;
        return this;
    }

    /**
     * 
     * @return
     *     The patronId
     */
    @JsonProperty("patron_id")
    public String getPatronId() {
        return patronId;
    }

    /**
     * 
     * @param patronId
     *     The patron_id
     */
    @JsonProperty("patron_id")
    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public Fine withPatronId(String patronId) {
        this.patronId = patronId;
        return this;
    }

    /**
     * 
     * @return
     *     The fineForgiven
     */
    @JsonProperty("fine_forgiven")
    public FineForgiven getFineForgiven() {
        return fineForgiven;
    }

    /**
     * 
     * @param fineForgiven
     *     The fine_forgiven
     */
    @JsonProperty("fine_forgiven")
    public void setFineForgiven(FineForgiven fineForgiven) {
        this.fineForgiven = fineForgiven;
    }

    public Fine withFineForgiven(FineForgiven fineForgiven) {
        this.fineForgiven = fineForgiven;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Fine withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
