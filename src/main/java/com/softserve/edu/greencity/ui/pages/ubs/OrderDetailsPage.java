package com.softserve.edu.greencity.ui.pages.ubs;


import com.softserve.edu.greencity.data.UBS.UBSDataStrings;
import com.softserve.edu.greencity.data.UBS.Certificates;
import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderDetailsPage extends UBSCourierBasePage {

    private ButtonElement cancelButton;
    private ButtonElement nextButton;
    ///Region [Comments]
    private LabelElement commentLabel;
    private TextAreaElement commentTextarea;
    private LabelElement commentAlertLabel;
    ///Endregion
    private LabelElement pointsBalanceLabel;
    private ButtonElement addCertifircateButton;
    private InputElement certificateInput;
    private ButtonElement activateCertificateButton;
    private LabelElement certificateMessage;
    private List<AdditionalCertificatesComponents> additionalCertificates;
    private InputElement additionalCertificateInput;
    private ButtonElement additionalActivateCertificateButton;

    private LabelElement ecoStoreLabel;
    private ButtonElement yesWaitingOrderButton;
    private ButtonElement noWaitingOrderButton;
    private InputElement orderNumberInput;
    private LabelElement incorrectOrderMessage;
    private ButtonElement addAnotherOrderNumber;
    private List<AnotherOrderNumberComponents> anotherOrderNumber;
    private InputElement anotherOrderNumberInput;

    private List<ServicesComponents> servicesComponents;
    private LabelElement orderAmount;
    private LabelElement amountDue;
    private LabelElement sertificateLabel;
    private List<WebElement> numberOfPackeges;
    private List<WebElement> totalLabels;


    public OrderDetailsPage(WebDriver webDriver) {
        super(webDriver);
        initOrderDetailsElements();
    }

    public void initOrderDetailsElements() {
        commentTextarea = new TextAreaElement(driver, OrderDetailsPageLocators.COMMENT_TEXTAREA);
        commentLabel = new LabelElement(driver, OrderDetailsPageLocators.COMMENT_LABEL);
        pointsBalanceLabel = new LabelElement(driver, OrderDetailsPageLocators.POINTS_BALANCE_LABEL);
        certificateInput = new InputElement(driver, OrderDetailsPageLocators.CERTIFICATE_INPUT);
        ecoStoreLabel = new LabelElement(driver, OrderDetailsPageLocators.ECO_STORE_LABEL);
    }

    public OrderDetailsPage clickOnInputNumberOfPackeges(int index) {
        numberOfPackeges.get(index).click();
        return this;
    }

    public String getTextOrderAmount() {
        String amount = getOrderAmount().getText();
        return amount;
    }

    public String getTextAmountDue() {
        String amount = getAmountDue().getText();
        return amount;
    }
    public int getAmountDueNumber(){
        waitsSwitcher.sleep(2000);
        String [] array = getTextAmountDue().replace("-","").split(" ");
        return Integer.parseInt(array[0]);
    }
    public int getCertificateLabelNumber(){
        waitsSwitcher.sleep(1000);
        String[] array = getCertificateLabel().getText().replace("-","").split(" ");
        return Integer.parseInt(array[0]);
    }

    public OrderDetailsPage clickUP() {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ARROW_UP).build().perform();
        return this;
    }

    public OrderDetailsPage clickDown() {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ARROW_DOWN).build().perform();
        return this;
    }

    public OrderDetailsPage EnterOnInputNumberOfPackeges(int index) {
        numberOfPackeges.get(index).sendKeys();
        return this;
    }

    public String getTotalPrice(int index) {
        String total = totalLabels.get(index).getText();
        return total;
    }

    public ButtonElement getNextButton() {
        if (nextButton == null) {
            nextButton = new ButtonElement(driver, OrderDetailsPageLocators.NEXT);
        }
        return nextButton;
    }

    private ButtonElement getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new ButtonElement(driver, OrderDetailsPageLocators.CANCEL);
        }
        return cancelButton;
    }

    public LabelElement getOrderAmount() {
        if (orderAmount == null) {
            orderAmount = new LabelElement(driver, OrderDetailsPageLocators.ORDER_AMOUT);
        }
        return orderAmount;
    }

    public LabelElement getAmountDue() {
        if (amountDue == null) {
            amountDue = new LabelElement(driver, OrderDetailsPageLocators.AMOUNT_DUE);
        }
        return amountDue;
    }
    public  LabelElement getCertificateLabel(){
       sertificateLabel = new LabelElement( driver,OrderDetailsPageLocators.SERTIFICATE_LABEL);
        return sertificateLabel;
    }

    public List<ServicesComponents> getServicesComponents() {
        servicesComponents = new ArrayList<>();
        for (WebElement webElement : getServices()) {
            servicesComponents.add(new ServicesComponents(driver, webElement));
        }
        return servicesComponents;
    }

    public List<WebElement> getServices() {
        try {
            return waitsSwitcher.setExplicitWait(3,
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(OrderDetailsPageLocators.SERVICES.getPath()));
        } catch (TimeoutException e) {
            return new ArrayList<>();
        }
    }

    public List<WebElement> getNumberOfPackeges() {
        if (numberOfPackeges == null) {
            numberOfPackeges = new ArrayList<>();
            numberOfPackeges = driver.findElements(OrderDetailsPageLocators.NUMBER_OF_PACKEGES.getPath());
        }
        return numberOfPackeges;
    }

    public OrderDetailsPage fillAllFieldsForServices(int value) {
        logger.info("fill all fields for services");
        for (ServicesComponents servicesComponents : getServicesComponents()) {
            servicesComponents.getInput().sendKeys(Integer.toString(value));
        }
        return this;
    }

    public List<WebElement> getTotalLabels() {
        if (totalLabels == null) {
            totalLabels = new ArrayList<>();
            totalLabels = driver.findElements(OrderDetailsPageLocators.TOTAL.getPath());
        }
        return totalLabels;
    }
    public int getTotalSum(){
        int sum = 0;
        for (WebElement element:getTotalLabels()) {
            String[] array = element.getText().split( " ");
            sum+= Integer.parseInt(array[0]);
        }
        return sum;
    }

    public List<AdditionalCertificatesComponents> getAdditionalCertificates() {
        additionalCertificates = new ArrayList<>();
        for (WebElement webElement : getCertificates()) {
            additionalCertificates.add(new AdditionalCertificatesComponents(driver, webElement));
        }
        return additionalCertificates;
    }
    public OrderDetailsPage activateCertificateByPosition(int number, String sertificate){
        getAdditionalCertificates().get(number).getCertificateInput().sendKeys(sertificate);
        getAdditionalCertificates().get(number).getActivateCertificateButton().click();
        return this;
    }

    public List<WebElement> getCertificates() {
        try {
            return waitsSwitcher.setExplicitWait(3,
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(OrderDetailsPageLocators.ADDITIONAL_CERTIFICATES.getPath()));
        } catch (TimeoutException e) {
            return new ArrayList<>();
        }
    }

    public AdditionalCertificatesComponents findCertificateByNumber(String sertificate) {
        //todo remove possible return null!!!
        AdditionalCertificatesComponents component = null;
        Iterator<AdditionalCertificatesComponents> iterator = getAdditionalCertificates().iterator();
        while (iterator.hasNext()) {
            AdditionalCertificatesComponents next = iterator.next();
            if (next.getCertificateInput().getText().equalsIgnoreCase(sertificate)) {
                component = next;
            }
        }
        return component;
    }

    public List<AnotherOrderNumberComponents> getAnotherOrderNumber() {
        anotherOrderNumber = new ArrayList<>();
        for (WebElement webElement : getOrderNumber()) {
            anotherOrderNumber.add(new AnotherOrderNumberComponents(driver, webElement));
        }
        return anotherOrderNumber;
    }

    public List<WebElement> getOrderNumber() {
        try {
            return waitsSwitcher.setExplicitWait(4,
                    ExpectedConditions.visibilityOfAllElementsLocatedBy
                            (OrderDetailsPageLocators.ANOTHER_ORDER_NUMBER.getPath()));
        } catch (TimeoutException e) {
            return new ArrayList<>();
        }
    }

    public TextAreaElement getCommentTextarea() {
        return commentTextarea;
    }

    public LabelElement getCommentLabel() {
        return commentLabel;
    }

    public WebElement getCommentAlertLabel() {
        //commentAlertLabel = new LabelElement(driver, OrderDetailsPageLocators.COMMENT_ALERT_LABEL);
        return waitsSwitcher.setExplicitWait(40, ExpectedConditions
                .visibilityOf(searchElementByCss(OrderDetailsPageLocators.COMMENT_ALERT_LABEL.getPath())));
    }

    public LabelElement getPointsBalanceLabel() {
        return pointsBalanceLabel;
    }

    public InputElement getCertificateInput() {
        return certificateInput;
    }

    public ButtonElement getAddCertificateButton() {
        addCertifircateButton = new ButtonElement(driver, OrderDetailsPageLocators.ADD_CERTIFICATE_BUTTON);
        return addCertifircateButton;
    }
    public int getDiscountFromMessage(String message){
        String[] array = message.split(" ");
        return Integer.parseInt(array[2]);

    }
    public ButtonElement getCancelCertificateButton() {
        activateCertificateButton = new ButtonElement(driver, OrderDetailsPageLocators.ACTIVATE_BUTTON);
        return activateCertificateButton;
    }

    public Boolean isCancelButtonActive(){
        getCancelCertificateButton();
        return (activateCertificateButton.getText().equals(UBSDataStrings.CANCEL_ENG.getMessage())
                        || activateCertificateButton.getText().equals(UBSDataStrings.CANCEL_RU.getMessage())
                            || activateCertificateButton.getText().equals(UBSDataStrings.CANCEL_UA.getMessage()));
    }
    public LabelElement getCertificateMessage() {
        waitsSwitcher.sleep(1500);
        certificateMessage = new LabelElement(driver, OrderDetailsPageLocators.CERTIFICATE_MESSAGE);
        return certificateMessage;
    }

    public ButtonElement getActivateCertificateButton() {
        activateCertificateButton = new ButtonElement(driver, OrderDetailsPageLocators.ACTIVATE_BUTTON);
        return activateCertificateButton;
    }

    public String getActivateButtonColor() {
        return driver.findElement(OrderDetailsPageLocators.ACTIVATE_BUTTON.getPath()).getCssValue("background");
    }

    public boolean isActicateButtonActive() {
        return getActivateButtonColor().equalsIgnoreCase("#13aa57");
    }

    public ButtonElement getYesWaitingOrderButton() {
        yesWaitingOrderButton = new ButtonElement(driver, OrderDetailsPageLocators.YES_WAITING_ORDER_RADIO_BUTTON);
        return yesWaitingOrderButton;
    }

    public ButtonElement getNoWaitingOrderButton() {
        noWaitingOrderButton = new ButtonElement(driver, OrderDetailsPageLocators.NO_WAITING_ORDER_RADIO_BUTTON);
        return noWaitingOrderButton;
    }

    public InputElement getOrderNumberInput() {
        orderNumberInput = new InputElement(driver, OrderDetailsPageLocators.ORDER_NUMBER_INPUT);
        return orderNumberInput;
    }

    public InputElement getOrderNumberSecondInput() {
        orderNumberInput = new InputElement(driver, OrderDetailsPageLocators.ORDER_NUMBER_SECOND_INPUT);
        return orderNumberInput;
    }

    public LabelElement getIncorrectOrderMessage() {
        incorrectOrderMessage = new LabelElement(driver, OrderDetailsPageLocators.INCORRECT_ORDER_NUMBER_MESSAGE);
        return incorrectOrderMessage;
    }

    public ButtonElement getAddAnotherOrderNumberButton() {
        addAnotherOrderNumber = new ButtonElement(driver, OrderDetailsPageLocators.ADD_ANOTHER_ORDER_BUTTON);
        return addAnotherOrderNumber;
    }

    public OrderDetailsPage inputComment(String comment) {
        getCommentTextarea().clearText();
        getCommentTextarea().enterText(comment);
        return this;
    }

    public OrderDetailsPage inputCertificate(String certificate) {
        getCertificateInput().clearInput();
        getCertificateInput().sendKeys(certificate);
        return this;
    }

    public OrderDetailsPage clickActivateButton() {
        getActivateCertificateButton().click();
        return this;
    }

    public OrderDetailsPage clickCancelCertificateButton() {
        getActivateCertificateButton().click();
        return this;
    }

    public OrderDetailsPage clickAddCertificateButton() {
        getAddCertificateButton().click();
        getAdditionalCertificates();
        return this;
    }

    public OrderDetailsPage clickNoWaitingForAnOrderButton() {
        getNoWaitingOrderButton().click();
        return this;
    }

    public OrderDetailsPage clickYesWaitingForAnOrderButton() {
        getYesWaitingOrderButton().click();
        return this;
    }

    public OrderDetailsPage inputOrderNumber(String orderNumber) {
        getOrderNumberInput().clearInput();
        getOrderNumberInput().sendKeys(orderNumber);
        return this;
    }

    public OrderDetailsPage inputSecondOrderNumber(String orderNumber) {
        getOrderNumberSecondInput().clearInput();
        getOrderNumberSecondInput().sendKeys(orderNumber);
        return this;
    }

    public OrderDetailsPage clickAnotherOrderNumberButton() {
        getAddAnotherOrderNumberButton().click();
        getAnotherOrderNumber();
        return this;
    }

    public PersonalDataPage clickOnNextButton() {
        getNextButton().click();
        return new PersonalDataPage(driver);
    }

    public CancelOrderPopupComponent clickOnCancelButtonWhenChangesPresent() {
        getCancelButton().click();
        return new CancelOrderPopupComponent(driver, this, new WelcomePage(driver));
    }

    public WelcomePage clickOnCancelButtonWhenChangesAbsent() {
        getCancelButton().click();
        return new WelcomePage(driver);
    }
}
