package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderDetailsPage extends UBSCourierBasePage {

    private ButtonElement cancelButton;
    private ButtonElement nextButton;
    private LabelElement commentLabel;
    private TextAreaElement commentTextarea;
    private LabelElement commentAlertLabel;
    private LabelElement pointsBalanceLabel;
    private ButtonElement addCertifircateButton;
    private InputElement certificateInput;
    private ButtonElement activateCertificateButton;
    private LabelElement certificateMessage;
    private List<AdditionalCertificatesComponents> additionalCertificates;
    private InputElement additionalCertificateInput;
    private ButtonElement additionalActivateCertificateButton;
    private List<ServicesComponents> servicesComponents;
    private LabelElement orderAmount;
    private LabelElement amountDue;
    private List<WebElement> numberOfPackeges;
    private List<WebElement> totalLabels;

    public OrderDetailsPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    public void initElements() {
        commentTextarea = new TextAreaElement(driver, OrderDetailsPageLocators.COMMENT_TEXTAREA);
        commentLabel = new LabelElement(driver, OrderDetailsPageLocators.COMMENT_LABEL);
        pointsBalanceLabel = new LabelElement(driver, OrderDetailsPageLocators.POINTS_BALANCE_LABEL);
        certificateInput = new InputElement(driver, OrderDetailsPageLocators.CERTIFICATE_INPUT);
       }

       public OrderDetailsPage clickOnInputNumberOfPackeges(int index){
        numberOfPackeges.get(index).click();
        return this;
       }

       public String getTextOrderAmount(){
        String amount = getOrderAmount().getText();
        return amount;
       }

    public String getTextAmountDue(){
        String amount = getAmountDue().getText();
        return amount;
    }

    public OrderDetailsPage clickUP(){
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ARROW_UP).build().perform();
        return this;
    }

    public OrderDetailsPage clickDown(){
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.ARROW_DOWN).build().perform();
        return this;
    }

    public OrderDetailsPage EnterOnInputNumberOfPackeges(int index){
        numberOfPackeges.get(index).sendKeys("20");
        return this;
    }

    public String getTotalPrice(int index){
        String total = totalLabels.get(index).getText();
        return total;
    }

    private ButtonElement getNextButton() {
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

    public LabelElement getOrderAmount(){
        if (orderAmount == null){
            orderAmount = new LabelElement(driver,OrderDetailsPageLocators.ORDER_AMOUT);
        }
        return orderAmount;
    }

    public LabelElement getAmountDue(){
        if (amountDue == null){
            amountDue = new LabelElement(driver,OrderDetailsPageLocators.AMOUNT_DUE);
        }
        return amountDue;
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

    public List<WebElement> getNumberOfPackeges(){
        if (numberOfPackeges == null) {
            numberOfPackeges = new ArrayList<>();
            numberOfPackeges = driver.findElements(OrderDetailsPageLocators.NUMBER_OF_PACKEGES.getPath());
        }
        return numberOfPackeges;
    }

    public List<WebElement> getTotalLabels(){
        if (totalLabels == null) {
            totalLabels = new ArrayList<>();
            totalLabels = driver.findElements(OrderDetailsPageLocators.TOTAL.getPath());
        }
        return numberOfPackeges;
    }
    public List<AdditionalCertificatesComponents> getAdditionalCertificates() {
        additionalCertificates = new ArrayList<>();
        for (WebElement webElement : getCertificates()) {
            additionalCertificates.add(new AdditionalCertificatesComponents(driver, webElement));
        }
        return additionalCertificates;
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

    public TextAreaElement getCommentTextarea() {
        return commentTextarea;
    }

    public LabelElement getCommentLabel() {
        return commentLabel;
    }

    public LabelElement getPointsBalanceLabel() {
        return pointsBalanceLabel;
    }

    public InputElement getCertificateInput() {
        return certificateInput;
    }

    public ButtonElement getAddCertifircateButton() {
        addCertifircateButton = new ButtonElement(driver, OrderDetailsPageLocators.ADD_CERTIFICATE_BUTTON);
        return addCertifircateButton;
    }

    public ButtonElement getCancelCertifircateButton() {
        addCertifircateButton = new ButtonElement(driver, OrderDetailsPageLocators.ADD_CERTIFICATE_BUTTON);
        return addCertifircateButton;
    }

    public LabelElement getCertificateMessage() {

        certificateMessage = new LabelElement(driver, OrderDetailsPageLocators.CERTIFICATE_MESSAGE);
        return certificateMessage;
    }

    public WebElement getCommentAlertLabel() {
       //commentAlertLabel = new LabelElement(driver, OrderDetailsPageLocators.COMMENT_ALERT_LABEL);
        return waitsSwitcher.setExplicitWait(40,ExpectedConditions
                .visibilityOf(searchElementByCss(OrderDetailsPageLocators.COMMENT_ALERT_LABEL.getPath())));
    }
    public OrderDetailsPage inputComment(String comment) {
        getCommentTextarea().clearText();
        getCommentTextarea().enterText(comment);
        return this;
    }
    public OrderDetailsPage inputCertificate(String certificate){
        getCertificateInput().clearInput();
        getCertificateInput().sendKeys(certificate);
        return this;
    }
    public ButtonElement getActivateCertificateButton(){
        activateCertificateButton = new ButtonElement(driver, OrderDetailsPageLocators.ACTIVATE_BUTTON);
        return  activateCertificateButton;
    }
    public OrderDetailsPage clickActivateButton(){
        getActivateCertificateButton().click();
        return this;
    }
    public OrderDetailsPage clickCancelCertificateButton(){
        getActivateCertificateButton().click();
        return this;
    }
    public String getActivateButtonColor(){
      return driver.findElement(OrderDetailsPageLocators.ACTIVATE_BUTTON.getPath()).getCssValue("background");
    }
    public boolean isActicateButtonActive(){
        return getActivateButtonColor().equalsIgnoreCase("#13aa57");
    }
    public OrderDetailsPage clickAddCertificateButton(){
        getAddCertifircateButton().click();
        getAdditionalCertificates();
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
