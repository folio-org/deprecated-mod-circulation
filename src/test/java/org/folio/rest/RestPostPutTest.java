package org.folio.rest;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.folio.rest.persist.MongoCRUD;
import org.folio.rest.tools.utils.NetworkUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.google.common.io.ByteStreams;


/**
 * @author shale
 *
 */

@RunWith(VertxUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestPostPutTest {

  private static Vertx             vertx;
  private Async                    async = null;
  static int                       port;
  private static ArrayList<String> urls;

  private static final String[]                   bibId = new String[]{""};
  private static final  String[]                   patronId = new String[]{""};

  private static final  String[]                   fineId = new String[]{""};
  private static final  String[]                   loanId = new String[]{""};
  private static final  String[]                   requestId = new String[]{""};
  private static final  String[]                   itemId = new String[]{""};

  /**
   * only want to deploy verticle once for all tests
   */
  @BeforeClass
  public static void onlyOnce() {
    vertx = Vertx.vertx();

    // find a free port and use it to deploy the verticle
    port = NetworkUtils.nextFreePort();

    DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port));
    vertx.deployVerticle(RestVerticle.class.getName(), options);

    // load the urls to test from the csv file
    try {
      urls = urlsFromFile();
    } catch (IOException e2) {
      e2.printStackTrace();
    }

    // TODO change this to wait with handler
    //wait until until the port the verticle is deployed on starts responding
    for (int i = 0; i < 15; i++) {
      try {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", port), 100 /* ms timeout */);
        socket.close();
        break;
      } catch (IOException e) { // NOSONAR
        // loop for 15 seconds while waiting for the verticle to deploy
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e1) {}
      }
    }

    // start the embedded mongo - this is blocking code and may throw a thread blocked exception which is ok
    MongoCRUD.setIsEmbedded(true);
    try {
      MongoCRUD.getInstance(vertx).startEmbeddedMongo();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @AfterClass
  public static void tearDown() {
    deleteTempFilesCreated();
    vertx.close();
    // another dirty hack - loop for 15 seconds while waiting for the port the verticle was deployed on
    // stops answering - meaning the verticle is no longer listening on thaat port and hence un-deployed
    for (int i = 0; i < 15; i++) {
      try {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", port), 100 /* ms timeout */);
        socket.close();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e1) {}
      } catch (IOException e) { // NOSONAR
        break;
      }
    }
  }

  private static void deleteTempFilesCreated(){
    System.out.println("deleting created files");
    // Lists all files in folder
    File folder = new File(RestVerticle.DEFAULT_TEMP_DIR);
    File fList[] = folder.listFiles();
    // Searchs items_flat.txt
    for (int i = 0; i < fList.length; i++) {
        String pes = fList[i].getName();
        if (pes.endsWith("items_flat.txt")) {
            // and deletes
            boolean success = fList[i].delete();
        }
    }
  }

  /**
   * simple POST of a bib
   *
   * @param context
   */
  @Test
  public void test1(TestContext context) {

    //StringBuffer id = new StringBuffer();

    sendData("http://localhost:" + port + "/bibs", context, HttpMethod.POST,
            "{" + "\"bib_view\": {" + "\"Title\": \"Of Mice And Men\"," + "\"Author\": \"J. Stienbeck\","
          + "\"publication_date\": \"1413879432450\"," + "\"desc\": \"description\"}}", bibId, 201);
    //bibId = id.toString();

  }

  /**
   * simple update of that bib
   *
   * @param context
   */
  @Test
  public void test2(TestContext context) {
    System.out.println("bibId = " + bibId[0]);

    sendData("http://localhost:" + port + "/bibs/" + bibId[0], context, HttpMethod.PUT,
            "{" + "\"bib_view\": {" + "\"Title\": \"Of Mice And Men\"," + "\"Author\": \"J. Stienbeck JR.\","
          + "\"publication_date\": \"1413879432450\"," + "\"desc\": \"description\"}}", null, 204);
  }

  /**
   * simple DELETE of the bib
   *
   * @param context
   */
  @Test
  public void test3(TestContext context) {

    sendData("http://localhost:" + port + "/bibs/" + bibId[0], context, HttpMethod.DELETE, "", null, 204);

  }

  /**
   *
   * simple POST of a patron
   *
   * @param context
   */
  @Test
  public void test4(TestContext context){
    try {
      sendData("http://localhost:" + port + "/patrons", context, HttpMethod.POST,
              getFile("patron.json"), patronId, 201);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
  *
  * simple POST of a patron (running again so there is data from the group by test)
  *
  * @param context
  */
  @Test
  public void test4a(TestContext context){
    try {
      sendData("http://localhost:" + port + "/patrons", context, HttpMethod.POST,
              getFile("patron2.json"), null, 201);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
  *
  * simple POST of a patron (running again so there is data from the group by test)
  *
  * @param context
  */
  @Test
  public void test4b(TestContext context){
    try {
      sendData("http://localhost:" + port + "/patrons", context, HttpMethod.POST,
              getFile("patron.json"), null, 201);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  /**
   * simple UPDATE of the patron (last modified date added automatically on server side)
   *
   * @param context
   */
  @Test
  public void test5(TestContext context) {

    try {

      sendData("http://localhost:" + port + "/patrons/" + patronId[0], context, HttpMethod.PUT,
              getFile("patron.json"), null, 204);


    } catch (IOException e) {
      e.printStackTrace();
      context.fail(e.getMessage());
    }
  }

  /**
   * simple POST of a fine - add it to the previously created patron
   *
   * @param context
   */
  @Test
  public void test6(TestContext context){


    JsonObject fine;
    try {
      fine = new JsonObject(getFile("fine.json"));

      fine.put("patron_id", patronId[0]);

      sendData("http://localhost:" + port + "/patrons/" + patronId[0] + "/fines", context, HttpMethod.POST,
              fine.encode(), fineId, 201);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * simple POST of a loan object add it to the previously created patron
   *
   * @param context
   */
  @Test
  public void test7(TestContext context){

    try {
      JsonObject loan = new JsonObject(getFile("loan.json"));

      loan.put("patron_id", patronId[0]);

      sendData("http://localhost:" + port + "/patrons/" + patronId[0] + "/loans", context, HttpMethod.POST,
              loan.encode(), loanId, 201);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  /**
   * check update / delete of non existent patron
   * @param context
   */
  @Test
  public void test8(TestContext context){

    try {
      JsonObject patron = new JsonObject(getFile("patron.json"));

      sendData("http://localhost:" + port + "/patrons/12345", context, HttpMethod.PUT, patron.encode(), null, 404);
      sendData("http://localhost:" + port + "/patrons/12345", context, HttpMethod.DELETE, "", null, 404);

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }


  /**
   * simple POST of a request item - add it to the previously created patron
   *
   * @param context
   */
  @Test
  public void test8a(TestContext context){

    try {
      JsonObject request = new JsonObject(getFile("request.json"));

      request.put("patron_id", patronId[0]);

      sendData("http://localhost:" + port + "/patrons/" + patronId[0] + "/requests?item_id=23344156380001021", context,
              HttpMethod.POST, request.encode() , requestId, 201);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * simple upoad of a file using /admin
   *
   * @param context
   */
/*  @Test
  public void test8b(TestContext context){

      try {
        sendData("http://localhost:" + port + "/admin/upload?file_name=items_flat.txt&"
                + "persist_method=SAVE_AND_NOTIFY&bus_address=uploads.import.items" ,
            context, HttpMethod.POST, getFile("items_flat.txt") , null, "multipart/form-data", 204);
      } catch (IOException e) {
        e.printStackTrace();
      }
  }*/

  /**
   * test api generated client
   * @param context
   */
  @Test
  public void test8b(TestContext context){
/*    Async async = context.async();
    try {
      new PatronsClient("localhost", port, "abc").getPatronId(patronId[0], null ,  response -> {
        if(response.statusCode() != 200 && response.statusCode() != 404){
          context.fail();
        }
        async.complete();
      });
    } catch (Exception e) {
      e.printStackTrace();
      context.fail();
    }*/
  }

  /**
   * simple POST of an item
   *
   * @param context
   */
  @Test
  public void test8c(TestContext context){

    try {
      JsonObject request = new JsonObject(getFile("item.json"));

      sendData("http://localhost:" + port + "/items", context,
              HttpMethod.POST, request.encode() , itemId, 201);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test8d(TestContext context){
    Async async = context.async();
    JsonObject groupByDate = new JsonObject();

    /*
     *EXAMPLE OF GROUPING BY DATE FIELD
     * groupByDate.put("_id", new JsonObject()
     *          .put("month", new JsonObject("{\"$month\": \"$last_modified\"}"))
     *          .put("day", new JsonObject("{\"$dayOfMonth\": \"$last_modified\"}"))
     *          .put("year", new JsonObject("{\"$year\": \"$last_modified\"}")));
     */

    //add the _id date part to group by
    groupByDate.put("_id", "$status");

    //add sum of loans per date
    groupByDate.put("totalLoans", new JsonObject("{\"$sum\": \"$total_loans\" }"));

    //add amount of entries per date (count)
    groupByDate.put("count", new JsonObject("{ \"$sum\": 1 }"));

    new JsonObject();

    String json = "{\"_id\" : \"$status\",\"totalLoans\":{\"$sum\": \"$total_loans\" }, \"count\":{ \"$sum\": 1 }}";

    MongoCRUD.getInstance(vertx).groupBy("patron", new JsonObject(json),  job -> {
      if( job.succeeded() ) {
        context.assertTrue(true);
      }
      else{
        context.fail();
      }
      async.complete();
    });
  }


  /**
   *read a list of GET urls and run them all
   *
   * @param context
   */
  @Test
  public void test8e(TestContext context) {
    try {
      urls.forEach(url -> {
        Async async = context.async();

        //split the fields in the csv file [method, url, comment]
        String[] urlInfo = url.split(" , ");
        HttpMethod method = HttpMethod.GET;
        HttpClient client = vertx.createHttpClient();

        //build the url by replacing the placeholder in the url <port> with the free port used for deployment
        HttpClientRequest request = client.requestAbs(method, urlInfo[1].replaceFirst("<port>", port + "").
          replaceFirst("<patron_id>", patronId[0]).replaceFirst("<item_id>", itemId[0]),
          new Handler<HttpClientResponse>() {

            @Override
            public void handle(HttpClientResponse httpClientResponse) {

              System.out.println(urlInfo[1]);
              if (httpClientResponse.statusCode() != 404) {
                // this is cheating for now - add posts to the test case so that
                // we dont get 404 for missing entities
                context.assertInRange(200, httpClientResponse.statusCode(), 99);
              }
              httpClientResponse.bodyHandler(new Handler<Buffer>() {
                @Override
                public void handle(Buffer buffer) {
                  System.out.println(buffer.getString(0, buffer.length()));
                  async.complete();
                }
              });
            }
          });
        //request.headers().add("Authorization", "abcdefg");
        request.headers().add("Accept", "application/json");
        request.setChunked(true);
        request.end();
      });
    } catch (Throwable e) {
      e.printStackTrace();
    } finally {

    }
  }


  private void sendData(String api, TestContext context, HttpMethod method, String content, String[] id, int errorCode) {
    sendData(api, context, method, content, id, "application/json", errorCode);
  }

  /**
   * for POST / PUT / DELETE
   * @param api
   * @param context
   * @param method
   * @param content
   * @param id
   */
  private void sendData(String api, TestContext context, HttpMethod method, String content, String[] id,
      String contentType, int errorCode) {
    Async async = context.async();
    HttpClient client = vertx.createHttpClient();
    HttpClientRequest request;
    Buffer buffer = Buffer.buffer(content);

    if (method == HttpMethod.POST) {
      request = client.postAbs(api);
    }
    else if (method == HttpMethod.DELETE) {
      request = client.deleteAbs(api);
    }
    else {
      request = client.putAbs(api);
    }
    request.exceptionHandler(error -> {
      async.complete();
      context.fail(error.getMessage());
    }).handler(response -> {
      int statusCode = response.statusCode();
      // is it 2XX
      System.out.println("Status - " + statusCode + " at " + System.currentTimeMillis() + " for " + api);

      if (statusCode >= HttpResponseStatus.OK.code() && statusCode < HttpResponseStatus.MULTIPLE_CHOICES.code()) {
        if(id != null){
          id[0] = response.getHeader("Location") ;
        }
      }
      if(errorCode == statusCode){
        context.assertTrue(true);
      } else {
        context.fail("expected " + errorCode +" code, but got " + statusCode);
      }
      if(!async.isCompleted()){
        async.complete();
      }
      System.out.println("complete");
    });

    request.setChunked(true);
    //request.putHeader("Authorization", "abcdefg");
    request.putHeader("Accept", "application/json,text/plain");
    request.putHeader("Content-type", contentType);
    //request.write(buffer);
    request.end(buffer);
  }

  private static ArrayList<String> urlsFromFile() throws IOException {
    ArrayList<String> ret = new ArrayList<String>();
    byte[] content = ByteStreams.toByteArray(RestPostPutTest.class.getResourceAsStream("/urls.csv"));
    InputStream is = null;
    BufferedReader bfReader = null;
    try {
      is = new ByteArrayInputStream(content);
      bfReader = new BufferedReader(new InputStreamReader(is));
      String temp = null;
      while ((temp = bfReader.readLine()) != null) {
        ret.add(temp);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (is != null)
          is.close();
      } catch (Exception ex) {

      }
    }
    return ret;
  }


  private String getFile(String filename) throws IOException {
    return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(filename), "UTF-8");
  }

}
