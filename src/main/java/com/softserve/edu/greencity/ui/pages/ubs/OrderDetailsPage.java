package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
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


    public OrderDetailsPage(WebDriver webDriver) {
        super(webDriver);
        initElements();
    }

    public void initElements() {
        commentTextarea = new TextAreaElement(driver, OrderDetailsPageLocators.COMMENT_TEXTAREA);
        commentLabel = new LabelElement(driver, OrderDetailsPageLocators.COMMENT_LABEL);
        pointsBalanceLabel = new LabelElement(driver, OrderDetailsPageLocators.POINTS_BALANCE_LABEL);
        certificateInput = new InputElement(driver, OrderDetailsPageLocators.CERTIFICATE_INPUT);
        activateCertificateButton = new ButtonElement(driver, OrderDetailsPageLocators.ACTIVATE_BUTTON);
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

    private List<AdditionalCertificatesComponents> getAdditionalCertificates() {
        additionalCertificates = new ArrayList<>();
        for (WebElement webElement : getCertificates()) {
            additionalCertificates.add(new AdditionalCertificatesComponents(driver, webElement));
        }
        return additionalCertificates;
    }

    private List<WebElement> getCertificates() {
        try {
            return waitsSwitcher.setExplicitWait(3,
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(OrderDetailsPageLocators.ADDITIONAL_CERTIFICATES.getPath()));
        } catch (TimeoutException e) {
            return new ArrayList<>();
        }
    }

    private AdditionalCertificatesComponents findCertificateByNumber(String sertificate) {
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

    private TextAreaElement getCommentTextarea() {
        return commentTextarea;
    }

    private LabelElement getCommentLabel() {
        return commentLabel;
    }

    private LabelElement getPointsBalanceLabel() {
        return pointsBalanceLabel;
    }

    private InputElement getCertificateInput() {
        return certificateInput;
    }

    private ButtonElement getAddCertifircateButton() {
        addCertifircateButton = new ButtonElement(driver, OrderDetailsPageLocators.ADD_CERTIFICATE_BUTTON);
        return addCertifircateButton;
    }

    private ButtonElement getCancelCertifircateButton() {
        addCertifircateButton = new ButtonElement(driver, OrderDetailsPageLocators.ADD_CERTIFICATE_BUTTON);
        return addCertifircateButton;
    }

    private LabelElement getCertificateMessage() {
        certificateMessage = new LabelElement(driver, OrderDetailsPageLocators.CERTIFICATE_MESSAGE);
        return certificateMessage;
    }

    private LabelElement getCommentAlertLabel() {
        commentAlertLabel = new LabelElement(driver, OrderDetailsPageLocators.COMMENT_ALERT_LABEL);
        return commentAlertLabel;
    }
    private OrderDetailsPage inputComment(String comment) {
        getCommentTextarea().clearText();
        getCommentTextarea().enterText(comment);
        return this;
    }
    private OrderDetailsPage inputCertificate(String certificate){
        getCertificateInput().clearInput();
        getCertificateInput().sendKeys(certificate);
        return this;
    }
    private OrderDetailsPage clickActivateButton(){
        getAddCertifircateButton().click();
        return this;
    }
    private OrderDetailsPage clickCancelCertificateButton(){
        getAddCertifircateButton().click();
        return this;
    }
    private OrderDetailsPage clickAddCertificateButton(){
        getAddCertifircateButton().click();
        getAdditionalCertificates();
        return this;
    }

    public PersonalDataPage clickOnNextButton() {
        getNextButton().click();
        return new PersonalDataPage(driver);
    }

    public CancelOrderPopupComponent clickOnCancelButton() {
        getCancelButton().click();
        return new CancelOrderPopupComponent(driver, this);
    }

}
