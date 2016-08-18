
package com.sling.rest.jaxrs.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "_id",
    "request_status",
    "place_in_queue",
    "request_date",
    "request_type",
    "pickup_location",
    "item_id",
    "bib_id",
    "patron_id"
})
public class ItemRequest {

    @JsonProperty("_id")
    private String Id;
    @JsonProperty("request_status")
    private String requestStatus;
    @JsonProperty("place_in_queue")
    private Integer placeInQueue;
    @JsonProperty("request_date")
    private String requestDate;
    @JsonProperty("request_type")
    private String requestType;
    @JsonProperty("pickup_location")
    private String pickupLocation;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_id")
    @NotNull
    private String itemId;
    @JsonProperty("bib_id")
    private String bibId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("patron_id")
    @NotNull
    private String patronId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    public ItemRequest withId(String Id) {
        this.Id = Id;
        return this;
    }

    /**
     * 
     * @return
     *     The requestStatus
     */
    @JsonProperty("request_status")
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * 
     * @param requestStatus
     *     The request_status
     */
    @JsonProperty("request_status")
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public ItemRequest withRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    /**
     * 
     * @return
     *     The placeInQueue
     */
    @JsonProperty("place_in_queue")
    public Integer getPlaceInQueue() {
        return placeInQueue;
    }

    /**
     * 
     * @param placeInQueue
     *     The place_in_queue
     */
    @JsonProperty("place_in_queue")
    public void setPlaceInQueue(Integer placeInQueue) {
        this.placeInQueue = placeInQueue;
    }

    public ItemRequest withPlaceInQueue(Integer placeInQueue) {
        this.placeInQueue = placeInQueue;
        return this;
    }

    /**
     * 
     * @return
     *     The requestDate
     */
    @JsonProperty("request_date")
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * 
     * @param requestDate
     *     The request_date
     */
    @JsonProperty("request_date")
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public ItemRequest withRequestDate(String requestDate) {
        this.requestDate = requestDate;
        return this;
    }

    /**
     * 
     * @return
     *     The requestType
     */
    @JsonProperty("request_type")
    public String getRequestType() {
        return requestType;
    }

    /**
     * 
     * @param requestType
     *     The request_type
     */
    @JsonProperty("request_type")
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public ItemRequest withRequestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    /**
     * 
     * @return
     *     The pickupLocation
     */
    @JsonProperty("pickup_location")
    public String getPickupLocation() {
        return pickupLocation;
    }

    /**
     * 
     * @param pickupLocation
     *     The pickup_location
     */
    @JsonProperty("pickup_location")
    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public ItemRequest withPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
        return this;
    }

    /**
     * 
     * (Required)
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
     * (Required)
     * 
     * @param itemId
     *     The item_id
     */
    @JsonProperty("item_id")
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public ItemRequest withItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    /**
     * 
     * @return
     *     The bibId
     */
    @JsonProperty("bib_id")
    public String getBibId() {
        return bibId;
    }

    /**
     * 
     * @param bibId
     *     The bib_id
     */
    @JsonProperty("bib_id")
    public void setBibId(String bibId) {
        this.bibId = bibId;
    }

    public ItemRequest withBibId(String bibId) {
        this.bibId = bibId;
        return this;
    }

    /**
     * 
     * (Required)
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
     * (Required)
     * 
     * @param patronId
     *     The patron_id
     */
    @JsonProperty("patron_id")
    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public ItemRequest withPatronId(String patronId) {
        this.patronId = patronId;
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

    public ItemRequest withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
