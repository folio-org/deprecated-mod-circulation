
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
    "circ_desk",
    "library",
    "patron_id",
    "item_barcode",
    "item_id",
    "due_date",
    "loan_status",
    "loan_date",
    "loan_fine",
    "title",
    "author",
    "description",
    "publication_year",
    "call_number",
    "renewable",
    "location_code",
    "item_policy"
})
public class Loan {

    @JsonProperty("_id")
    private String Id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("circ_desk")
    @Valid
    @NotNull
    private CircDesk circDesk;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("library")
    @Valid
    @NotNull
    private Library library;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("patron_id")
    @NotNull
    private String patronId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_barcode")
    @NotNull
    private String itemBarcode;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_id")
    @NotNull
    private String itemId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("due_date")
    @NotNull
    private Integer dueDate;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("loan_status")
    @NotNull
    private String loanStatus;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("loan_date")
    @NotNull
    private Integer loanDate;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("loan_fine")
    @NotNull
    private Integer loanFine;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("title")
    @NotNull
    private String title;
    @JsonProperty("author")
    private String author;
    @JsonProperty("description")
    private String description;
    @JsonProperty("publication_year")
    private Integer publicationYear;
    @JsonProperty("call_number")
    private String callNumber;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("renewable")
    @NotNull
    private Boolean renewable;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("location_code")
    @Valid
    @NotNull
    private LocationCode locationCode;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_policy")
    @Valid
    @NotNull
    private ItemPolicy itemPolicy;
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

    public Loan withId(String Id) {
        this.Id = Id;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The circDesk
     */
    @JsonProperty("circ_desk")
    public CircDesk getCircDesk() {
        return circDesk;
    }

    /**
     * 
     * (Required)
     * 
     * @param circDesk
     *     The circ_desk
     */
    @JsonProperty("circ_desk")
    public void setCircDesk(CircDesk circDesk) {
        this.circDesk = circDesk;
    }

    public Loan withCircDesk(CircDesk circDesk) {
        this.circDesk = circDesk;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The library
     */
    @JsonProperty("library")
    public Library getLibrary() {
        return library;
    }

    /**
     * 
     * (Required)
     * 
     * @param library
     *     The library
     */
    @JsonProperty("library")
    public void setLibrary(Library library) {
        this.library = library;
    }

    public Loan withLibrary(Library library) {
        this.library = library;
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

    public Loan withPatronId(String patronId) {
        this.patronId = patronId;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The itemBarcode
     */
    @JsonProperty("item_barcode")
    public String getItemBarcode() {
        return itemBarcode;
    }

    /**
     * 
     * (Required)
     * 
     * @param itemBarcode
     *     The item_barcode
     */
    @JsonProperty("item_barcode")
    public void setItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
    }

    public Loan withItemBarcode(String itemBarcode) {
        this.itemBarcode = itemBarcode;
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

    public Loan withItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The dueDate
     */
    @JsonProperty("due_date")
    public Integer getDueDate() {
        return dueDate;
    }

    /**
     * 
     * (Required)
     * 
     * @param dueDate
     *     The due_date
     */
    @JsonProperty("due_date")
    public void setDueDate(Integer dueDate) {
        this.dueDate = dueDate;
    }

    public Loan withDueDate(Integer dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The loanStatus
     */
    @JsonProperty("loan_status")
    public String getLoanStatus() {
        return loanStatus;
    }

    /**
     * 
     * (Required)
     * 
     * @param loanStatus
     *     The loan_status
     */
    @JsonProperty("loan_status")
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Loan withLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The loanDate
     */
    @JsonProperty("loan_date")
    public Integer getLoanDate() {
        return loanDate;
    }

    /**
     * 
     * (Required)
     * 
     * @param loanDate
     *     The loan_date
     */
    @JsonProperty("loan_date")
    public void setLoanDate(Integer loanDate) {
        this.loanDate = loanDate;
    }

    public Loan withLoanDate(Integer loanDate) {
        this.loanDate = loanDate;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The loanFine
     */
    @JsonProperty("loan_fine")
    public Integer getLoanFine() {
        return loanFine;
    }

    /**
     * 
     * (Required)
     * 
     * @param loanFine
     *     The loan_fine
     */
    @JsonProperty("loan_fine")
    public void setLoanFine(Integer loanFine) {
        this.loanFine = loanFine;
    }

    public Loan withLoanFine(Integer loanFine) {
        this.loanFine = loanFine;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * (Required)
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Loan withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 
     * @return
     *     The author
     */
    @JsonProperty("author")
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    @JsonProperty("author")
    public void setAuthor(String author) {
        this.author = author;
    }

    public Loan withAuthor(String author) {
        this.author = author;
        return this;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Loan withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 
     * @return
     *     The publicationYear
     */
    @JsonProperty("publication_year")
    public Integer getPublicationYear() {
        return publicationYear;
    }

    /**
     * 
     * @param publicationYear
     *     The publication_year
     */
    @JsonProperty("publication_year")
    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Loan withPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    /**
     * 
     * @return
     *     The callNumber
     */
    @JsonProperty("call_number")
    public String getCallNumber() {
        return callNumber;
    }

    /**
     * 
     * @param callNumber
     *     The call_number
     */
    @JsonProperty("call_number")
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }

    public Loan withCallNumber(String callNumber) {
        this.callNumber = callNumber;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The renewable
     */
    @JsonProperty("renewable")
    public Boolean getRenewable() {
        return renewable;
    }

    /**
     * 
     * (Required)
     * 
     * @param renewable
     *     The renewable
     */
    @JsonProperty("renewable")
    public void setRenewable(Boolean renewable) {
        this.renewable = renewable;
    }

    public Loan withRenewable(Boolean renewable) {
        this.renewable = renewable;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The locationCode
     */
    @JsonProperty("location_code")
    public LocationCode getLocationCode() {
        return locationCode;
    }

    /**
     * 
     * (Required)
     * 
     * @param locationCode
     *     The location_code
     */
    @JsonProperty("location_code")
    public void setLocationCode(LocationCode locationCode) {
        this.locationCode = locationCode;
    }

    public Loan withLocationCode(LocationCode locationCode) {
        this.locationCode = locationCode;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The itemPolicy
     */
    @JsonProperty("item_policy")
    public ItemPolicy getItemPolicy() {
        return itemPolicy;
    }

    /**
     * 
     * (Required)
     * 
     * @param itemPolicy
     *     The item_policy
     */
    @JsonProperty("item_policy")
    public void setItemPolicy(ItemPolicy itemPolicy) {
        this.itemPolicy = itemPolicy;
    }

    public Loan withItemPolicy(ItemPolicy itemPolicy) {
        this.itemPolicy = itemPolicy;
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

    public Loan withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
