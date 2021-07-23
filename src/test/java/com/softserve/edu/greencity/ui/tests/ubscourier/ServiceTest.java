package com.softserve.edu.greencity.ui.tests.ubscourier;

import com.softserve.edu.greencity.data.users.User;
import com.softserve.edu.greencity.data.users.UserRepository;
import com.softserve.edu.greencity.ui.pages.common.MainMenuDropdown;
import com.softserve.edu.greencity.ui.pages.econews.CreateNewsPage;
import com.softserve.edu.greencity.ui.pages.ubs.OrderDetailsPage;
import com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunnerWithLoginLogout;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ServiceTest extends GreenCityTestRunnerWithLoginLogout {
    private CreateNewsPage createNewsPage;
    private OrderDetailsPage orderDetailsPage;

    @BeforeMethod
    public void SignIn() {
        SoftAssert softAssert = new SoftAssert();
        User user = UserRepository.get().temporary();
        orderDetailsPage = loadApplication()
                .signIn()
                .getManualLoginComponent()
                .successfullyLogin(user)
                .navigateMenuUBSCourier();
    }

    //@AfterMethod
    public void signOut() {
        createNewsPage.signOut();
    }

    @Test(testName = "GC-1951 ", description = "GC-1951 ")
    @Description("Verify that a total price of 'Безнадійний одяг' 120 л. label is calculated according to the formula")
    public void OldClothesFormula120LTest(){
        logger.info("Enter 3 in input of 'Безнадійний одяг 120l'");
       orderDetailsPage.enterOnInputNumberOfPackeges(1,"3");
        Assert.assertEquals(orderDetailsPage.getTotalPrice(1),"900 UAH", "Calculating of formula went wrong");
    }

    @Test(testName = "GC-1949 ", description = "GC-1949 ")
    @Description("Verify that the system shows the error message “Мінімальна сума замовлення повинна складати 500 грн” " +
            "under the total amount if it is less than 500 UAH and the button “Далі” shall be disabled.")
    public void ErrorMessageAppears(){
        logger.info("Enter total amount less than 500 UAH");
        orderDetailsPage.enterOnInputNumberOfPackeges(2,"1");
        Assert.assertEquals(orderDetailsPage.getTextOfMinimumErrorMassage(),"The minimum order amount must be 500 UAH", "Error massage was not shown");
    }

    @Test(testName = "GC-1952 ", description = "GC-1952 ")
    @Description("Verify that a total price of 'Безнадійний одяг' 20 л. label is calculated according to the formula")
    public void OldClothesFormula20LTest(){
        logger.info("Enter 3 in input of 'Безнадійний одяг 20l'");
        orderDetailsPage.enterOnInputNumberOfPackeges(0,"3");
        Assert.assertEquals(orderDetailsPage.getTotalPrice(0),"150 UAH", "Calculating of formula went wrong");
    }

    @Test(testName = "GC-1950 ", description = "GC-1950 ")
    @Description("Verify that a total price of 'Безнадійний одяг' 20 л. label is calculated according to the formula")
    public void RecycledMaterialsFormulaTest(){
        logger.info("Enter 3 in input of 'Вторсировина'");
        orderDetailsPage.enterOnInputNumberOfPackeges(2,"2");
        Assert.assertEquals(orderDetailsPage.getTotalPrice(2),"500 UAH", "Calculating of formula went wrong");
    }

    @Test(testName = "GC-1953 ", description = "GC-1953 ")
    @Description("Verify that a total price of 'Безнадійний одяг' 20 л. label is calculated according to the formula")
    public void TotalORderFormulaTest(){
        logger.info("Enter numbers in total inputs'");
        orderDetailsPage
                .enterOnInputNumberOfPackeges(0,"5")
                .enterOnInputNumberOfPackeges(1,"8")
                .enterOnInputNumberOfPackeges(2,"2");
        Assert.assertEquals(orderDetailsPage.getTextOrderAmount(),"3150 UAH", "Calculating of formula went wrong");
    }

    @Test(testName = "GC-1968 ", description = "GC-1968 ")
    @Description("Verify the behavior of the system when the User enters different data into «Кількість пакетів» field")
    public void BehaviourTestNumberOfPackages(){
        logger.info("Enter numbers in total inputs with the help of spinner'");
       orderDetailsPage.clickOnInputNumberOfPackeges(0).clickUP()
               .clickOnInputNumberOfPackeges(1).clickUP()
               .clickOnInputNumberOfPackeges(2).clickUP().clickDown();
       softAssert.assertEquals(orderDetailsPage.getTextNumberOfPackeges(0), "1", "Spinner doesn't work");
        softAssert.assertEquals(orderDetailsPage.getTextNumberOfPackeges(1), "1", "Spinner doesn't work");
        softAssert.assertEquals(orderDetailsPage.getTextNumberOfPackeges(2), "0", "Spinner doesn't work");
        logger.info("Enter valid numbers in total inputs with the help of keybord'");
        orderDetailsPage
                .enterOnInputNumberOfPackeges(0,"1")
                .enterOnInputNumberOfPackeges(1,"34")
                .enterOnInputNumberOfPackeges(2,"999")
                .enterOnInputNumberOfPackeges(1,"233");
        logger.info("Enter whole numbers in total inputs with the help of keybord'");
        orderDetailsPage
                .enterOnInputNumberOfPackeges(0,"4.1")
                .enterOnInputNumberOfPackeges(1,"8.5")
                .enterOnInputNumberOfPackeges(2,"20.5");
        logger.info("Enter whole numbers in total inputs with the help of keybord'");
        orderDetailsPage
                .enterOnInputNumberOfPackeges(0,"abc")
                .enterOnInputNumberOfPackeges(1,"q")
                .enterOnInputNumberOfPackeges(2,"tuiodkasl");
        logger.info("Enter special characters in total inputs with the help of keybord'");
        orderDetailsPage
                .enterOnInputNumberOfPackeges(0,"@@@")
                .enterOnInputNumberOfPackeges(1,"#")
                .enterOnInputNumberOfPackeges(2,"@#@#@#@");
        softAssert.assertAll();
        // Спитати за провірку введеного в інпут
    }

    @Test(testName = "GC-2058  ", description = "GC-2058  ")
    @Description("Verify that when the user switches to a different window within the GreenCity website, the system shows an alert message")
    public void ErrorMassageAppearsAfterSwitchingWindow(){
        logger.info("Enter numbers in total inputs'");
        orderDetailsPage
                .enterOnInputNumberOfPackeges(0,"5")
                .enterOnInputNumberOfPackeges(1,"8")
                .enterOnInputNumberOfPackeges(2,"2");
        logger.info("Clicked on 'About us'' button");
        MainMenuDropdown mainMenuDropdown = new MainMenuDropdown(driver);
        mainMenuDropdown.clickMenuAbout();
        // Alert doesn't appear
        // TODO Bug report
        softAssert.assertAll();
    }

    @Test(testName = "GC-1974  ", description = "GC-1974  ")
    @Description("Verify that the user can order services when he applies the certificate, bonus, and leaves a comment")
    public void CanToOrderServicesAfterCertificateAndComment(){
        logger.info("Enter numbers in total inputs'");
        orderDetailsPage
                .enterOnInputNumberOfPackeges(0,"5")
                .enterOnInputNumberOfPackeges(1,"8")
                .enterOnInputNumberOfPackeges(2,"2");
        Assert.assertEquals(orderDetailsPage.getTextOrderAmount(),"3150 UAH", "Calculating of formula went wrong");
    }

}
