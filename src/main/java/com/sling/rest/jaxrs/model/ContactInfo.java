
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
    "patron_address_local",
    "patron_address_home",
    "patron_address_work",
    "patron_email",
    "patron_email_alternative",
    "patron_phone_cell",
    "patron_phone_home",
    "patron_phone_work",
    "patron_primary_contact_info"
})
public class ContactInfo {

    @JsonProperty("patron_address_local")
    @Valid
    private PatronAddressLocal patronAddressLocal;
    @JsonProperty("patron_address_home")
    @Valid
    private PatronAddressHome patronAddressHome;
    @JsonProperty("patron_address_work")
    @Valid
    private PatronAddressWork patronAddressWork;
    @JsonProperty("patron_email")
    private String patronEmail;
    @JsonProperty("patron_email_alternative")
    private String patronEmailAlternative;
    @JsonProperty("patron_phone_cell")
    private String patronPhoneCell;
    @JsonProperty("patron_phone_home")
    private String patronPhoneHome;
    @JsonProperty("patron_phone_work")
    private String patronPhoneWork;
    @JsonProperty("patron_primary_contact_info")
    private String patronPrimaryContactInfo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The patronAddressLocal
     */
    @JsonProperty("patron_address_local")
    public PatronAddressLocal getPatronAddressLocal() {
        return patronAddressLocal;
    }

    /**
     * 
     * @param patronAddressLocal
     *     The patron_address_local
     */
    @JsonProperty("patron_address_local")
    public void setPatronAddressLocal(PatronAddressLocal patronAddressLocal) {
        this.patronAddressLocal = patronAddressLocal;
    }

    public ContactInfo withPatronAddressLocal(PatronAddressLocal patronAddressLocal) {
        this.patronAddressLocal = patronAddressLocal;
        return this;
    }

    /**
     * 
     * @return
     *     The patronAddressHome
     */
    @JsonProperty("patron_address_home")
    public PatronAddressHome getPatronAddressHome() {
        return patronAddressHome;
    }

    /**
     * 
     * @param patronAddressHome
     *     The patron_address_home
     */
    @JsonProperty("patron_address_home")
    public void setPatronAddressHome(PatronAddressHome patronAddressHome) {
        this.patronAddressHome = patronAddressHome;
    }

    public ContactInfo withPatronAddressHome(PatronAddressHome patronAddressHome) {
        this.patronAddressHome = patronAddressHome;
        return this;
    }

    /**
     * 
     * @return
     *     The patronAddressWork
     */
    @JsonProperty("patron_address_work")
    public PatronAddressWork getPatronAddressWork() {
        return patronAddressWork;
    }

    /**
     * 
     * @param patronAddressWork
     *     The patron_address_work
     */
    @JsonProperty("patron_address_work")
    public void setPatronAddressWork(PatronAddressWork patronAddressWork) {
        this.patronAddressWork = patronAddressWork;
    }

    public ContactInfo withPatronAddressWork(PatronAddressWork patronAddressWork) {
        this.patronAddressWork = patronAddressWork;
        return this;
    }

    /**
     * 
     * @return
     *     The patronEmail
     */
    @JsonProperty("patron_email")
    public String getPatronEmail() {
        return patronEmail;
    }

    /**
     * 
     * @param patronEmail
     *     The patron_email
     */
    @JsonProperty("patron_email")
    public void setPatronEmail(String patronEmail) {
        this.patronEmail = patronEmail;
    }

    public ContactInfo withPatronEmail(String patronEmail) {
        this.patronEmail = patronEmail;
        return this;
    }

    /**
     * 
     * @return
     *     The patronEmailAlternative
     */
    @JsonProperty("patron_email_alternative")
    public String getPatronEmailAlternative() {
        return patronEmailAlternative;
    }

    /**
     * 
     * @param patronEmailAlternative
     *     The patron_email_alternative
     */
    @JsonProperty("patron_email_alternative")
    public void setPatronEmailAlternative(String patronEmailAlternative) {
        this.patronEmailAlternative = patronEmailAlternative;
    }

    public ContactInfo withPatronEmailAlternative(String patronEmailAlternative) {
        this.patronEmailAlternative = patronEmailAlternative;
        return this;
    }

    /**
     * 
     * @return
     *     The patronPhoneCell
     */
    @JsonProperty("patron_phone_cell")
    public String getPatronPhoneCell() {
        return patronPhoneCell;
    }

    /**
     * 
     * @param patronPhoneCell
     *     The patron_phone_cell
     */
    @JsonProperty("patron_phone_cell")
    public void setPatronPhoneCell(String patronPhoneCell) {
        this.patronPhoneCell = patronPhoneCell;
    }

    public ContactInfo withPatronPhoneCell(String patronPhoneCell) {
        this.patronPhoneCell = patronPhoneCell;
        return this;
    }

    /**
     * 
     * @return
     *     The patronPhoneHome
     */
    @JsonProperty("patron_phone_home")
    public String getPatronPhoneHome() {
        return patronPhoneHome;
    }

    /**
     * 
     * @param patronPhoneHome
     *     The patron_phone_home
     */
    @JsonProperty("patron_phone_home")
    public void setPatronPhoneHome(String patronPhoneHome) {
        this.patronPhoneHome = patronPhoneHome;
    }

    public ContactInfo withPatronPhoneHome(String patronPhoneHome) {
        this.patronPhoneHome = patronPhoneHome;
        return this;
    }

    /**
     * 
     * @return
     *     The patronPhoneWork
     */
    @JsonProperty("patron_phone_work")
    public String getPatronPhoneWork() {
        return patronPhoneWork;
    }

    /**
     * 
     * @param patronPhoneWork
     *     The patron_phone_work
     */
    @JsonProperty("patron_phone_work")
    public void setPatronPhoneWork(String patronPhoneWork) {
        this.patronPhoneWork = patronPhoneWork;
    }

    public ContactInfo withPatronPhoneWork(String patronPhoneWork) {
        this.patronPhoneWork = patronPhoneWork;
        return this;
    }

    /**
     * 
     * @return
     *     The patronPrimaryContactInfo
     */
    @JsonProperty("patron_primary_contact_info")
    public String getPatronPrimaryContactInfo() {
        return patronPrimaryContactInfo;
    }

    /**
     * 
     * @param patronPrimaryContactInfo
     *     The patron_primary_contact_info
     */
    @JsonProperty("patron_primary_contact_info")
    public void setPatronPrimaryContactInfo(String patronPrimaryContactInfo) {
        this.patronPrimaryContactInfo = patronPrimaryContactInfo;
    }

    public ContactInfo withPatronPrimaryContactInfo(String patronPrimaryContactInfo) {
        this.patronPrimaryContactInfo = patronPrimaryContactInfo;
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

    public ContactInfo withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
