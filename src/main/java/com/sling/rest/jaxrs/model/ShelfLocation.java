
package com.sling.rest.jaxrs.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "classification_number",
    "shelf_listing_number"
})
public class ShelfLocation {

    @JsonProperty("classification_number")
    private String classificationNumber;
    @JsonProperty("shelf_listing_number")
    private String shelfListingNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The classificationNumber
     */
    @JsonProperty("classification_number")
    public String getClassificationNumber() {
        return classificationNumber;
    }

    /**
     * 
     * @param classificationNumber
     *     The classification_number
     */
    @JsonProperty("classification_number")
    public void setClassificationNumber(String classificationNumber) {
        this.classificationNumber = classificationNumber;
    }

    public ShelfLocation withClassificationNumber(String classificationNumber) {
        this.classificationNumber = classificationNumber;
        return this;
    }

    /**
     * 
     * @return
     *     The shelfListingNumber
     */
    @JsonProperty("shelf_listing_number")
    public String getShelfListingNumber() {
        return shelfListingNumber;
    }

    /**
     * 
     * @param shelfListingNumber
     *     The shelf_listing_number
     */
    @JsonProperty("shelf_listing_number")
    public void setShelfListingNumber(String shelfListingNumber) {
        this.shelfListingNumber = shelfListingNumber;
    }

    public ShelfLocation withShelfListingNumber(String shelfListingNumber) {
        this.shelfListingNumber = shelfListingNumber;
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

    public ShelfLocation withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
