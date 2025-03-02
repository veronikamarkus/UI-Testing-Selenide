package ui_testing.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private SelenideElement bikeLightItemTitle = $(".cart_item_label #item_0_title_link .inventory_item_name");
    private SelenideElement continueShoppingButton = $("#continue-shopping");
    private SelenideElement bikeLightRemoveButton = $("#remove-sauce-labs-bike-light");
    private SelenideElement checkoutButton = $("#checkout");

    public void clickContinueShoppingButton(){
        continueShoppingButton.click();
    }

    public String getBikeLightItemTitle(){
        return bikeLightItemTitle.getText();
    }

    public void clickRemoveBikeLightButton(){
        bikeLightRemoveButton.click();
    }

    public void clickCheckoutButton(){
        checkoutButton.click();
    }
}
