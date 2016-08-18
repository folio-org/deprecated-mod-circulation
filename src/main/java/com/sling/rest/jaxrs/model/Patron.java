
package com.sling.rest.jaxrs.model;

import java.util.HashMap;
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
    "_id",
    "status",
    "patron_name",
    "patron_barcode",
    "patron_local_id",
    "contact_info",
    "total_loans",
    "total_fines",
    "total_fines_paid",
    "patron_code"
})
public class Patron {

    @JsonProperty("_id")
    private String Id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("status")
    @NotNull
    private String status;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("patron_name")
    @NotNull
    private String patronName;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("patron_barcode")
    @NotNull
    private String patronBarcode;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("patron_local_id")
    @NotNull
    private String patronLocalId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("contact_info")
    @Valid
    @NotNull
    private ContactInfo contactInfo;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("total_loans")
    @NotNull
    private Integer totalLoans;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("total_fines")
    @NotNull
    private String totalFines;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("total_fines_paid")
    @NotNull
    private String totalFinesPaid;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("patron_code")
    @Valid
    @NotNull
    private PatronCode patronCode;
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

    public Patron withId(String Id) {
        this.Id = Id;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * (Required)
     * 
     * @param status
     *     The status
     */
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public Patron withStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The patronName
     */
    @JsonProperty("patron_name")
    public String getPatronName() {
        return patronName;
    }

    /**
     * 
     * (Required)
     * 
     * @param patronName
     *     The patron_name
     */
    @JsonProperty("patron_name")
    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public Patron withPatronName(String patronName) {
        this.patronName = patronName;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The patronBarcode
     */
    @JsonProperty("patron_barcode")
    public String getPatronBarcode() {
        return patronBarcode;
    }

    /**
     * 
     * (Required)
     * 
     * @param patronBarcode
     *     The patron_barcode
     */
    @JsonProperty("patron_barcode")
    public void setPatronBarcode(String patronBarcode) {
        this.patronBarcode = patronBarcode;
    }

    public Patron withPatronBarcode(String patronBarcode) {
        this.patronBarcode = patronBarcode;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The patronLocalId
     */
    @JsonProperty("patron_local_id")
    public String getPatronLocalId() {
        return patronLocalId;
    }

    /**
     * 
     * (Required)
     * 
     * @param patronLocalId
     *     The patron_local_id
     */
    @JsonProperty("patron_local_id")
    public void setPatronLocalId(String patronLocalId) {
        this.patronLocalId = patronLocalId;
    }

    public Patron withPatronLocalId(String patronLocalId) {
        this.patronLocalId = patronLocalId;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The contactInfo
     */
    @JsonProperty("contact_info")
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    /**
     * 
     * (Required)
     * 
     * @param contactInfo
     *     The contact_info
     */
    @JsonProperty("contact_info")
    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Patron withContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The totalLoans
     */
    @JsonProperty("total_loans")
    public Integer getTotalLoans() {
        return totalLoans;
    }

    /**
     * 
     * (Required)
     * 
     * @param totalLoans
     *     The total_loans
     */
    @JsonProperty("total_loans")
    public void setTotalLoans(Integer totalLoans) {
        this.totalLoans = totalLoans;
    }

    public Patron withTotalLoans(Integer totalLoans) {
        this.totalLoans = totalLoans;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The totalFines
     */
    @JsonProperty("total_fines")
    public String getTotalFines() {
        return totalFines;
    }

    /**
     * 
     * (Required)
     * 
     * @param totalFines
     *     The total_fines
     */
    @JsonProperty("total_fines")
    public void setTotalFines(String totalFines) {
        this.totalFines = totalFines;
    }

    public Patron withTotalFines(String totalFines) {
        this.totalFines = totalFines;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The totalFinesPaid
     */
    @JsonProperty("total_fines_paid")
    public String getTotalFinesPaid() {
        return totalFinesPaid;
    }

    /**
     * 
     * (Required)
     * 
     * @param totalFinesPaid
     *     The total_fines_paid
     */
    @JsonProperty("total_fines_paid")
    public void setTotalFinesPaid(String totalFinesPaid) {
        this.totalFinesPaid = totalFinesPaid;
    }

    public Patron withTotalFinesPaid(String totalFinesPaid) {
        this.totalFinesPaid = totalFinesPaid;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The patronCode
     */
    @JsonProperty("patron_code")
    public PatronCode getPatronCode() {
        return patronCode;
    }

    /**
     * 
     * (Required)
     * 
     * @param patronCode
     *     The patron_code
     */
    @JsonProperty("patron_code")
    public void setPatronCode(PatronCode patronCode) {
        this.patronCode = patronCode;
    }

    public Patron withPatronCode(PatronCode patronCode) {
        this.patronCode = patronCode;
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

    public Patron withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
