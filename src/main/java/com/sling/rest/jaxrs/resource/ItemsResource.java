
package com.sling.rest.jaxrs.resource;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import com.sling.rest.annotations.Validate;
import com.sling.rest.jaxrs.model.Fine;
import com.sling.rest.jaxrs.model.Fines;
import com.sling.rest.jaxrs.model.Item;
import com.sling.rest.jaxrs.model.ItemRequest;
import com.sling.rest.jaxrs.model.ItemRequests;
import com.sling.rest.jaxrs.model.Items;
import io.vertx.core.Context;


/**
 * Items services
 * 
 */
@Path("items")
public interface ItemsResource {


    /**
     * Return list of items
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param offset
     *     Skip over a number of elements by specifying an offset value for the query e.g. 0
     * @param query
     *     JSON array [{"field1","value1","operator1"},{"field2","value2","operator2"},...,{"fieldN","valueN","operatorN"}] with valid searchable fields: for example location
     *      e.g. ["item_data.location.value", "STACK", "="]
     *     
     * @param limit
     *     Limit the number of elements returned in the response e.g. 10
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param orderBy
     *     Order by field: field A, field B
     *     
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param order
     *     Order
     */
    @GET
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getItems(
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("query")
        String query,
        @QueryParam("orderBy")
        String orderBy,
        @QueryParam("order")
        @DefaultValue("desc")
        ItemsResource.Order order,
        @QueryParam("offset")
        @DefaultValue("0")
        @Min(0L)
        @Max(1000L)
        int offset,
        @QueryParam("limit")
        @DefaultValue("10")
        @Min(1L)
        @Max(100L)
        int limit,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Create an Item
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param entity
     *      e.g. {
     *     		"item_id": "2222222222",
     *       "barcode": "39031031697261",
     *       "location": {
     *         "value": "STACK",
     *         "desc": "Stacks (STACK)"
     *       },
     *       "item_status": {
     *         "value": "01",
     *         "desc": "ITEM_STATUS_MISSING"
     *       },
     *       "material_type": {
     *         "value": "BOOK",
     *         "desc": "Book",
     *         "icon": "book.png"
     *       },
     *       "shelf_location": {
     *         "classification_number": "1234567890",
     *         "shelf_listing_number": "12345678"
     *       },
     *       "copy_id": "1",
     *       "item_link": "/bibs/99100383909999/item/1234567890009999",
     *       "bib_id": "99100383909999"
     *     }
     */
    @POST
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postItems(
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Item entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve item item with given {itemId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param view
     *     displays a [brief] or [full] view of the returned entities - for example: show full view of the entities
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     */
    @GET
    @Path("{itemId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getItemsByItemId(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("view")
        @DefaultValue("brief")
        String view,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Delete item item with given {itemId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     */
    @DELETE
    @Path("{itemId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deleteItemsByItemId(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Update item item with given {itemId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param entity
     *      e.g. {
     *       "_id": "131231212",
     *       "barcode": "39031031697261",
     *       "location": {
     *         "value": "STACK",
     *         "desc": "Stacks (STACK)"
     *       },
     *       "item_status": {
     *         "value": "01",
     *         "desc": "ITEM_STATUS_MISSING"
     *       },
     *       "material_type": {
     *         "value": "BOOK",
     *         "desc": "Book",
     *         "icon": "book.png"
     *       },
     *       "shelf_location": {
     *         "classification_number": "1234567890",
     *         "shelf_listing_number": "12345678"
     *       },
     *       "copy_id": "1",
     *       "item_link": "/bibs/99100383909999/item/1234567890009999",
     *       "bib_id": "99100383909999"
     *     }
     */
    @PUT
    @Path("{itemId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putItemsByItemId(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Item entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve a list of fine items.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param offset
     *     Skip over a number of elements by specifying an offset value for the query e.g. 0
     * @param query
     *     JSON array [{"field1","value1","operator1"},{"field2","value2","operator2"},...,{"fieldN","valueN","operatorN"}] with valid searchable fields: for example lastname
     *      e.g. ["fine_data.fine_amount", 400, "="]
     *     
     * @param limit
     *     Limit the number of elements returned in the response e.g. 10
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param orderBy
     *     Order by field: field A, field B
     *     
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param order
     *     Order
     */
    @GET
    @Path("{itemId}/fines")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getItemsByItemIdFines(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("query")
        String query,
        @QueryParam("orderBy")
        String orderBy,
        @QueryParam("order")
        @DefaultValue("desc")
        ItemsResource.Order order,
        @QueryParam("offset")
        @DefaultValue("0")
        @Min(0L)
        @Max(1000L)
        int offset,
        @QueryParam("limit")
        @DefaultValue("10")
        @Min(1L)
        @Max(100L)
        int limit,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Create a new fine item.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param entity
     *      e.g. {
     *       "fine_amount": 400,
     *       "fine_outstanding": 100,
     *       "fine_date": 1413879432450,
     *       "fine_pay_in_full": false,
     *       "fine_pay_in_partial": true,
     *       "fine_note": "aaaaaa",
     *       "item_id": "12345",
     *       "patron_id": "1221212",
     *       "fine_forgiven": {
     *         "user": "the cool librarian",
     *         "amount": "none"
     *       }
     *     }
     */
    @POST
    @Path("{itemId}/fines")
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postItemsByItemIdFines(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Fine entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Applies the operation specified in the query parameter to a specific fine.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param op
     *     Type of operation:
     *      e.g. pay, waive, dispute
     * @param amount
     *     Amount to pay against all current fines on patron
     *      e.g. 10
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param paymentMethod
     *     Payment method used to make payment, ex. cash.
     *     
     * @param comment
     *     Short comment 
     *     
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param fineId
     *     
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     */
    @POST
    @Path("{itemId}/fines/{fineId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postItemsByItemIdFinesByFineId(
        @PathParam("fineId")
        @NotNull
        String fineId,
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("op")
        @DefaultValue("pay")
        @NotNull
        ItemsResource.Op op,
        @QueryParam("amount")
        @NotNull
        String amount,
        @QueryParam("payment_method")
        @DefaultValue("cash")
        @NotNull
        String paymentMethod,
        @QueryParam("comment")
        String comment,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve fine item with given {fineId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param fineId
     *     
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     */
    @GET
    @Path("{itemId}/fines/{fineId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getItemsByItemIdFinesByFineId(
        @PathParam("fineId")
        @NotNull
        String fineId,
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Delete fine item with given {fineId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param fineId
     *     
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     */
    @DELETE
    @Path("{itemId}/fines/{fineId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deleteItemsByItemIdFinesByFineId(
        @PathParam("fineId")
        @NotNull
        String fineId,
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Update fine item with given {fineId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param fineId
     *     
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param entity
     *      e.g. {
     *       "fine_amount": 400,
     *       "fine_outstanding": 100,
     *       "fine_date": 1413879432450,
     *       "_id": "950645520000121",
     *       "fine_pay_in_full": false,
     *       "fine_pay_in_partial": true,
     *       "fine_note": "aaaaaa",
     *       "item_id": "12345",
     *       "patron_id": "1221212",
     *       "fine_forgiven": {
     *         "user": "the cool librarian",
     *         "amount": "none"
     *       }
     *     }
     */
    @PUT
    @Path("{itemId}/fines/{fineId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putItemsByItemIdFinesByFineId(
        @PathParam("fineId")
        @NotNull
        String fineId,
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Fine entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve a list of request items.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param requestType
     *     Filter by a specific request type
     * @param offset
     *     Skip over a number of elements by specifying an offset value for the query e.g. 0
     * @param limit
     *     Limit the number of elements returned in the response e.g. 10
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param status
     *     Return requests that are either only active (default) or history of all requests (anonymized requests will not be returned) 
     *     
     */
    @GET
    @Path("{itemId}/requests")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getItemsByItemIdRequests(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("status")
        @DefaultValue("active")
        @NotNull
        ItemsResource.Status status,
        @QueryParam("request_type")
        @DefaultValue("ALL")
        ItemsResource.RequestType requestType,
        @QueryParam("offset")
        @DefaultValue("0")
        @Min(0L)
        @Max(1000L)
        int offset,
        @QueryParam("limit")
        @DefaultValue("10")
        @Min(1L)
        @Max(100L)
        int limit,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Create a request for a specific item id
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param entity
     *      e.g. {
     *       "request_status": "NOT_STARTED",
     *       "place_in_queue": 11,
     *       "request_date": "2015-12-12",
     *       "request_type": "HOLD|RECALL|MOVE|DIGITIZATION|BOOKING|ETC...",
     *       "_id": "00000000900111",
     *       "pickup_location": "MATHLIB",
     *       "item_id": "23344156380001021",
     *       "bib_id": "13344156380001021",
     *       "patron_id": "Me123"
     *     }
     */
    @POST
    @Path("{itemId}/requests")
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postItemsByItemIdRequests(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, ItemRequest entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve request item with given {requestId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param requestId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     */
    @GET
    @Path("{itemId}/requests/{requestId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getItemsByItemIdRequestsByRequestId(
        @PathParam("requestId")
        @NotNull
        String requestId,
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Cancel an item request
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param reason
     *     Reason code
     *     
     * @param requestId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param comment
     *     Short comment
     *     
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param notify
     *     Notify involved parties
     *     
     */
    @DELETE
    @Path("{itemId}/requests/{requestId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deleteItemsByItemIdRequestsByRequestId(
        @PathParam("requestId")
        @NotNull
        String requestId,
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("reason")
        @NotNull
        String reason,
        @QueryParam("comment")
        String comment,
        @QueryParam("notify")
        String notify,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Update request item with given {requestId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param itemId
     *     
     * @param requestId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param entity
     *      e.g. {
     *       "request_status": "NOT_STARTED",
     *       "place_in_queue": 11,
     *       "request_date": "2015-12-12",
     *       "request_type": "HOLD|RECALL|MOVE|DIGITIZATION|BOOKING|ETC...",
     *       "_id": "00000000900111",
     *       "pickup_location": "MATHLIB",
     *       "item_id": "23344156380001021",
     *       "bib_id": "13344156380001021",
     *       "patron_id": "Me123"
     *     }
     */
    @PUT
    @Path("{itemId}/requests/{requestId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putItemsByItemIdRequestsByRequestId(
        @PathParam("requestId")
        @NotNull
        String requestId,
        @PathParam("itemId")
        @NotNull
        String itemId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, ItemRequest entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    public class DeleteItemsByItemIdFinesByFineIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeleteItemsByItemIdFinesByFineIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item deleted successfully
         * 
         */
        public static ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "fine not found"
         * 
         * 
         * @param entity
         *     "fine not found"
         *     
         */
        public static ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete fine -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete fine -- constraint violation"
         *     
         */
        public static ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

    }

    public class DeleteItemsByItemIdRequestsByRequestIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeleteItemsByItemIdRequestsByRequestIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item deleted successfully
         * 
         */
        public static ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "request not found"
         * 
         * 
         * @param entity
         *     "request not found"
         *     
         */
        public static ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete request -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete request -- constraint violation"
         *     
         */
        public static ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

    }

    public class DeleteItemsByItemIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeleteItemsByItemIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item deleted successfully
         * 
         */
        public static ItemsResource.DeleteItemsByItemIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new ItemsResource.DeleteItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "item not found"
         * 
         * 
         * @param entity
         *     "item not found"
         *     
         */
        public static ItemsResource.DeleteItemsByItemIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete item -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete item -- constraint violation"
         *     
         */
        public static ItemsResource.DeleteItemsByItemIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static ItemsResource.DeleteItemsByItemIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.DeleteItemsByItemIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.DeleteItemsByItemIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.DeleteItemsByItemIdResponse(responseBuilder.build());
        }

    }

    public class GetItemsByItemIdFinesByFineIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetItemsByItemIdFinesByFineIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns item with a given ID e.g. {
         *   "fine_amount": 400,
         *   "fine_outstanding": 100,
         *   "fine_date": 1413879432450,
         *   "_id": "950645520000121",
         *   "fine_pay_in_full": false,
         *   "fine_pay_in_partial": true,
         *   "fine_note": "aaaaaa",
         *   "item_id": "12345",
         *   "patron_id": "1221212",
         *   "fine_forgiven": {
         *     "user": "the cool librarian",
         *     "amount": "none"
         *   }
         * }
         * 
         * @param entity
         *     {
         *       "fine_amount": 400,
         *       "fine_outstanding": 100,
         *       "fine_date": 1413879432450,
         *       "_id": "950645520000121",
         *       "fine_pay_in_full": false,
         *       "fine_pay_in_partial": true,
         *       "fine_note": "aaaaaa",
         *       "item_id": "12345",
         *       "patron_id": "1221212",
         *       "fine_forgiven": {
         *         "user": "the cool librarian",
         *         "amount": "none"
         *       }
         *     }
         */
        public static ItemsResource.GetItemsByItemIdFinesByFineIdResponse withJsonOK(Fine entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "fine not found"
         * 
         * 
         * @param entity
         *     "fine not found"
         *     
         */
        public static ItemsResource.GetItemsByItemIdFinesByFineIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static ItemsResource.GetItemsByItemIdFinesByFineIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdFinesByFineIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdFinesByFineIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

    }

    public class GetItemsByItemIdFinesResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetItemsByItemIdFinesResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a list of fine items e.g. {
         *   "fine_amount": 400,
         *   "fine_outstanding": 100,
         *   "fine_date": 1413879432450,
         *   "_id": "950645520000121",
         *   "fine_pay_in_full": false,
         *   "fine_pay_in_partial": true,
         *   "fine_note": "aaaaaa",
         *   "item_id": "12345",
         *   "patron_id": "1221212",
         *   "fine_forgiven": {
         *     "user": "the cool librarian",
         *     "amount": "none"
         *   }
         * }
         * 
         * @param entity
         *     {
         *       "fine_amount": 400,
         *       "fine_outstanding": 100,
         *       "fine_date": 1413879432450,
         *       "_id": "950645520000121",
         *       "fine_pay_in_full": false,
         *       "fine_pay_in_partial": true,
         *       "fine_note": "aaaaaa",
         *       "item_id": "12345",
         *       "patron_id": "1221212",
         *       "fine_forgiven": {
         *         "user": "the cool librarian",
         *         "amount": "none"
         *       }
         *     }
         */
        public static ItemsResource.GetItemsByItemIdFinesResponse withJsonOK(Fines entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list fines -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list fines -- malformed parameter 'query', syntax error at column 6
         */
        public static ItemsResource.GetItemsByItemIdFinesResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static ItemsResource.GetItemsByItemIdFinesResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdFinesResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdFinesResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdFinesResponse(responseBuilder.build());
        }

    }

    public class GetItemsByItemIdRequestsByRequestIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetItemsByItemIdRequestsByRequestIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns item with a given ID e.g. {
         *   "request_status": "NOT_STARTED",
         *   "place_in_queue": 11,
         *   "request_date": "2015-12-12",
         *   "request_type": "HOLD|RECALL|MOVE|DIGITIZATION|BOOKING|ETC...",
         *   "_id": "00000000900111",
         *   "pickup_location": "MATHLIB",
         *   "item_id": "23344156380001021",
         *   "bib_id": "13344156380001021",
         *   "patron_id": "Me123"
         * }
         * 
         * @param entity
         *     {
         *       "request_status": "NOT_STARTED",
         *       "place_in_queue": 11,
         *       "request_date": "2015-12-12",
         *       "request_type": "HOLD|RECALL|MOVE|DIGITIZATION|BOOKING|ETC...",
         *       "_id": "00000000900111",
         *       "pickup_location": "MATHLIB",
         *       "item_id": "23344156380001021",
         *       "bib_id": "13344156380001021",
         *       "patron_id": "Me123"
         *     }
         */
        public static ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse withJsonOK(ItemRequest entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "request not found"
         * 
         * 
         * @param entity
         *     "request not found"
         *     
         */
        public static ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

    }

    public class GetItemsByItemIdRequestsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetItemsByItemIdRequestsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a list of request items e.g. {
         *   "request_status": "NOT_STARTED",
         *   "place_in_queue": 11,
         *   "request_date": "2015-12-12",
         *   "request_type": "HOLD|RECALL|MOVE|DIGITIZATION|BOOKING|ETC...",
         *   "pickup_location": "MATHLIB",
         *   "item_id": "23344156380001021",
         *   "bib_id": "13344156380001021",
         *   "patron_id": "Me123"
         * }
         * 
         * @param entity
         *     {
         *       "request_status": "NOT_STARTED",
         *       "place_in_queue": 11,
         *       "request_date": "2015-12-12",
         *       "request_type": "HOLD|RECALL|MOVE|DIGITIZATION|BOOKING|ETC...",
         *       "pickup_location": "MATHLIB",
         *       "item_id": "23344156380001021",
         *       "bib_id": "13344156380001021",
         *       "patron_id": "Me123"
         *     }
         */
        public static ItemsResource.GetItemsByItemIdRequestsResponse withJsonOK(ItemRequests entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list requests -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list requests -- malformed parameter 'query', syntax error at column 6
         */
        public static ItemsResource.GetItemsByItemIdRequestsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static ItemsResource.GetItemsByItemIdRequestsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdRequestsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdRequestsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdRequestsResponse(responseBuilder.build());
        }

    }

    public class GetItemsByItemIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetItemsByItemIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns item with a given ID e.g. {
         *   "_id": "131231212",
         *   "barcode": "39031031697261",
         *   "location": {
         *     "value": "STACK",
         *     "desc": "Stacks (STACK)"
         *   },
         *   "item_status": {
         *     "value": "01",
         *     "desc": "ITEM_STATUS_MISSING"
         *   },
         *   "material_type": {
         *     "value": "BOOK",
         *     "desc": "Book",
         *     "icon": "book.png"
         *   },
         *   "shelf_location": {
         *     "classification_number": "1234567890",
         *     "shelf_listing_number": "12345678"
         *   },
         *   "copy_id": "1",
         *   "item_link": "/bibs/99100383909999/item/1234567890009999",
         *   "bib_id": "99100383909999"
         * }
         * 
         * @param entity
         *     {
         *       "_id": "131231212",
         *       "barcode": "39031031697261",
         *       "location": {
         *         "value": "STACK",
         *         "desc": "Stacks (STACK)"
         *       },
         *       "item_status": {
         *         "value": "01",
         *         "desc": "ITEM_STATUS_MISSING"
         *       },
         *       "material_type": {
         *         "value": "BOOK",
         *         "desc": "Book",
         *         "icon": "book.png"
         *       },
         *       "shelf_location": {
         *         "classification_number": "1234567890",
         *         "shelf_listing_number": "12345678"
         *       },
         *       "copy_id": "1",
         *       "item_link": "/bibs/99100383909999/item/1234567890009999",
         *       "bib_id": "99100383909999"
         *     }
         */
        public static ItemsResource.GetItemsByItemIdResponse withJsonOK(Item entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "item not found"
         * 
         * 
         * @param entity
         *     "item not found"
         *     
         */
        public static ItemsResource.GetItemsByItemIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static ItemsResource.GetItemsByItemIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsByItemIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsByItemIdResponse(responseBuilder.build());
        }

    }

    public class GetItemsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetItemsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a list of item items e.g. {
         *   "_id": "131231212",
         *   "barcode": "39031031697261",
         *   "location": {
         *     "value": "STACK",
         *     "desc": "Stacks (STACK)"
         *   },
         *   "item_status": {
         *     "value": "01",
         *     "desc": "ITEM_STATUS_MISSING"
         *   },
         *   "material_type": {
         *     "value": "BOOK",
         *     "desc": "Book",
         *     "icon": "book.png"
         *   },
         *   "shelf_location": {
         *     "classification_number": "1234567890",
         *     "shelf_listing_number": "12345678"
         *   },
         *   "copy_id": "1",
         *   "item_link": "/bibs/99100383909999/item/1234567890009999",
         *   "bib_id": "99100383909999"
         * }
         * 
         * @param entity
         *     {
         *       "_id": "131231212",
         *       "barcode": "39031031697261",
         *       "location": {
         *         "value": "STACK",
         *         "desc": "Stacks (STACK)"
         *       },
         *       "item_status": {
         *         "value": "01",
         *         "desc": "ITEM_STATUS_MISSING"
         *       },
         *       "material_type": {
         *         "value": "BOOK",
         *         "desc": "Book",
         *         "icon": "book.png"
         *       },
         *       "shelf_location": {
         *         "classification_number": "1234567890",
         *         "shelf_listing_number": "12345678"
         *       },
         *       "copy_id": "1",
         *       "item_link": "/bibs/99100383909999/item/1234567890009999",
         *       "bib_id": "99100383909999"
         *     }
         */
        public static ItemsResource.GetItemsResponse withJsonOK(Items entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list items -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list items -- malformed parameter 'query', syntax error at column 6
         */
        public static ItemsResource.GetItemsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static ItemsResource.GetItemsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.GetItemsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.GetItemsResponse(responseBuilder.build());
        }

    }

    public enum Op {

        pay,
        waive,
        dispute;

    }

    public enum Order {

        desc,
        asc;

    }

    public class PostItemsByItemIdFinesByFineIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostItemsByItemIdFinesByFineIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a newly created item, with server-controlled fields like 'id' populated e.g. {
         *   "fine_amount": 400,
         *   "fine_outstanding": 100,
         *   "fine_date": 1413879432450,
         *   "_id": "950645520000121",
         *   "fine_pay_in_full": false,
         *   "fine_pay_in_partial": true,
         *   "fine_note": "aaaaaa",
         *   "item_id": "12345",
         *   "patron_id": "1221212",
         *   "fine_forgiven": {
         *     "user": "the cool librarian",
         *     "amount": "none"
         *   }
         * }
         * 
         * @param location
         *     URI to the created fine item
         * @param entity
         *     {
         *       "fine_amount": 400,
         *       "fine_outstanding": 100,
         *       "fine_date": 1413879432450,
         *       "_id": "950645520000121",
         *       "fine_pay_in_full": false,
         *       "fine_pay_in_partial": true,
         *       "fine_note": "aaaaaa",
         *       "item_id": "12345",
         *       "patron_id": "1221212",
         *       "fine_forgiven": {
         *         "user": "the cool librarian",
         *         "amount": "none"
         *       }
         *     }
         */
        public static ItemsResource.PostItemsByItemIdFinesByFineIdResponse withJsonCreated(String location, Fine entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to pay fine -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to pay fine -- malformed JSON at 13:3"
         *     
         */
        public static ItemsResource.PostItemsByItemIdFinesByFineIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static ItemsResource.PostItemsByItemIdFinesByFineIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PostItemsByItemIdFinesByFineIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PostItemsByItemIdFinesByFineIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

    }

    public class PostItemsByItemIdFinesResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostItemsByItemIdFinesResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a newly created item, with server-controlled fields like 'id' populated e.g. {
         *   "fine_amount": 400,
         *   "fine_outstanding": 100,
         *   "fine_date": 1413879432450,
         *   "fine_pay_in_full": false,
         *   "fine_pay_in_partial": true,
         *   "fine_note": "aaaaaa",
         *   "item_id": "12345",
         *   "patron_id": "1221212",
         *   "fine_forgiven": {
         *     "user": "the cool librarian",
         *     "amount": "none"
         *   }
         * }
         * 
         * @param location
         *     URI to the created fine item
         * @param entity
         *     {
         *       "fine_amount": 400,
         *       "fine_outstanding": 100,
         *       "fine_date": 1413879432450,
         *       "fine_pay_in_full": false,
         *       "fine_pay_in_partial": true,
         *       "fine_note": "aaaaaa",
         *       "item_id": "12345",
         *       "patron_id": "1221212",
         *       "fine_forgiven": {
         *         "user": "the cool librarian",
         *         "amount": "none"
         *       }
         *     }
         */
        public static ItemsResource.PostItemsByItemIdFinesResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add fine -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add fine -- malformed JSON at 13:3"
         *     
         */
        public static ItemsResource.PostItemsByItemIdFinesResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static ItemsResource.PostItemsByItemIdFinesResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PostItemsByItemIdFinesResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PostItemsByItemIdFinesResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdFinesResponse(responseBuilder.build());
        }

    }

    public class PostItemsByItemIdRequestsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostItemsByItemIdRequestsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a newly created item, with server-controlled fields like 'id' populated e.g. {
         *   "request_status": "NOT_STARTED",
         *   "place_in_queue": 11,
         *   "request_date": "2015-12-12",
         *   "request_type": "HOLD|RECALL|MOVE|DIGITIZATION|BOOKING|ETC...",
         *   "_id": "00000000900111",
         *   "pickup_location": "MATHLIB",
         *   "item_id": "23344156380001021",
         *   "bib_id": "13344156380001021",
         *   "patron_id": "Me123"
         * }
         * 
         * @param location
         *     URI to the created request item
         * @param entity
         *     {
         *       "request_status": "NOT_STARTED",
         *       "place_in_queue": 11,
         *       "request_date": "2015-12-12",
         *       "request_type": "HOLD|RECALL|MOVE|DIGITIZATION|BOOKING|ETC...",
         *       "_id": "00000000900111",
         *       "pickup_location": "MATHLIB",
         *       "item_id": "23344156380001021",
         *       "bib_id": "13344156380001021",
         *       "patron_id": "Me123"
         *     }
         */
        public static ItemsResource.PostItemsByItemIdRequestsResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add request -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add request -- malformed JSON at 13:3"
         *     
         */
        public static ItemsResource.PostItemsByItemIdRequestsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static ItemsResource.PostItemsByItemIdRequestsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdRequestsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PostItemsByItemIdRequestsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PostItemsByItemIdRequestsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsByItemIdRequestsResponse(responseBuilder.build());
        }

    }

    public class PostItemsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostItemsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a newly created item, with server-controlled fields like 'id' populated e.g. {
         * 		"item_id": "2222222222",
         *   "barcode": "39031031697261",
         *   "location": {
         *     "value": "STACK",
         *     "desc": "Stacks (STACK)"
         *   },
         *   "item_status": {
         *     "value": "01",
         *     "desc": "ITEM_STATUS_MISSING"
         *   },
         *   "material_type": {
         *     "value": "BOOK",
         *     "desc": "Book",
         *     "icon": "book.png"
         *   },
         *   "shelf_location": {
         *     "classification_number": "1234567890",
         *     "shelf_listing_number": "12345678"
         *   },
         *   "copy_id": "1",
         *   "item_link": "/bibs/99100383909999/item/1234567890009999",
         *   "bib_id": "99100383909999"
         * }
         * 
         * @param location
         *     URI to the created item item
         * @param entity
         *     {
         *     		"item_id": "2222222222",
         *       "barcode": "39031031697261",
         *       "location": {
         *         "value": "STACK",
         *         "desc": "Stacks (STACK)"
         *       },
         *       "item_status": {
         *         "value": "01",
         *         "desc": "ITEM_STATUS_MISSING"
         *       },
         *       "material_type": {
         *         "value": "BOOK",
         *         "desc": "Book",
         *         "icon": "book.png"
         *       },
         *       "shelf_location": {
         *         "classification_number": "1234567890",
         *         "shelf_listing_number": "12345678"
         *       },
         *       "copy_id": "1",
         *       "item_link": "/bibs/99100383909999/item/1234567890009999",
         *       "bib_id": "99100383909999"
         *     }
         */
        public static ItemsResource.PostItemsResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add item -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add item -- malformed JSON at 13:3"
         *     
         */
        public static ItemsResource.PostItemsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static ItemsResource.PostItemsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PostItemsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PostItemsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PostItemsResponse(responseBuilder.build());
        }

    }

    public class PutItemsByItemIdFinesByFineIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutItemsByItemIdFinesByFineIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static ItemsResource.PutItemsByItemIdFinesByFineIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new ItemsResource.PutItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "fine not found"
         * 
         * 
         * @param entity
         *     "fine not found"
         *     
         */
        public static ItemsResource.PutItemsByItemIdFinesByFineIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update fine -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update fine -- malformed JSON at 13:4"
         *     
         */
        public static ItemsResource.PutItemsByItemIdFinesByFineIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static ItemsResource.PutItemsByItemIdFinesByFineIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PutItemsByItemIdFinesByFineIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PutItemsByItemIdFinesByFineIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdFinesByFineIdResponse(responseBuilder.build());
        }

    }

    public class PutItemsByItemIdRequestsByRequestIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutItemsByItemIdRequestsByRequestIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "request not found"
         * 
         * 
         * @param entity
         *     "request not found"
         *     
         */
        public static ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update request -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update request -- malformed JSON at 13:4"
         *     
         */
        public static ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdRequestsByRequestIdResponse(responseBuilder.build());
        }

    }

    public class PutItemsByItemIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutItemsByItemIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static ItemsResource.PutItemsByItemIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new ItemsResource.PutItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "item not found"
         * 
         * 
         * @param entity
         *     "item not found"
         *     
         */
        public static ItemsResource.PutItemsByItemIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update item -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update item -- malformed JSON at 13:4"
         *     
         */
        public static ItemsResource.PutItemsByItemIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static ItemsResource.PutItemsByItemIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PutItemsByItemIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static ItemsResource.PutItemsByItemIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new ItemsResource.PutItemsByItemIdResponse(responseBuilder.build());
        }

    }

    public enum RequestType {

        HOLD,
        DIGITIZATION,
        BOOKING,
        ALL;

    }

    public enum Status {

        active,
        history;

    }

}
