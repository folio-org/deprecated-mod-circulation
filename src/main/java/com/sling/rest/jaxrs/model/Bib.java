
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
    "bib_view"
})
public class Bib {

    @JsonProperty("_id")
    private String Id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("bib_view")
    @Valid
    @NotNull
    private BibView bibView;
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

    public Bib withId(String Id) {
        this.Id = Id;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The bibView
     */
    @JsonProperty("bib_view")
    public BibView getBibView() {
        return bibView;
    }

    /**
     * 
     * (Required)
     * 
     * @param bibView
     *     The bib_view
     */
    @JsonProperty("bib_view")
    public void setBibView(BibView bibView) {
        this.bibView = bibView;
    }

    public Bib withBibView(BibView bibView) {
        this.bibView = bibView;
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

    public Bib withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
