package com.softserve.edu.greencity.ui.tests;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.softserve.edu.greencity.ui.pages.tipstricks.TipsTricksPage;

public class TipsTricksTest extends GreencityTestRunner {
// @Test
  public void checkButtonTop() {
      TipsTricksPage tipstrickspage = loadApplication();
      tipstrickspage.navigateMyCabinet();
      //code for login
      AssertJUnit.assertEquals( driver.getTitle(), "My Cabinet");
  }
  
 //@Test
  public void text() {
      TipsTricksPage tipstrickspage = loadApplication();
      System.out.println(tipstrickspage.getAmountPeopleText());
      System.out.println("Amount Bags were used: " + tipstrickspage); 
      
  }
  //@Test
  public void checkGetNumber() {
      TipsTricksPage tipsTricksPage = loadApplication();
      System.out.println("Amount People: " +  tipsTricksPage.quantityPeople());
      System.out.println("Amount Bags were used: " + tipsTricksPage.quantityBags()); 
      System.out.println("Amount Cups were used: " + tipsTricksPage.quantityCups()); 
      AssertJUnit.assertEquals(tipsTricksPage.quantityPeople(), tipsTricksPage.quantityPeople());
     AssertJUnit.assertEquals(tipsTricksPage.quantityBags(), tipsTricksPage.quantityBags());
     AssertJUnit.assertEquals(tipsTricksPage.quantityCups(), tipsTricksPage.quantityCups()); 
  }
  //@Parameters({ "email" })
 // @Test 
  public void subscribe(String email) throws InterruptedException {
     TipsTricksPage subscr = loadApplication();
     subscr.clickEmailTipsTricks();
     subscr.setEmailTipsTricks(email);
//     subscr.setEmailTipsTricks("almyyhvxddxxnoczzt@ttirv.com");
     Thread.sleep(1000);
     subscr.clickSubscribeOnTipsTricks();
  } 
     
//   @Test
   public void mainEcoNews() {
       TipsTricksPage news = loadApplication();
       news.moveMainEcoNewsLink();
       
   }
     
      
  }

