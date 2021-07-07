package com.softserve.edu.greencity.ui.pages.ubs;

import com.softserve.edu.greencity.ui.elements.ButtonElement;
import com.softserve.edu.greencity.ui.elements.InputElement;
import com.softserve.edu.greencity.ui.elements.LabelElement;
import com.softserve.edu.greencity.ui.elements.TextAreaElement;
import com.softserve.edu.greencity.ui.locators.ubs.OrderDetailsPageLocators;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
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
    private ButtonElement additionalactivateCertificateButton;


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

    public PersonalDataPage clickOnNextButton() {
        getNextButton().click();
        return new PersonalDataPage(driver);
    }

    public CancelOrderPopupComponent clickOnCancelButton() {
        getCancelButton().click();
        return new CancelOrderPopupComponent(driver, this);
    }

}
