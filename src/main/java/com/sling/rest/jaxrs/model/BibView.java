
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
    "Title",
    "Author",
    "publication_date",
    "desc"
})
public class BibView {

    @JsonProperty("Title")
    private String Title;
    @JsonProperty("Author")
    private String Author;
    @JsonProperty("publication_date")
    private String publicationDate;
    @JsonProperty("desc")
    private String desc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The Title
     */
    @JsonProperty("Title")
    public String getTitle() {
        return Title;
    }

    /**
     * 
     * @param Title
     *     The Title
     */
    @JsonProperty("Title")
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public BibView withTitle(String Title) {
        this.Title = Title;
        return this;
    }

    /**
     * 
     * @return
     *     The Author
     */
    @JsonProperty("Author")
    public String getAuthor() {
        return Author;
    }

    /**
     * 
     * @param Author
     *     The Author
     */
    @JsonProperty("Author")
    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public BibView withAuthor(String Author) {
        this.Author = Author;
        return this;
    }

    /**
     * 
     * @return
     *     The publicationDate
     */
    @JsonProperty("publication_date")
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * 
     * @param publicationDate
     *     The publication_date
     */
    @JsonProperty("publication_date")
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public BibView withPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    /**
     * 
     * @return
     *     The desc
     */
    @JsonProperty("desc")
    public String getDesc() {
        return desc;
    }

    /**
     * 
     * @param desc
     *     The desc
     */
    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BibView withDesc(String desc) {
        this.desc = desc;
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

    public BibView withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
