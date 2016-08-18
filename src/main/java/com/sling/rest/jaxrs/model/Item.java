
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
    "bib_id",
    "barcode",
    "location",
    "item_status",
    "material_type",
    "copy_id",
    "item_link",
    "shelf_location"
})
public class Item {

    @JsonProperty("_id")
    private String Id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("bib_id")
    @NotNull
    private String bibId;
    @JsonProperty("barcode")
    private String barcode;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("location")
    @Valid
    @NotNull
    private Location location;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_status")
    @Valid
    @NotNull
    private ItemStatus itemStatus;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("material_type")
    @Valid
    @NotNull
    private MaterialType materialType;
    @JsonProperty("copy_id")
    private String copyId;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("item_link")
    @NotNull
    private String itemLink;
    @JsonProperty("shelf_location")
    @Valid
    private ShelfLocation shelfLocation;
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

    public Item withId(String Id) {
        this.Id = Id;
        return this;
    }

    /**
     * 
     * (Required)
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
     * (Required)
     * 
     * @param bibId
     *     The bib_id
     */
    @JsonProperty("bib_id")
    public void setBibId(String bibId) {
        this.bibId = bibId;
    }

    public Item withBibId(String bibId) {
        this.bibId = bibId;
        return this;
    }

    /**
     * 
     * @return
     *     The barcode
     */
    @JsonProperty("barcode")
    public String getBarcode() {
        return barcode;
    }

    /**
     * 
     * @param barcode
     *     The barcode
     */
    @JsonProperty("barcode")
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Item withBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The location
     */
    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    /**
     * 
     * (Required)
     * 
     * @param location
     *     The location
     */
    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    public Item withLocation(Location location) {
        this.location = location;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The itemStatus
     */
    @JsonProperty("item_status")
    public ItemStatus getItemStatus() {
        return itemStatus;
    }

    /**
     * 
     * (Required)
     * 
     * @param itemStatus
     *     The item_status
     */
    @JsonProperty("item_status")
    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Item withItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The materialType
     */
    @JsonProperty("material_type")
    public MaterialType getMaterialType() {
        return materialType;
    }

    /**
     * 
     * (Required)
     * 
     * @param materialType
     *     The material_type
     */
    @JsonProperty("material_type")
    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public Item withMaterialType(MaterialType materialType) {
        this.materialType = materialType;
        return this;
    }

    /**
     * 
     * @return
     *     The copyId
     */
    @JsonProperty("copy_id")
    public String getCopyId() {
        return copyId;
    }

    /**
     * 
     * @param copyId
     *     The copy_id
     */
    @JsonProperty("copy_id")
    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }

    public Item withCopyId(String copyId) {
        this.copyId = copyId;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     * @return
     *     The itemLink
     */
    @JsonProperty("item_link")
    public String getItemLink() {
        return itemLink;
    }

    /**
     * 
     * (Required)
     * 
     * @param itemLink
     *     The item_link
     */
    @JsonProperty("item_link")
    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public Item withItemLink(String itemLink) {
        this.itemLink = itemLink;
        return this;
    }

    /**
     * 
     * @return
     *     The shelfLocation
     */
    @JsonProperty("shelf_location")
    public ShelfLocation getShelfLocation() {
        return shelfLocation;
    }

    /**
     * 
     * @param shelfLocation
     *     The shelf_location
     */
    @JsonProperty("shelf_location")
    public void setShelfLocation(ShelfLocation shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public Item withShelfLocation(ShelfLocation shelfLocation) {
        this.shelfLocation = shelfLocation;
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

    public Item withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
