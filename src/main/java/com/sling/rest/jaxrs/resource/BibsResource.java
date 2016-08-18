
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
import com.sling.rest.jaxrs.model.Bib;
import com.sling.rest.jaxrs.model.Bibs;
import com.sling.rest.jaxrs.model.Item;
import com.sling.rest.jaxrs.model.ItemRequest;
import com.sling.rest.jaxrs.model.ItemRequests;
import com.sling.rest.jaxrs.model.Items;
import io.vertx.core.Context;


/**
 * Collection of bib items.
 * 
 */
@Path("bibs")
public interface BibsResource {


    /**
     * Retrieve a list of bib items.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param view
     *     displays a [brief] or [full] view of the returned entities - for example: show brief view of the entities
     *     
     * @param offset
     *     Skip over a number of elements by specifying an offset value for the query e.g. 0
     * @param query
     *     JSON array [{"field1","value1","operator1"},{"field2","value2","operator2"},...,{"fieldN","valueN","operatorN"}] with valid searchable fields: for example fieldA
     *      e.g. ["bib_data.bib_view.Title", "Of Mice And Men", "="]
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
    void getBibs(
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("query")
        String query,
        @QueryParam("orderBy")
        String orderBy,
        @QueryParam("order")
        @DefaultValue("desc")
        BibsResource.Order order,
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
     * Create a new bib item.
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
     *       "bib_view": {
     *         "Title": "Of Mice And Men",
     *         "Author": "J. Stienbeck",
     *         "publication_date": "1413879432450",
     *         "desc": "description"
     *       }
     *     }
     */
    @POST
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postBibs(
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Bib entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve bib item with given {bibId}
     * 
     * 
     * @param bibId
     *     
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param view
     *     displays a [brief] or [full] view of the returned entities - for example: show [brief] or [full] view of the entities
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
    @Path("{bibId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getBibsByBibId(
        @PathParam("bibId")
        @NotNull
        String bibId,
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
     * Delete bib item with given {bibId}
     * 
     * 
     * @param bibId
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
     */
    @DELETE
    @Path("{bibId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deleteBibsByBibId(
        @PathParam("bibId")
        @NotNull
        String bibId,
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
     * Update bib item with given {bibId}
     * 
     * 
     * @param bibId
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
     *       "_id": "23344156380001021",
     *       "bib_view": {
     *         "Title": "Of Mice And Men",
     *         "Author": "J. Stienbeck",
     *         "publication_date": "1413879432450",
     *         "desc": "description"
     *       }
     *     }
     */
    @PUT
    @Path("{bibId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putBibsByBibId(
        @PathParam("bibId")
        @NotNull
        String bibId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Bib entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve a list of request items.
     * 
     * @param bibId
     *     
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
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
    @Path("{bibId}/requests")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getBibsByBibIdRequests(
        @PathParam("bibId")
        @NotNull
        String bibId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("status")
        @DefaultValue("active")
        @NotNull
        BibsResource.Status status,
        @QueryParam("request_type")
        @DefaultValue("ALL")
        BibsResource.RequestType requestType,
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
     * Create a new request item.
     * 
     * @param bibId
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
    @POST
    @Path("{bibId}/requests")
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postBibsByBibIdRequests(
        @PathParam("bibId")
        @NotNull
        String bibId,
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
     * @param bibId
     *     
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
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
    @Path("{bibId}/requests/{requestId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getBibsByBibIdRequestsByRequestId(
        @PathParam("requestId")
        @NotNull
        String requestId,
        @PathParam("bibId")
        @NotNull
        String bibId,
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
     * Delete request item with given {requestId}
     * 
     * 
     * @param bibId
     *     
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
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
    @DELETE
    @Path("{bibId}/requests/{requestId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deleteBibsByBibIdRequestsByRequestId(
        @PathParam("requestId")
        @NotNull
        String requestId,
        @PathParam("bibId")
        @NotNull
        String bibId,
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
     * Update request item with given {requestId}
     * 
     * 
     * @param bibId
     *     
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
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
    @Path("{bibId}/requests/{requestId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putBibsByBibIdRequestsByRequestId(
        @PathParam("requestId")
        @NotNull
        String requestId,
        @PathParam("bibId")
        @NotNull
        String bibId,
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
     * Retrieve a list of item items.
     * 
     * @param bibId
     *     
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
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
     */
    @GET
    @Path("{bibId}/items")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getBibsByBibIdItems(
        @PathParam("bibId")
        @NotNull
        String bibId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
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
     * Create a new item item.
     * 
     * @param bibId
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
    @Path("{bibId}/items")
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postBibsByBibIdItems(
        @PathParam("bibId")
        @NotNull
        String bibId,
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
     * @param bibId
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
    @Path("{bibId}/items/{itemId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getBibsByBibIdItemsByItemId(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @PathParam("bibId")
        @NotNull
        String bibId,
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
     * @param bibId
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
    @Path("{bibId}/items/{itemId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deleteBibsByBibIdItemsByItemId(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @PathParam("bibId")
        @NotNull
        String bibId,
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
     * @param bibId
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
    @Path("{bibId}/items/{itemId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putBibsByBibIdItemsByItemId(
        @PathParam("itemId")
        @NotNull
        String itemId,
        @PathParam("bibId")
        @NotNull
        String bibId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Item entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    public class DeleteBibsByBibIdItemsByItemIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeleteBibsByBibIdItemsByItemIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item deleted successfully
         * 
         */
        public static BibsResource.DeleteBibsByBibIdItemsByItemIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new BibsResource.DeleteBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "item not found"
         * 
         * 
         * @param entity
         *     "item not found"
         *     
         */
        public static BibsResource.DeleteBibsByBibIdItemsByItemIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete item -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete item -- constraint violation"
         *     
         */
        public static BibsResource.DeleteBibsByBibIdItemsByItemIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static BibsResource.DeleteBibsByBibIdItemsByItemIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.DeleteBibsByBibIdItemsByItemIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.DeleteBibsByBibIdItemsByItemIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

    }

    public class DeleteBibsByBibIdRequestsByRequestIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeleteBibsByBibIdRequestsByRequestIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item deleted successfully
         * 
         */
        public static BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "request not found"
         * 
         * 
         * @param entity
         *     "request not found"
         *     
         */
        public static BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete request -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete request -- constraint violation"
         *     
         */
        public static BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

    }

    public class DeleteBibsByBibIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeleteBibsByBibIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Unable to delete bib. Item is linked to this bib
         * 
         */
        public static BibsResource.DeleteBibsByBibIdResponse withConflict() {
            Response.ResponseBuilder responseBuilder = Response.status(409);
            return new BibsResource.DeleteBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Item deleted successfully
         * 
         */
        public static BibsResource.DeleteBibsByBibIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new BibsResource.DeleteBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "bib not found"
         * 
         * 
         * @param entity
         *     "bib not found"
         *     
         */
        public static BibsResource.DeleteBibsByBibIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete bib -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete bib -- constraint violation"
         *     
         */
        public static BibsResource.DeleteBibsByBibIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static BibsResource.DeleteBibsByBibIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.DeleteBibsByBibIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.DeleteBibsByBibIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.DeleteBibsByBibIdResponse(responseBuilder.build());
        }

    }

    public class GetBibsByBibIdItemsByItemIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetBibsByBibIdItemsByItemIdResponse(Response delegate) {
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
        public static BibsResource.GetBibsByBibIdItemsByItemIdResponse withJsonOK(Item entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "item not found"
         * 
         * 
         * @param entity
         *     "item not found"
         *     
         */
        public static BibsResource.GetBibsByBibIdItemsByItemIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static BibsResource.GetBibsByBibIdItemsByItemIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdItemsByItemIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdItemsByItemIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

    }

    public class GetBibsByBibIdItemsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetBibsByBibIdItemsResponse(Response delegate) {
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
        public static BibsResource.GetBibsByBibIdItemsResponse withJsonOK(Items entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list items -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list items -- malformed parameter 'query', syntax error at column 6
         */
        public static BibsResource.GetBibsByBibIdItemsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static BibsResource.GetBibsByBibIdItemsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdItemsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdItemsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdItemsResponse(responseBuilder.build());
        }

    }

    public class GetBibsByBibIdRequestsByRequestIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetBibsByBibIdRequestsByRequestIdResponse(Response delegate) {
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
        public static BibsResource.GetBibsByBibIdRequestsByRequestIdResponse withJsonOK(ItemRequest entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "request not found"
         * 
         * 
         * @param entity
         *     "request not found"
         *     
         */
        public static BibsResource.GetBibsByBibIdRequestsByRequestIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static BibsResource.GetBibsByBibIdRequestsByRequestIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdRequestsByRequestIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdRequestsByRequestIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

    }

    public class GetBibsByBibIdRequestsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetBibsByBibIdRequestsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a list of request items e.g. {
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
        public static BibsResource.GetBibsByBibIdRequestsResponse withJsonOK(ItemRequests entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list requests -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list requests -- malformed parameter 'query', syntax error at column 6
         */
        public static BibsResource.GetBibsByBibIdRequestsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static BibsResource.GetBibsByBibIdRequestsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdRequestsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdRequestsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdRequestsResponse(responseBuilder.build());
        }

    }

    public class GetBibsByBibIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetBibsByBibIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns item with a given ID e.g. {
         *   "_id": "23344156380001021",
         *   "bib_view": {
         *     "Title": "Of Mice And Men",
         *     "Author": "J. Stienbeck",
         *     "publication_date": "1413879432450",
         *     "desc": "description"
         *   }
         * }
         * 
         * @param entity
         *     {
         *       "_id": "23344156380001021",
         *       "bib_view": {
         *         "Title": "Of Mice And Men",
         *         "Author": "J. Stienbeck",
         *         "publication_date": "1413879432450",
         *         "desc": "description"
         *       }
         *     }
         */
        public static BibsResource.GetBibsByBibIdResponse withJsonOK(Bib entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "bib not found"
         * 
         * 
         * @param entity
         *     "bib not found"
         *     
         */
        public static BibsResource.GetBibsByBibIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static BibsResource.GetBibsByBibIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsByBibIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsByBibIdResponse(responseBuilder.build());
        }

    }

    public class GetBibsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetBibsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a list of bib items e.g. {
         *   "_id": "23344156380001021",
         *   "bib_view": {
         *     "Title": "Of Mice And Men",
         *     "Author": "J. Stienbeck",
         *     "publication_date": "1413879432450",
         *     "desc": "description"
         *   }
         * }
         * 
         * @param entity
         *     {
         *       "_id": "23344156380001021",
         *       "bib_view": {
         *         "Title": "Of Mice And Men",
         *         "Author": "J. Stienbeck",
         *         "publication_date": "1413879432450",
         *         "desc": "description"
         *       }
         *     }
         */
        public static BibsResource.GetBibsResponse withJsonOK(Bibs entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list bibs -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list bibs -- malformed parameter 'query', syntax error at column 6
         */
        public static BibsResource.GetBibsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static BibsResource.GetBibsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.GetBibsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.GetBibsResponse(responseBuilder.build());
        }

    }

    public enum Order {

        desc,
        asc;

    }

    public class PostBibsByBibIdItemsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostBibsByBibIdItemsResponse(Response delegate) {
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
        public static BibsResource.PostBibsByBibIdItemsResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdItemsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add item -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add item -- malformed JSON at 13:3"
         *     
         */
        public static BibsResource.PostBibsByBibIdItemsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdItemsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static BibsResource.PostBibsByBibIdItemsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdItemsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PostBibsByBibIdItemsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdItemsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PostBibsByBibIdItemsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdItemsResponse(responseBuilder.build());
        }

    }

    public class PostBibsByBibIdRequestsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostBibsByBibIdRequestsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a newly created item, with server-controlled fields like 'id' populated e.g. {
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
         * @param location
         *     URI to the created request item
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
        public static BibsResource.PostBibsByBibIdRequestsResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add request -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add request -- malformed JSON at 13:3"
         *     
         */
        public static BibsResource.PostBibsByBibIdRequestsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static BibsResource.PostBibsByBibIdRequestsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdRequestsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PostBibsByBibIdRequestsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PostBibsByBibIdRequestsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsByBibIdRequestsResponse(responseBuilder.build());
        }

    }

    public class PostBibsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostBibsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a newly created item, with server-controlled fields like 'id' populated e.g. {
         *   "bib_view": {
         *     "Title": "Of Mice And Men",
         *     "Author": "J. Stienbeck",
         *     "publication_date": "1413879432450",
         *     "desc": "description"
         *   }
         * }
         * 
         * @param location
         *     URI to the created bib item
         * @param entity
         *     {
         *       "bib_view": {
         *         "Title": "Of Mice And Men",
         *         "Author": "J. Stienbeck",
         *         "publication_date": "1413879432450",
         *         "desc": "description"
         *       }
         *     }
         */
        public static BibsResource.PostBibsResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add bib -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add bib -- malformed JSON at 13:3"
         *     
         */
        public static BibsResource.PostBibsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static BibsResource.PostBibsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PostBibsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PostBibsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PostBibsResponse(responseBuilder.build());
        }

    }

    public class PutBibsByBibIdItemsByItemIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutBibsByBibIdItemsByItemIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static BibsResource.PutBibsByBibIdItemsByItemIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new BibsResource.PutBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "item not found"
         * 
         * 
         * @param entity
         *     "item not found"
         *     
         */
        public static BibsResource.PutBibsByBibIdItemsByItemIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update item -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update item -- malformed JSON at 13:4"
         *     
         */
        public static BibsResource.PutBibsByBibIdItemsByItemIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static BibsResource.PutBibsByBibIdItemsByItemIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PutBibsByBibIdItemsByItemIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PutBibsByBibIdItemsByItemIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdItemsByItemIdResponse(responseBuilder.build());
        }

    }

    public class PutBibsByBibIdRequestsByRequestIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutBibsByBibIdRequestsByRequestIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static BibsResource.PutBibsByBibIdRequestsByRequestIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new BibsResource.PutBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "request not found"
         * 
         * 
         * @param entity
         *     "request not found"
         *     
         */
        public static BibsResource.PutBibsByBibIdRequestsByRequestIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update request -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update request -- malformed JSON at 13:4"
         *     
         */
        public static BibsResource.PutBibsByBibIdRequestsByRequestIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static BibsResource.PutBibsByBibIdRequestsByRequestIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PutBibsByBibIdRequestsByRequestIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PutBibsByBibIdRequestsByRequestIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdRequestsByRequestIdResponse(responseBuilder.build());
        }

    }

    public class PutBibsByBibIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutBibsByBibIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static BibsResource.PutBibsByBibIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new BibsResource.PutBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "bib not found"
         * 
         * 
         * @param entity
         *     "bib not found"
         *     
         */
        public static BibsResource.PutBibsByBibIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update bib -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update bib -- malformed JSON at 13:4"
         *     
         */
        public static BibsResource.PutBibsByBibIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static BibsResource.PutBibsByBibIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PutBibsByBibIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static BibsResource.PutBibsByBibIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new BibsResource.PutBibsByBibIdResponse(responseBuilder.build());
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
