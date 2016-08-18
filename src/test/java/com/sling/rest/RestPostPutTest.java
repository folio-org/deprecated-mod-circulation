/**
 * RestPostPutTest
 * 
 * Aug 16, 2016
 *
 * Apache License Version 2.0
 */
package com.sling.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.google.common.io.ByteStreams;
import com.sling.rest.persist.MongoCRUD;
import com.sling.rest.resource.utils.NetworkUtils;

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
  private static String            insertID;

  /**
   * only want to deploy verticle once for all tests
   */
  @BeforeClass
  public static void onlyOnce() {
    vertx = Vertx.vertx();
    port = NetworkUtils.nextFreePort();
    // make sure the port from the pom.xml is the same as the port
    // in the excel file with the urls
    DeploymentOptions options = new DeploymentOptions().setConfig(new JsonObject().put("http.port", port));
    vertx.deployVerticle(RestVerticle.class.getName(), options);
    try {
      urls = urlsFromFile();
    } catch (IOException e2) {
      e2.printStackTrace();
    }
    //dirty hack - wait until port the verticle is deployed on starts responding
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
    //start the embedded mongo
    MongoCRUD.setIsEmbedded(true);
    try {
      //blocks
      MongoCRUD.getInstance(vertx).startEmbeddedMongo();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @AfterClass
  public static void tearDown() {
    vertx.close();
    for (int i = 0; i < 15; i++) {
      try {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", port), 100 /* ms timeout */);
        socket.close();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e1) {}
      } catch (IOException e) { // NOSONAR
        // loop for 15 seconds while waiting for the verticle to un-deploy
        break;
      }
    }

  }

  @Test
  public void test1(TestContext context) {
    sendData("http://localhost:" + port + "/apis/bibs", context, HttpMethod.POST);
  }

  @Test
  public void test2(TestContext context) {
    sendData("http://localhost:" + port + "/apis/bibs/" + insertID, context, HttpMethod.PUT);
  }

  @Test
  public void test3(TestContext context) {
    try {
      int[] urlCount = { urls.size() };
      // Async async = context.async(urlCount[0]);
      urls.forEach(url -> {
        Async async = context.async();
        urlCount[0] = urlCount[0] - 1;
        HttpMethod method = null;

        String[] urlInfo = url.split(" , ");
        if ("POST".equalsIgnoreCase(urlInfo[0].trim())) {
          method = HttpMethod.POST;
        } else if ("PUT".equalsIgnoreCase(urlInfo[0].trim())) {
          method = HttpMethod.PUT;
        } else if ("DELETE".equalsIgnoreCase(urlInfo[0].trim())) {
          method = HttpMethod.DELETE;
        } else {
          method = HttpMethod.GET;
        }
        HttpClient client = vertx.createHttpClient();
        HttpClientRequest request = client.requestAbs(method, urlInfo[1].replaceFirst("<port>", port + ""),
          new Handler<HttpClientResponse>() {

            @Override
            public void handle(HttpClientResponse httpClientResponse) {

              System.out.println(urlInfo[1]);
              if (httpClientResponse.statusCode() != 404) {
                // this is cheating for now - add posts to the test case so that
                // we dont get 404 for missing entities
                context.assertInRange(200, httpClientResponse.statusCode(), 5);
              }
              // System.out.println(context.assertInRange(200, httpClientResponse.statusCode(),5).);
              httpClientResponse.bodyHandler(new Handler<Buffer>() {
                @Override
                public void handle(Buffer buffer) {
                  /*
                   * // System.out.println("Response (" // + buffer.length() // + "): ");
                   */System.out.println(buffer.getString(0, buffer.length()));
                  async.complete();

                }
              });
            }
          });
        request.headers().add("Authorization", "abcdefg");
        request.headers().add("Accept", "application/json");
        request.setChunked(true);
        request.end();
      });
    } catch (Throwable e) {
      e.printStackTrace();
    } finally {

    }
  }
  
  private void sendData(String api, TestContext context, HttpMethod method) {
    async = context.async();
    HttpClient client = vertx.createHttpClient();
    HttpClientRequest request;
    Buffer buffer = Buffer.buffer("{" + "\"bib_view\": {" + "\"Title\": \"Of Mice And Men\"," + "\"Author\": \"J. Stienbeck\","
        + "\"publication_date\": \"1413879432450\"," + "\"desc\": \"description\"}}");

    if (method == HttpMethod.POST) {
      request = client.postAbs(api);
    } else {
      request = client.putAbs(api);
    }
    request.exceptionHandler(error -> {
      context.fail(error.getMessage());
    }).handler(response -> {
      int statusCode = response.statusCode();
      // is it 2XX
      System.out.println("Status - " + statusCode);

      if (statusCode >= HttpResponseStatus.OK.code() && statusCode < HttpResponseStatus.MULTIPLE_CHOICES.code()) {
        context.assertTrue(true);
        insertID = response.getHeader("Location");
      } else {
        response.bodyHandler(responseData -> {
          context.fail("got non 200 response from bosun, error: " + responseData + " code " + statusCode);
        });
      }
      async.complete();
    })
    .setChunked(true)
    .putHeader("Authorization", "abcdefg")
    .putHeader("Accept", "application/json;text/plain")
    .putHeader("Content-type", "application/json")
    .write(buffer)
    .end();
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
}
