package ui_testing.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {
    private SelenideElement title = $(".title");
    private SelenideElement backHomeButton = $("#back-to-products");

    public String getTitleText(){
        return title.getText();
    }

    public void clickBackHomeButton(){
        backHomeButton.click();
    }
}
