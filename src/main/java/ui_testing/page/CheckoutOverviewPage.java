package ui_testing.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutOverviewPage {
    private SelenideElement title = $(".title");
    private SelenideElement bikeLightItemTitle = $("#item_0_title_link .inventory_item_name");
    private SelenideElement cancelButton = $("#cancel");
    private SelenideElement finishButton = $("#finish");

    public String getTitleText(){
        return title.getText();
    }

    public String getBikeLightItemTitle(){
        return bikeLightItemTitle.getText();
    }

    public void clickCancelButton(){
        cancelButton.click();
    }

    public void clickFinishButton(){
        finishButton.click();
    }
}
