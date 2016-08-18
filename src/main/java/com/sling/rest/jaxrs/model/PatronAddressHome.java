
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
    "line1",
    "line2",
    "city",
    "state_province",
    "postal_code",
    "address_note",
    "start_date"
})
public class PatronAddressHome {

    @JsonProperty("line1")
    private String line1;
    @JsonProperty("line2")
    private String line2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state_province")
    private String stateProvince;
    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("address_note")
    private String addressNote;
    @JsonProperty("start_date")
    private String startDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The line1
     */
    @JsonProperty("line1")
    public String getLine1() {
        return line1;
    }

    /**
     * 
     * @param line1
     *     The line1
     */
    @JsonProperty("line1")
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public PatronAddressHome withLine1(String line1) {
        this.line1 = line1;
        return this;
    }

    /**
     * 
     * @return
     *     The line2
     */
    @JsonProperty("line2")
    public String getLine2() {
        return line2;
    }

    /**
     * 
     * @param line2
     *     The line2
     */
    @JsonProperty("line2")
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public PatronAddressHome withLine2(String line2) {
        this.line2 = line2;
        return this;
    }

    /**
     * 
     * @return
     *     The city
     */
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    public PatronAddressHome withCity(String city) {
        this.city = city;
        return this;
    }

    /**
     * 
     * @return
     *     The stateProvince
     */
    @JsonProperty("state_province")
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * 
     * @param stateProvince
     *     The state_province
     */
    @JsonProperty("state_province")
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public PatronAddressHome withStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
        return this;
    }

    /**
     * 
     * @return
     *     The postalCode
     */
    @JsonProperty("postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 
     * @param postalCode
     *     The postal_code
     */
    @JsonProperty("postal_code")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public PatronAddressHome withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * 
     * @return
     *     The addressNote
     */
    @JsonProperty("address_note")
    public String getAddressNote() {
        return addressNote;
    }

    /**
     * 
     * @param addressNote
     *     The address_note
     */
    @JsonProperty("address_note")
    public void setAddressNote(String addressNote) {
        this.addressNote = addressNote;
    }

    public PatronAddressHome withAddressNote(String addressNote) {
        this.addressNote = addressNote;
        return this;
    }

    /**
     * 
     * @return
     *     The startDate
     */
    @JsonProperty("start_date")
    public String getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate
     *     The start_date
     */
    @JsonProperty("start_date")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public PatronAddressHome withStartDate(String startDate) {
        this.startDate = startDate;
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

    public PatronAddressHome withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
