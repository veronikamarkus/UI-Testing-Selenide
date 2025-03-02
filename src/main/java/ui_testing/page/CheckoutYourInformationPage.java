package ui_testing.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutYourInformationPage {
    private SelenideElement title = $(".title");
    private SelenideElement firstName = $("#first-name");
    private SelenideElement lastName = $("#last-name");
    private SelenideElement postalCode = $("#postal-code");
    private SelenideElement continueButton = $("#continue");

    private SelenideElement errorMessageField = $("h3");

    public String getTitleText(){
        return title.getText();
    }

    public void enterFirstName(String _firstName){
        firstName.setValue(_firstName);
    }

    public void enterLastName(String _lastName){
        lastName.setValue(_lastName);
    }

    public void enterPostalCode(String _postalCode) {postalCode.setValue(_postalCode);}
    public void clickContinueButton(){
        continueButton.click();
    }

    public String getErrorMessageFieldText(){
        return errorMessageField.getText();
    }
}
