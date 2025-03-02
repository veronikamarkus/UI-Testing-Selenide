package ui_testing.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    private SelenideElement productTitle = $(".inventory_details_name.large_size");

    public String getProductTitleText(){
        return productTitle.getText();
    }

}
