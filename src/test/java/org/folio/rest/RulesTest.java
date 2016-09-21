package org.folio.rest;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.folio.rest.jaxrs.model.Item;
import org.folio.rulez.Rules;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.api.runtime.rule.Match;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is a class to launch  rules.
 */
public class RulesTest {

  public static final String      RULES_DIR         = "/";
  public static final String      RULES_FILE_PATH   = "src/main/resources/";
  private static ObjectMapper mapper                = new ObjectMapper();
  private KieSession ksession;
  
  @Before
  public void setup(){
    try {
      ksession = new Rules().buildSession(RulesTest.class.getResource(RULES_DIR).toURI());
    }  catch (Exception e) {
      e.printStackTrace();
      Assert.fail("can't create drools session....");
    }

  }
  
  @Test
  public final void checkRule() throws Exception {
    try {
      Item item = mapper.readValue(getFile("item.json"), Item.class);
      ksession.insert(item);
      ksession.fireAllRules();
      //Assert.assertEquals(5, message.getStatus());
    } catch (Throwable t) {
      t.printStackTrace();
      throw t;
    }
  }

  private String getFile(String filename) throws IOException {
    return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(filename), "UTF-8");
  }
  
  @After
  public void tearDown(){
    
    if(ksession != null){
      ksession.dispose();
    }
  }

}