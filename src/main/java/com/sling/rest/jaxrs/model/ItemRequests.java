
package com.sling.rest.jaxrs.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
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
    "item_requests",
    "total_records"
})
public class ItemRequests {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_requests")
    @Valid
    @NotNull
    private List<ItemRequest> itemRequests = new ArrayList<ItemRequest>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("total_records")
    @NotNull
    private Integer totalRecords;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     * @return
     *     The itemRequests
     */
    @JsonProperty("item_requests")
    public List<ItemRequest> getItemRequests() {
        return itemRequests;
    }

    /**
     * 
     * (Required)
     * 
     * @param itemRequests
     *     The item_requests
     */
    @JsonProperty("item_requests")
    public void setItemRequests(List<ItemRequest> itemRequests) {
        this.itemRequests = itemRequests;
    }

    public ItemRequests withItemRequests(List<ItemRequest> itemRequests) {
        this.itemRequests = itemRequests;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The totalRecords
     */
    @JsonProperty("total_records")
    public Integer getTotalRecords() {
        return totalRecords;
    }

    /**
     * 
     * (Required)
     * 
     * @param totalRecords
     *     The total_records
     */
    @JsonProperty("total_records")
    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public ItemRequests withTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
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

    public ItemRequests withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
