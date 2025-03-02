package ui_testing.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement usernameField = $("#user-name");
    private SelenideElement passwordField = $("#password");
    private SelenideElement loginButton = $("#login-button");
    private SelenideElement errorMessageField = $("h3");

    public void enterUserName(String username){
        usernameField.setValue(username);
    }

    public void enterPassword(String password){
        passwordField.setValue(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public String getErrorMessageField(){
        return errorMessageField.getText();
    }
}
