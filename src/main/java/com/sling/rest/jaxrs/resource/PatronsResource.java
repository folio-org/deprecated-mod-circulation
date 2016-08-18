
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
import com.sling.rest.jaxrs.model.ItemRequest;
import com.sling.rest.jaxrs.model.ItemRequests;
import com.sling.rest.jaxrs.model.Loan;
import com.sling.rest.jaxrs.model.Loans;
import com.sling.rest.jaxrs.model.Patron;
import com.sling.rest.jaxrs.model.Patrons;
import io.vertx.core.Context;


/**
 * Collection of patron items.
 * 
 */
@Path("patrons")
public interface PatronsResource {


    /**
     * Retrieve a list of patron items.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param offset
     *     Skip over a number of elements by specifying an offset value for the query e.g. 0
     * @param query
     *     JSON array [{"field1","value1","operator1"},{"field2","value2","operator2"},...,{"fieldN","valueN","operatorN"}] with valid searchable fields: for example lastname
     *      e.g. ["patron.status", "ACTIVE", "="]
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
    void getPatrons(
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("query")
        String query,
        @QueryParam("orderBy")
        String orderBy,
        @QueryParam("order")
        @DefaultValue("desc")
        PatronsResource.Order order,
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
     * Create a new patron item.
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
     *     	"status": "ACTIVE",
     *     	"patron_name": "Smith,John",
     *     	"patron_barcode": "00007888",
     *     	"patron_local_id": "abcdefd",
     *     	"contact_info": {
     *     		"patron_address_local": {
     *     			"line1": "Main Street 1",
     *     			"line2": "Nice building near the corner",
     *     			"city": "London",
     *     			"state_province": "",
     *     			"postal_code": "",
     *     			"address_note": "",
     *     			"start_date": "2013-12-26Z"
     *     		},
     *     		"patron_address_home": {
     *     			"line1": "Main Street 1",
     *     			"line2": "Nice building near the corner",
     *     			"city": "London",
     *     			"state_province": "",
     *     			"postal_code": "",
     *     			"address_note": "",
     *     			"start_date": "2013-12-26Z"
     *     		},
     *     		"patron_address_work": {
     *     			"line1": "Main Street 1",
     *     			"line2": "Nice building near the corner",
     *     			"city": "London",
     *     			"state_province": "",
     *     			"postal_code": "",
     *     			"address_note": "",
     *     			"start_date": "2013-12-26Z"
     *     		},
     *     		"patron_email": "johns@mylib.org",
     *     		"patron_email_alternative": "johns@mylib.org",
     *     		"patron_phone_cell": "123456789",
     *     		"patron_phone_home": "123456789",
     *     		"patron_phone_work": "123456789",
     *     		"patron_primary_contact_info": "patron_email"
     *     	},
     *     	"total_loans": 50,
     *     	"total_fines": "100$",
     *     	"total_fines_paid": "0$",
     *     	"patron_code": {
     *     		"value": "CH",
     *     		"description": "Child"
     *     	}
     *     }
     */
    @POST
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postPatrons(
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Patron entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve a list of fine items.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param offset
     *     Skip over a number of elements by specifying an offset value for the query e.g. 0
     * @param query
     *     JSON array [{"field1","value1","operator1"},{"field2","value2","operator2"},...,{"fieldN","valueN","operatorN"}] with valid searchable fields: for example lastname
     *      e.g. ["fine_data.fine_amount",  150, ">"]
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
    @Path("fines")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getPatronsFines(
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("query")
        String query,
        @QueryParam("orderBy")
        String orderBy,
        @QueryParam("order")
        @DefaultValue("desc")
        PatronsResource.Order order,
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
     * Retrieve patron item with given {patronId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param patronId
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
    @Path("{patronId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getPatronsByPatronId(
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * Delete patron item with given {patronId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param patronId
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
    @Path("{patronId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deletePatronsByPatronId(
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * Update patron item with given {patronId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param patronId
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
     *     	"status": "ACTIVE",
     *     	"patron_name": "Smith,John",
     *     	"patron_barcode": "00007888",
     *     	"patron_local_id": "abcdefd",
     *     	"_id": "test3355",
     *     	"contact_info": {
     *     		"patron_address_local": {
     *     			"line1": "Main Street 1",
     *     			"line2": "Nice building near the corner",
     *     			"city": "London",
     *     			"state_province": "",
     *     			"postal_code": "",
     *     			"address_note": "",
     *     			"start_date": "2013-12-26Z"
     *     		},
     *     		"patron_address_home": {
     *     			"line1": "Main Street 1",
     *     			"line2": "Nice building near the corner",
     *     			"city": "London",
     *     			"state_province": "",
     *     			"postal_code": "",
     *     			"address_note": "",
     *     			"start_date": "2013-12-26Z"
     *     		},
     *     		"patron_address_work": {
     *     			"line1": "Main Street 1",
     *     			"line2": "Nice building near the corner",
     *     			"city": "London",
     *     			"state_province": "",
     *     			"postal_code": "",
     *     			"address_note": "",
     *     			"start_date": "2013-12-26Z"
     *     		},
     *     		"patron_email": "johns@mylib.org",
     *     		"patron_email_alternative": "johns@mylib.org",
     *     		"patron_phone_cell": "123456789",
     *     		"patron_phone_home": "123456789",
     *     		"patron_phone_work": "123456789",
     *     		"patron_primary_contact_info": "patron_email"
     *     	},
     *     	"total_loans": 50,
     *     	"total_fines": "100$",
     *     	"total_fines_paid": "0$",
     *     	"patron_code": {
     *     		"value": "CH",
     *     		"description": "Child"
     *     	}
     *     }
     */
    @PUT
    @Path("{patronId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putPatronsByPatronId(
        @PathParam("patronId")
        @NotNull
        String patronId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Patron entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve a list of fine items.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param offset
     *     Skip over a number of elements by specifying an offset value for the query e.g. 0
     * @param patronId
     *     
     * @param query
     *     JSON array [{"field1","value1","operator1"},{"field2","value2","operator2"},...,{"fieldN","valueN","operatorN"}] with valid searchable fields: for example amount
     *      e.g. ["fine_data.fine_amount", 100, ">"]
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
    @Path("{patronId}/fines")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getPatronsByPatronIdFines(
        @PathParam("patronId")
        @NotNull
        String patronId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("query")
        String query,
        @QueryParam("orderBy")
        String orderBy,
        @QueryParam("order")
        @DefaultValue("desc")
        PatronsResource.Order order,
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
     * @param patronId
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
    @Path("{patronId}/fines")
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postPatronsByPatronIdFines(
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * @param op
     *     Type of operation:
     *      e.g. pay, waive, dispute
     * @param amount
     *     Amount to pay against all current fines on patron
     *      e.g. 10
     * @param patronId
     *     
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
    @Path("{patronId}/fines/{fineId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postPatronsByPatronIdFinesByFineId(
        @PathParam("fineId")
        @NotNull
        String fineId,
        @PathParam("patronId")
        @NotNull
        String patronId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("op")
        @DefaultValue("pay")
        @NotNull
        PatronsResource.Op op,
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
     * @param patronId
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
    @Path("{patronId}/fines/{fineId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getPatronsByPatronIdFinesByFineId(
        @PathParam("fineId")
        @NotNull
        String fineId,
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * @param patronId
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
    @Path("{patronId}/fines/{fineId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deletePatronsByPatronIdFinesByFineId(
        @PathParam("fineId")
        @NotNull
        String fineId,
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * @param patronId
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
    @Path("{patronId}/fines/{fineId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putPatronsByPatronIdFinesByFineId(
        @PathParam("fineId")
        @NotNull
        String fineId,
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * Retrieve a list of loan items.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param offset
     *     Skip over a number of elements by specifying an offset value for the query e.g. 0
     * @param patronId
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
    @Path("{patronId}/loans")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getPatronsByPatronIdLoans(
        @PathParam("patronId")
        @NotNull
        String patronId,
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
        @QueryParam("orderBy")
        String orderBy,
        @QueryParam("order")
        @DefaultValue("desc")
        PatronsResource.Order order,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Create a new loan item.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param patronId
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
     *           "circ_desk": {
     *             "value": "MAIN_CIRC",
     *             "desc": "Joe's Circulation Desk"
     *           },
     *           "library": {
     *             "value": "ULAW",
     *             "desc": "Main Library"
     *           },
     *           "patron_id": "Me123",
     *           "item_barcode": "000000055510000222",
     *           "item_id": "23344156380001021",
     *           "due_date": 1413813699999,
     *           "loan_status": "ACTIVE",
     *           "loan_date": 1403434999999,
     *           "loan_fine": 0,
     *           "title": "History today",
     *           "author": "me",
     *           "description": "",
     *           "publication_year": 2000,
     *           "call_number": "1234567890",
     *           "renewable": false,
     *           "location_code": {
     *             "value": "1234567890",
     *             "name": "1234567890"
     *           },
     *           "item_policy": {
     *             "value": 2,
     *             "description": "2 Week Loan"
     *           },
     *       "total_record_count": 1
     *     }
     */
    @POST
    @Path("{patronId}/loans")
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postPatronsByPatronIdLoans(
        @PathParam("patronId")
        @NotNull
        String patronId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Loan entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Renew a loan for patronId
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param patronId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param operation
     *     Type of operation = renew
     *      e.g. renew
     * @param loanId
     *     
     */
    @POST
    @Path("{patronId}/loans/{loanId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postPatronsByPatronIdLoansByLoanId(
        @PathParam("loanId")
        @NotNull
        String loanId,
        @PathParam("patronId")
        @NotNull
        String patronId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("operation")
        @DefaultValue("renew")
        @NotNull
        PatronsResource.Operation operation,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve loan item with given {loanId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param patronId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param loanId
     *     
     */
    @GET
    @Path("{patronId}/loans/{loanId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getPatronsByPatronIdLoansByLoanId(
        @PathParam("loanId")
        @NotNull
        String loanId,
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * Delete loan item with given {loanId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param patronId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param loanId
     *     
     */
    @DELETE
    @Path("{patronId}/loans/{loanId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deletePatronsByPatronIdLoansByLoanId(
        @PathParam("loanId")
        @NotNull
        String loanId,
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * Update loan item with given {loanId}
     * 
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param patronId
     *     
     * @param vertxContext
     *      The Vertx Context Object <code>io.vertx.core.Context</code> 
     * @param asyncResultHandler
     *     A <code>Handler<AsyncResult<Response>>></code> handler {@link io.vertx.core.Handler} which must be called as follows - Note the 'GetPatronsResponse' should be replaced with '[nameOfYourFunction]Response': (example only) <code>asyncResultHandler.handle(io.vertx.core.Future.succeededFuture(GetPatronsResponse.withJsonOK( new ObjectMapper().readValue(reply.result().body().toString(), Patron.class))));</code> in the final callback (most internal callback) of the function.
     * @param lang
     *     Requested language. Optional. [lang=en]
     *     
     * @param loanId
     *     
     * @param entity
     *      e.g. {
     *     
     *           "_id": "888888880000111",
     *           "circ_desk": {
     *             "value": "MAIN_CIRC",
     *             "desc": "Joe's Circulation Desk"
     *           },
     *           "library": {
     *             "value": "ULAW",
     *             "desc": "Main Library"
     *           },
     *           "patron_id": "Me123",
     *           "item_barcode": "000000055510000222",
     *           "item_id": "23344156380001021",
     *           "due_date": 1413813699999,
     *           "loan_status": "ACTIVE",
     *           "loan_date": 1403434999999,
     *           "loan_fine": 0,
     *           "title": "History today",
     *           "author": "me",
     *           "description": "",
     *           "publication_year": 2000,
     *           "call_number": "1234567890",
     *           "renewable": false,
     *           "location_code": {
     *             "value": "1234567890",
     *             "name": "1234567890"
     *           },
     *           "item_policy": {
     *             "value": 2,
     *             "description": "2 Week Loan"
     *           },
     *       "total_record_count": 1
     *     }
     */
    @PUT
    @Path("{patronId}/loans/{loanId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putPatronsByPatronIdLoansByLoanId(
        @PathParam("loanId")
        @NotNull
        String loanId,
        @PathParam("patronId")
        @NotNull
        String patronId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, Loan entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    /**
     * Retrieve a list of request items.
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param requestType
     *     Filter by a specific request type
     * @param offset
     *     Skip over a number of elements by specifying an offset value for the query e.g. 0
     * @param patronId
     *     
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
    @Path("{patronId}/requests")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getPatronsByPatronIdRequests(
        @PathParam("patronId")
        @NotNull
        String patronId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("status")
        @DefaultValue("active")
        @NotNull
        PatronsResource.Status status,
        @QueryParam("request_type")
        @DefaultValue("ALL")
        PatronsResource.RequestType requestType,
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
     *     Item id of the item
     *     
     * @param patronId
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
    @Path("{patronId}/requests")
    @Consumes("application/json")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void postPatronsByPatronIdRequests(
        @PathParam("patronId")
        @NotNull
        String patronId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("item_id")
        @NotNull
        String itemId,
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
     * @param patronId
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
    @Path("{patronId}/requests/{requestId}")
    @Produces({
        "application/json",
        "text/plain"
    })
    @Validate
    void getPatronsByPatronIdRequestsByRequestId(
        @PathParam("requestId")
        @NotNull
        String requestId,
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * Cancel a user's request
     * 
     * @param authorization
     *     Used to send a valid JWT token.
     *      e.g. Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ
     *     
     * @param reason
     *     Reason code
     *     
     * @param patronId
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
    @Path("{patronId}/requests/{requestId}")
    @Produces({
        "text/plain"
    })
    @Validate
    void deletePatronsByPatronIdRequestsByRequestId(
        @PathParam("requestId")
        @NotNull
        String requestId,
        @PathParam("patronId")
        @NotNull
        String patronId,
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
     * @param patronId
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
    @Path("{patronId}/requests/{requestId}")
    @Consumes("application/json")
    @Produces({
        "text/plain"
    })
    @Validate
    void putPatronsByPatronIdRequestsByRequestId(
        @PathParam("requestId")
        @NotNull
        String requestId,
        @PathParam("patronId")
        @NotNull
        String patronId,
        @HeaderParam("Authorization")
        @NotNull
        String authorization,
        @QueryParam("lang")
        @DefaultValue("en")
        @Pattern(regexp = "[a-zA-Z]{2}")
        String lang, ItemRequest entity, io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>>asyncResultHandler, Context vertxContext)
        throws Exception
    ;

    public class DeletePatronsByPatronIdFinesByFineIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeletePatronsByPatronIdFinesByFineIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item deleted successfully
         * 
         */
        public static PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "fine not found"
         * 
         * 
         * @param entity
         *     "fine not found"
         *     
         */
        public static PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete fine -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete fine -- constraint violation"
         *     
         */
        public static PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

    }

    public class DeletePatronsByPatronIdLoansByLoanIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeletePatronsByPatronIdLoansByLoanIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item deleted successfully
         * 
         */
        public static PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "loan not found"
         * 
         * 
         * @param entity
         *     "loan not found"
         *     
         */
        public static PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete loan -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete loan -- constraint violation"
         *     
         */
        public static PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

    }

    public class DeletePatronsByPatronIdRequestsByRequestIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeletePatronsByPatronIdRequestsByRequestIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item deleted successfully
         * 
         */
        public static PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "request not found"
         * 
         * 
         * @param entity
         *     "request not found"
         *     
         */
        public static PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete request -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete request -- constraint violation"
         *     
         */
        public static PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

    }

    public class DeletePatronsByPatronIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private DeletePatronsByPatronIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item deleted successfully
         * 
         */
        public static PatronsResource.DeletePatronsByPatronIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new PatronsResource.DeletePatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "patron not found"
         * 
         * 
         * @param entity
         *     "patron not found"
         *     
         */
        public static PatronsResource.DeletePatronsByPatronIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to delete patron -- constraint violation"
         * 
         * 
         * @param entity
         *     "unable to delete patron -- constraint violation"
         *     
         */
        public static PatronsResource.DeletePatronsByPatronIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.DeletePatronsByPatronIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.DeletePatronsByPatronIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.DeletePatronsByPatronIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.DeletePatronsByPatronIdResponse(responseBuilder.build());
        }

    }

    public class GetPatronsByPatronIdFinesByFineIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetPatronsByPatronIdFinesByFineIdResponse(Response delegate) {
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
        public static PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse withJsonOK(Fine entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "fine not found"
         * 
         * 
         * @param entity
         *     "fine not found"
         *     
         */
        public static PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

    }

    public class GetPatronsByPatronIdFinesResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetPatronsByPatronIdFinesResponse(Response delegate) {
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
        public static PatronsResource.GetPatronsByPatronIdFinesResponse withJsonOK(Fines entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list fines -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list fines -- malformed parameter 'query', syntax error at column 6
         */
        public static PatronsResource.GetPatronsByPatronIdFinesResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.GetPatronsByPatronIdFinesResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdFinesResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdFinesResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

    }

    public class GetPatronsByPatronIdLoansByLoanIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetPatronsByPatronIdLoansByLoanIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns item with a given ID e.g. {
         * 
         *       "_id": "888888880000111",
         *       "circ_desk": {
         *         "value": "MAIN_CIRC",
         *         "desc": "Joe's Circulation Desk"
         *       },
         *       "library": {
         *         "value": "ULAW",
         *         "desc": "Main Library"
         *       },
         *       "patron_id": "Me123",
         *       "item_barcode": "000000055510000222",
         *       "item_id": "23344156380001021",
         *       "due_date": 1413813699999,
         *       "loan_status": "ACTIVE",
         *       "loan_date": 1403434999999,
         *       "loan_fine": 0,
         *       "title": "History today",
         *       "author": "me",
         *       "description": "",
         *       "publication_year": 2000,
         *       "call_number": "1234567890",
         *       "renewable": false,
         *       "location_code": {
         *         "value": "1234567890",
         *         "name": "1234567890"
         *       },
         *       "item_policy": {
         *         "value": 2,
         *         "description": "2 Week Loan"
         *       },
         *   "total_record_count": 1
         * }
         * 
         * @param entity
         *     {
         *     
         *           "_id": "888888880000111",
         *           "circ_desk": {
         *             "value": "MAIN_CIRC",
         *             "desc": "Joe's Circulation Desk"
         *           },
         *           "library": {
         *             "value": "ULAW",
         *             "desc": "Main Library"
         *           },
         *           "patron_id": "Me123",
         *           "item_barcode": "000000055510000222",
         *           "item_id": "23344156380001021",
         *           "due_date": 1413813699999,
         *           "loan_status": "ACTIVE",
         *           "loan_date": 1403434999999,
         *           "loan_fine": 0,
         *           "title": "History today",
         *           "author": "me",
         *           "description": "",
         *           "publication_year": 2000,
         *           "call_number": "1234567890",
         *           "renewable": false,
         *           "location_code": {
         *             "value": "1234567890",
         *             "name": "1234567890"
         *           },
         *           "item_policy": {
         *             "value": 2,
         *             "description": "2 Week Loan"
         *           },
         *       "total_record_count": 1
         *     }
         */
        public static PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse withJsonOK(Loan entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "loan not found"
         * 
         * 
         * @param entity
         *     "loan not found"
         *     
         */
        public static PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

    }

    public class GetPatronsByPatronIdLoansResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetPatronsByPatronIdLoansResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a list of loan items e.g. {
         * 
         *       "_id": "888888880000111",
         *       "circ_desk": {
         *         "value": "MAIN_CIRC",
         *         "desc": "Joe's Circulation Desk"
         *       },
         *       "library": {
         *         "value": "ULAW",
         *         "desc": "Main Library"
         *       },
         *       "patron_id": "Me123",
         *       "item_barcode": "000000055510000222",
         *       "item_id": "23344156380001021",
         *       "due_date": 1413813699999,
         *       "loan_status": "ACTIVE",
         *       "loan_date": 1403434999999,
         *       "loan_fine": 0,
         *       "title": "History today",
         *       "author": "me",
         *       "description": "",
         *       "publication_year": 2000,
         *       "call_number": "1234567890",
         *       "renewable": false,
         *       "location_code": {
         *         "value": "1234567890",
         *         "name": "1234567890"
         *       },
         *       "item_policy": {
         *         "value": 2,
         *         "description": "2 Week Loan"
         *       },
         *   "total_record_count": 1
         * }
         * 
         * @param entity
         *     {
         *     
         *           "_id": "888888880000111",
         *           "circ_desk": {
         *             "value": "MAIN_CIRC",
         *             "desc": "Joe's Circulation Desk"
         *           },
         *           "library": {
         *             "value": "ULAW",
         *             "desc": "Main Library"
         *           },
         *           "patron_id": "Me123",
         *           "item_barcode": "000000055510000222",
         *           "item_id": "23344156380001021",
         *           "due_date": 1413813699999,
         *           "loan_status": "ACTIVE",
         *           "loan_date": 1403434999999,
         *           "loan_fine": 0,
         *           "title": "History today",
         *           "author": "me",
         *           "description": "",
         *           "publication_year": 2000,
         *           "call_number": "1234567890",
         *           "renewable": false,
         *           "location_code": {
         *             "value": "1234567890",
         *             "name": "1234567890"
         *           },
         *           "item_policy": {
         *             "value": 2,
         *             "description": "2 Week Loan"
         *           },
         *       "total_record_count": 1
         *     }
         */
        public static PatronsResource.GetPatronsByPatronIdLoansResponse withJsonOK(Loans entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list loans -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list loans -- malformed parameter 'query', syntax error at column 6
         */
        public static PatronsResource.GetPatronsByPatronIdLoansResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.GetPatronsByPatronIdLoansResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdLoansResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdLoansResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

    }

    public class GetPatronsByPatronIdRequestsByRequestIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetPatronsByPatronIdRequestsByRequestIdResponse(Response delegate) {
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
        public static PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse withJsonOK(ItemRequest entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "request not found"
         * 
         * 
         * @param entity
         *     "request not found"
         *     
         */
        public static PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

    }

    public class GetPatronsByPatronIdRequestsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetPatronsByPatronIdRequestsResponse(Response delegate) {
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
        public static PatronsResource.GetPatronsByPatronIdRequestsResponse withJsonOK(ItemRequests entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list requests -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list requests -- malformed parameter 'query', syntax error at column 6
         */
        public static PatronsResource.GetPatronsByPatronIdRequestsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.GetPatronsByPatronIdRequestsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdRequestsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdRequestsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

    }

    public class GetPatronsByPatronIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetPatronsByPatronIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns item with a given ID e.g. {
         * 	"status": "ACTIVE",
         * 	"patron_name": "Smith,John",
         * 	"patron_barcode": "00007888",
         * 	"patron_local_id": "abcdefd",
         * 	"_id": "test3355",
         * 	"contact_info": {
         * 		"patron_address_local": {
         * 			"line1": "Main Street 1",
         * 			"line2": "Nice building near the corner",
         * 			"city": "London",
         * 			"state_province": "",
         * 			"postal_code": "",
         * 			"address_note": "",
         * 			"start_date": "2013-12-26Z"
         * 		},
         * 		"patron_address_home": {
         * 			"line1": "Main Street 1",
         * 			"line2": "Nice building near the corner",
         * 			"city": "London",
         * 			"state_province": "",
         * 			"postal_code": "",
         * 			"address_note": "",
         * 			"start_date": "2013-12-26Z"
         * 		},
         * 		"patron_address_work": {
         * 			"line1": "Main Street 1",
         * 			"line2": "Nice building near the corner",
         * 			"city": "London",
         * 			"state_province": "",
         * 			"postal_code": "",
         * 			"address_note": "",
         * 			"start_date": "2013-12-26Z"
         * 		},
         * 		"patron_email": "johns@mylib.org",
         * 		"patron_email_alternative": "johns@mylib.org",
         * 		"patron_phone_cell": "123456789",
         * 		"patron_phone_home": "123456789",
         * 		"patron_phone_work": "123456789",
         * 		"patron_primary_contact_info": "patron_email"
         * 	},
         * 	"total_loans": 50,
         * 	"total_fines": "100$",
         * 	"total_fines_paid": "0$",
         * 	"patron_code": {
         * 		"value": "CH",
         * 		"description": "Child"
         * 	}
         * }
         * 
         * @param entity
         *     {
         *     	"status": "ACTIVE",
         *     	"patron_name": "Smith,John",
         *     	"patron_barcode": "00007888",
         *     	"patron_local_id": "abcdefd",
         *     	"_id": "test3355",
         *     	"contact_info": {
         *     		"patron_address_local": {
         *     			"line1": "Main Street 1",
         *     			"line2": "Nice building near the corner",
         *     			"city": "London",
         *     			"state_province": "",
         *     			"postal_code": "",
         *     			"address_note": "",
         *     			"start_date": "2013-12-26Z"
         *     		},
         *     		"patron_address_home": {
         *     			"line1": "Main Street 1",
         *     			"line2": "Nice building near the corner",
         *     			"city": "London",
         *     			"state_province": "",
         *     			"postal_code": "",
         *     			"address_note": "",
         *     			"start_date": "2013-12-26Z"
         *     		},
         *     		"patron_address_work": {
         *     			"line1": "Main Street 1",
         *     			"line2": "Nice building near the corner",
         *     			"city": "London",
         *     			"state_province": "",
         *     			"postal_code": "",
         *     			"address_note": "",
         *     			"start_date": "2013-12-26Z"
         *     		},
         *     		"patron_email": "johns@mylib.org",
         *     		"patron_email_alternative": "johns@mylib.org",
         *     		"patron_phone_cell": "123456789",
         *     		"patron_phone_home": "123456789",
         *     		"patron_phone_work": "123456789",
         *     		"patron_primary_contact_info": "patron_email"
         *     	},
         *     	"total_loans": 50,
         *     	"total_fines": "100$",
         *     	"total_fines_paid": "0$",
         *     	"patron_code": {
         *     		"value": "CH",
         *     		"description": "Child"
         *     	}
         *     }
         */
        public static PatronsResource.GetPatronsByPatronIdResponse withJsonOK(Patron entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "patron not found"
         * 
         * 
         * @param entity
         *     "patron not found"
         *     
         */
        public static PatronsResource.GetPatronsByPatronIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.GetPatronsByPatronIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsByPatronIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsByPatronIdResponse(responseBuilder.build());
        }

    }

    public class GetPatronsFinesResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetPatronsFinesResponse(Response delegate) {
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
        public static PatronsResource.GetPatronsFinesResponse withJsonOK(Fines entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsFinesResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list fines -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list fines -- malformed parameter 'query', syntax error at column 6
         */
        public static PatronsResource.GetPatronsFinesResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsFinesResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.GetPatronsFinesResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsFinesResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsFinesResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsFinesResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsFinesResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsFinesResponse(responseBuilder.build());
        }

    }

    public class GetPatronsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private GetPatronsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a list of patron items e.g. {
         * 	"status": "ACTIVE",
         * 	"patron_name": "Smith,John",
         * 	"patron_barcode": "00007888",
         * 	"patron_local_id": "abcdefd",
         * 	"_id": "test3355",
         * 	"contact_info": {
         * 		"patron_address_local": {
         * 			"line1": "Main Street 1",
         * 			"line2": "Nice building near the corner",
         * 			"city": "London",
         * 			"state_province": "",
         * 			"postal_code": "",
         * 			"address_note": "",
         * 			"start_date": "2013-12-26Z"
         * 		},
         * 		"patron_address_home": {
         * 			"line1": "Main Street 1",
         * 			"line2": "Nice building near the corner",
         * 			"city": "London",
         * 			"state_province": "",
         * 			"postal_code": "",
         * 			"address_note": "",
         * 			"start_date": "2013-12-26Z"
         * 		},
         * 		"patron_address_work": {
         * 			"line1": "Main Street 1",
         * 			"line2": "Nice building near the corner",
         * 			"city": "London",
         * 			"state_province": "",
         * 			"postal_code": "",
         * 			"address_note": "",
         * 			"start_date": "2013-12-26Z"
         * 		},
         * 		"patron_email": "johns@mylib.org",
         * 		"patron_email_alternative": "johns@mylib.org",
         * 		"patron_phone_cell": "123456789",
         * 		"patron_phone_home": "123456789",
         * 		"patron_phone_work": "123456789",
         * 		"patron_primary_contact_info": "patron_email"
         * 	},
         * 	"total_loans": 50,
         * 	"total_fines": "100$",
         * 	"total_fines_paid": "0$",
         * 	"patron_code": {
         * 		"value": "CH",
         * 		"description": "Child"
         * 	}
         * }
         * 
         * @param entity
         *     {
         *     	"status": "ACTIVE",
         *     	"patron_name": "Smith,John",
         *     	"patron_barcode": "00007888",
         *     	"patron_local_id": "abcdefd",
         *     	"_id": "test3355",
         *     	"contact_info": {
         *     		"patron_address_local": {
         *     			"line1": "Main Street 1",
         *     			"line2": "Nice building near the corner",
         *     			"city": "London",
         *     			"state_province": "",
         *     			"postal_code": "",
         *     			"address_note": "",
         *     			"start_date": "2013-12-26Z"
         *     		},
         *     		"patron_address_home": {
         *     			"line1": "Main Street 1",
         *     			"line2": "Nice building near the corner",
         *     			"city": "London",
         *     			"state_province": "",
         *     			"postal_code": "",
         *     			"address_note": "",
         *     			"start_date": "2013-12-26Z"
         *     		},
         *     		"patron_address_work": {
         *     			"line1": "Main Street 1",
         *     			"line2": "Nice building near the corner",
         *     			"city": "London",
         *     			"state_province": "",
         *     			"postal_code": "",
         *     			"address_note": "",
         *     			"start_date": "2013-12-26Z"
         *     		},
         *     		"patron_email": "johns@mylib.org",
         *     		"patron_email_alternative": "johns@mylib.org",
         *     		"patron_phone_cell": "123456789",
         *     		"patron_phone_home": "123456789",
         *     		"patron_phone_work": "123456789",
         *     		"patron_primary_contact_info": "patron_email"
         *     	},
         *     	"total_loans": 50,
         *     	"total_fines": "100$",
         *     	"total_fines_paid": "0$",
         *     	"patron_code": {
         *     		"value": "CH",
         *     		"description": "Child"
         *     	}
         *     }
         */
        public static PatronsResource.GetPatronsResponse withJsonOK(Patrons entity) {
            Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. unable to list patrons -- malformed parameter 'query', syntax error at column 6
         * 
         * @param entity
         *     unable to list patrons -- malformed parameter 'query', syntax error at column 6
         */
        public static PatronsResource.GetPatronsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.GetPatronsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.GetPatronsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.GetPatronsResponse(responseBuilder.build());
        }

    }

    public enum Op {

        pay,
        waive,
        dispute;

    }

    public enum Operation {

        renew;

    }

    public enum Order {

        desc,
        asc;

    }

    public class PostPatronsByPatronIdFinesByFineIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostPatronsByPatronIdFinesByFineIdResponse(Response delegate) {
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
        public static PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse withJsonCreated(String location, Fine entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to pay fine -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to pay fine -- malformed JSON at 13:3"
         *     
         */
        public static PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

    }

    public class PostPatronsByPatronIdFinesResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostPatronsByPatronIdFinesResponse(Response delegate) {
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
        public static PatronsResource.PostPatronsByPatronIdFinesResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add fine -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add fine -- malformed JSON at 13:3"
         *     
         */
        public static PatronsResource.PostPatronsByPatronIdFinesResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.PostPatronsByPatronIdFinesResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdFinesResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdFinesResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdFinesResponse(responseBuilder.build());
        }

    }

    public class PostPatronsByPatronIdLoansByLoanIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostPatronsByPatronIdLoansByLoanIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a newly created item, with server-controlled fields like 'id' populated e.g. {
         * 
         *       "_id": "888888880000111",
         *       "circ_desk": {
         *         "value": "MAIN_CIRC",
         *         "desc": "Joe's Circulation Desk"
         *       },
         *       "library": {
         *         "value": "ULAW",
         *         "desc": "Main Library"
         *       },
         *       "patron_id": "Me123",
         *       "item_barcode": "000000055510000222",
         *       "item_id": "23344156380001021",
         *       "due_date": 1413813699999,
         *       "loan_status": "ACTIVE",
         *       "loan_date": 1403434999999,
         *       "loan_fine": 0,
         *       "title": "History today",
         *       "author": "me",
         *       "description": "",
         *       "publication_year": 2000,
         *       "call_number": "1234567890",
         *       "renewable": false,
         *       "location_code": {
         *         "value": "1234567890",
         *         "name": "1234567890"
         *       },
         *       "item_policy": {
         *         "value": 2,
         *         "description": "2 Week Loan"
         *       },
         *   "total_record_count": 1
         * }
         * 
         * @param location
         *     URI to the created fine item
         * @param entity
         *     {
         *     
         *           "_id": "888888880000111",
         *           "circ_desk": {
         *             "value": "MAIN_CIRC",
         *             "desc": "Joe's Circulation Desk"
         *           },
         *           "library": {
         *             "value": "ULAW",
         *             "desc": "Main Library"
         *           },
         *           "patron_id": "Me123",
         *           "item_barcode": "000000055510000222",
         *           "item_id": "23344156380001021",
         *           "due_date": 1413813699999,
         *           "loan_status": "ACTIVE",
         *           "loan_date": 1403434999999,
         *           "loan_fine": 0,
         *           "title": "History today",
         *           "author": "me",
         *           "description": "",
         *           "publication_year": 2000,
         *           "call_number": "1234567890",
         *           "renewable": false,
         *           "location_code": {
         *             "value": "1234567890",
         *             "name": "1234567890"
         *           },
         *           "item_policy": {
         *             "value": 2,
         *             "description": "2 Week Loan"
         *           },
         *       "total_record_count": 1
         *     }
         */
        public static PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse withJsonCreated(String location, Loan entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to renew loan -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to renew loan -- malformed JSON at 13:3"
         *     
         */
        public static PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

    }

    public class PostPatronsByPatronIdLoansResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostPatronsByPatronIdLoansResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a newly created item, with server-controlled fields like 'id' populated e.g. {
         *       "circ_desk": {
         *         "value": "MAIN_CIRC",
         *         "desc": "Joe's Circulation Desk"
         *       },
         *       "library": {
         *         "value": "ULAW",
         *         "desc": "Main Library"
         *       },
         *       "patron_id": "Me123",
         *       "item_barcode": "000000055510000222",
         *       "item_id": "23344156380001021",
         *       "due_date": 1413813699999,
         *       "loan_status": "ACTIVE",
         *       "loan_date": 1403434999999,
         *       "loan_fine": 0,
         *       "title": "History today",
         *       "author": "me",
         *       "description": "",
         *       "publication_year": 2000,
         *       "call_number": "1234567890",
         *       "renewable": false,
         *       "location_code": {
         *         "value": "1234567890",
         *         "name": "1234567890"
         *       },
         *       "item_policy": {
         *         "value": 2,
         *         "description": "2 Week Loan"
         *       },
         *   "total_record_count": 1
         * }
         * 
         * @param location
         *     URI to the created loan item
         * @param entity
         *     {
         *           "circ_desk": {
         *             "value": "MAIN_CIRC",
         *             "desc": "Joe's Circulation Desk"
         *           },
         *           "library": {
         *             "value": "ULAW",
         *             "desc": "Main Library"
         *           },
         *           "patron_id": "Me123",
         *           "item_barcode": "000000055510000222",
         *           "item_id": "23344156380001021",
         *           "due_date": 1413813699999,
         *           "loan_status": "ACTIVE",
         *           "loan_date": 1403434999999,
         *           "loan_fine": 0,
         *           "title": "History today",
         *           "author": "me",
         *           "description": "",
         *           "publication_year": 2000,
         *           "call_number": "1234567890",
         *           "renewable": false,
         *           "location_code": {
         *             "value": "1234567890",
         *             "name": "1234567890"
         *           },
         *           "item_policy": {
         *             "value": 2,
         *             "description": "2 Week Loan"
         *           },
         *       "total_record_count": 1
         *     }
         */
        public static PatronsResource.PostPatronsByPatronIdLoansResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add loan -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add loan -- malformed JSON at 13:3"
         *     
         */
        public static PatronsResource.PostPatronsByPatronIdLoansResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.PostPatronsByPatronIdLoansResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdLoansResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdLoansResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdLoansResponse(responseBuilder.build());
        }

    }

    public class PostPatronsByPatronIdRequestsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostPatronsByPatronIdRequestsResponse(Response delegate) {
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
        public static PatronsResource.PostPatronsByPatronIdRequestsResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add request -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add request -- malformed JSON at 13:3"
         *     
         */
        public static PatronsResource.PostPatronsByPatronIdRequestsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.PostPatronsByPatronIdRequestsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdRequestsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsByPatronIdRequestsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsByPatronIdRequestsResponse(responseBuilder.build());
        }

    }

    public class PostPatronsResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PostPatronsResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Returns a newly created item, with server-controlled fields like 'id' populated e.g. {
         * 	"status": "ACTIVE",
         * 	"patron_name": "Smith,John",
         * 	"patron_barcode": "00007888",
         * 	"patron_local_id": "abcdefd",
         * 	"contact_info": {
         * 		"patron_address_local": {
         * 			"line1": "Main Street 1",
         * 			"line2": "Nice building near the corner",
         * 			"city": "London",
         * 			"state_province": "",
         * 			"postal_code": "",
         * 			"address_note": "",
         * 			"start_date": "2013-12-26Z"
         * 		},
         * 		"patron_address_home": {
         * 			"line1": "Main Street 1",
         * 			"line2": "Nice building near the corner",
         * 			"city": "London",
         * 			"state_province": "",
         * 			"postal_code": "",
         * 			"address_note": "",
         * 			"start_date": "2013-12-26Z"
         * 		},
         * 		"patron_address_work": {
         * 			"line1": "Main Street 1",
         * 			"line2": "Nice building near the corner",
         * 			"city": "London",
         * 			"state_province": "",
         * 			"postal_code": "",
         * 			"address_note": "",
         * 			"start_date": "2013-12-26Z"
         * 		},
         * 		"patron_email": "johns@mylib.org",
         * 		"patron_email_alternative": "johns@mylib.org",
         * 		"patron_phone_cell": "123456789",
         * 		"patron_phone_home": "123456789",
         * 		"patron_phone_work": "123456789",
         * 		"patron_primary_contact_info": "patron_email"
         * 	},
         * 	"total_loans": 50,
         * 	"total_fines": "100$",
         * 	"total_fines_paid": "0$",
         * 	"patron_code": {
         * 		"value": "CH",
         * 		"description": "Child"
         * 	}
         * }
         * 
         * @param location
         *     URI to the created patron item
         * @param entity
         *     {
         *     	"status": "ACTIVE",
         *     	"patron_name": "Smith,John",
         *     	"patron_barcode": "00007888",
         *     	"patron_local_id": "abcdefd",
         *     	"contact_info": {
         *     		"patron_address_local": {
         *     			"line1": "Main Street 1",
         *     			"line2": "Nice building near the corner",
         *     			"city": "London",
         *     			"state_province": "",
         *     			"postal_code": "",
         *     			"address_note": "",
         *     			"start_date": "2013-12-26Z"
         *     		},
         *     		"patron_address_home": {
         *     			"line1": "Main Street 1",
         *     			"line2": "Nice building near the corner",
         *     			"city": "London",
         *     			"state_province": "",
         *     			"postal_code": "",
         *     			"address_note": "",
         *     			"start_date": "2013-12-26Z"
         *     		},
         *     		"patron_address_work": {
         *     			"line1": "Main Street 1",
         *     			"line2": "Nice building near the corner",
         *     			"city": "London",
         *     			"state_province": "",
         *     			"postal_code": "",
         *     			"address_note": "",
         *     			"start_date": "2013-12-26Z"
         *     		},
         *     		"patron_email": "johns@mylib.org",
         *     		"patron_email_alternative": "johns@mylib.org",
         *     		"patron_phone_cell": "123456789",
         *     		"patron_phone_home": "123456789",
         *     		"patron_phone_work": "123456789",
         *     		"patron_primary_contact_info": "patron_email"
         *     	},
         *     	"total_loans": 50,
         *     	"total_fines": "100$",
         *     	"total_fines_paid": "0$",
         *     	"patron_code": {
         *     		"value": "CH",
         *     		"description": "Child"
         *     	}
         *     }
         */
        public static PatronsResource.PostPatronsResponse withJsonCreated(String location, StreamingOutput entity) {
            Response.ResponseBuilder responseBuilder = Response.status(201).header("Content-Type", "application/json").header("Location", location);
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to add patron -- malformed JSON at 13:3"
         * 
         * 
         * @param entity
         *     "unable to add patron -- malformed JSON at 13:3"
         *     
         */
        public static PatronsResource.PostPatronsResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. Internal server error, contact administrator
         * 
         * @param entity
         *     Internal server error, contact administrator
         */
        public static PatronsResource.PostPatronsResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PostPatronsResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PostPatronsResponse(responseBuilder.build());
        }

    }

    public class PutPatronsByPatronIdFinesByFineIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutPatronsByPatronIdFinesByFineIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "fine not found"
         * 
         * 
         * @param entity
         *     "fine not found"
         *     
         */
        public static PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update fine -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update fine -- malformed JSON at 13:4"
         *     
         */
        public static PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdFinesByFineIdResponse(responseBuilder.build());
        }

    }

    public class PutPatronsByPatronIdLoansByLoanIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutPatronsByPatronIdLoansByLoanIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "loan not found"
         * 
         * 
         * @param entity
         *     "loan not found"
         *     
         */
        public static PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update loan -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update loan -- malformed JSON at 13:4"
         *     
         */
        public static PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdLoansByLoanIdResponse(responseBuilder.build());
        }

    }

    public class PutPatronsByPatronIdRequestsByRequestIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutPatronsByPatronIdRequestsByRequestIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "request not found"
         * 
         * 
         * @param entity
         *     "request not found"
         *     
         */
        public static PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update request -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update request -- malformed JSON at 13:4"
         *     
         */
        public static PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdRequestsByRequestIdResponse(responseBuilder.build());
        }

    }

    public class PutPatronsByPatronIdResponse
        extends com.sling.rest.jaxrs.resource.support.ResponseWrapper
    {


        private PutPatronsByPatronIdResponse(Response delegate) {
            super(delegate);
        }

        /**
         * Item successfully updated
         * 
         */
        public static PatronsResource.PutPatronsByPatronIdResponse withNoContent() {
            Response.ResponseBuilder responseBuilder = Response.status(204);
            return new PatronsResource.PutPatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Item with a given ID not found e.g. "patron not found"
         * 
         * 
         * @param entity
         *     "patron not found"
         *     
         */
        public static PatronsResource.PutPatronsByPatronIdResponse withPlainNotFound(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Bad request, e.g malformed request body or query parameter. Details of the error (e.g name of the parameter or line/character number with malformed data) provided in the response. e.g. "unable to update patron -- malformed JSON at 13:4"
         * 
         * 
         * @param entity
         *     "unable to update patron -- malformed JSON at 13:4"
         *     
         */
        public static PatronsResource.PutPatronsByPatronIdResponse withPlainBadRequest(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Internal server error, e.g. due to misconfiguration e.g. internal server error, contact administrator
         * 
         * @param entity
         *     internal server error, contact administrator
         */
        public static PatronsResource.PutPatronsByPatronIdResponse withPlainInternalServerError(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * No valid token found
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PutPatronsByPatronIdResponse withPlainUnauthorized(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdResponse(responseBuilder.build());
        }

        /**
         * Access denied not valid privilage in resurces
         *  e.g. Unauthorized
         * 
         * @param entity
         *     Unauthorized
         */
        public static PatronsResource.PutPatronsByPatronIdResponse withPlainForbidden(String entity) {
            Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "text/plain");
            responseBuilder.entity(entity);
            return new PatronsResource.PutPatronsByPatronIdResponse(responseBuilder.build());
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
