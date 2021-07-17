package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import com.softserve.edu.greencity.ui.pages.common.WelcomePage;
import javafx.scene.control.RadioButton;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    private LabelElement ecoStoreLabel;
    private ButtonElement yesWaitingOrderButton;
    private ButtonElement noWaitingOrderButton;
    private InputElement orderNumberInput;
    private LabelElement incorrectOrderMessage;
    private ButtonElement addAnotherOrderNumber;
    private List<AnotherOrderNumberComponents> anotherOrderNumber;
    private InputElement anotherOrderNumberInput;


    public OrderDetailsPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    public void initElements() {
        commentTextarea = new TextAreaElement(driver, OrderDetailsPageLocators.COMMENT_TEXTAREA);
        commentLabel = new LabelElement(driver, OrderDetailsPageLocators.COMMENT_LABEL);
        pointsBalanceLabel = new LabelElement(driver, OrderDetailsPageLocators.POINTS_BALANCE_LABEL);
        certificateInput = new InputElement(driver, OrderDetailsPageLocators.CERTIFICATE_INPUT);
        ecoStoreLabel = new LabelElement(driver, OrderDetailsPageLocators.ECO_STORE_LABEL);
        orderNumberInput = new InputElement(driver, OrderDetailsPageLocators.ORDER_NUMBER_INPUT);
        incorrectOrderMessage = new LabelElement(driver, OrderDetailsPageLocators.INCORRECT_ORDER_NUMBER_MESSAGE);
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

    public ButtonElement getCancelCertificateButton() {
        addCertifircateButton = new ButtonElement(driver, OrderDetailsPageLocators.ADD_CERTIFICATE_BUTTON);
        return addCertifircateButton;
    }

    public LabelElement getCertificateMessage() {
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
        return orderNumberInput;
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

    public OrderDetailsPage clickYesWaitingForAnOrderButton(){
        getYesWaitingOrderButton().click();
        return this;
    }

    public OrderDetailsPage inputOrderNumber(String orderNumber){
        getOrderNumberInput().clearInput();
        getOrderNumberInput().sendKeys(orderNumber);
        return this;
    }

    public OrderDetailsPage clickAnotherOrderNumberButton(){
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
