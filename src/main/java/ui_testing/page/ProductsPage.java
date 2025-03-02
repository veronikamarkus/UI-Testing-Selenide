package ui_testing.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {
    private SelenideElement pageHeadingField = $(".title");
    private SelenideElement bikeLightItemTitle = $("#item_0_title_link .inventory_item_name");
    private SelenideElement bikeLightItemImage = $("#item_0_img_link .inventory_item_img");
    private SelenideElement bikeLightAddToCartButton = $("#add-to-cart-sauce-labs-bike-light");
    private SelenideElement cartButton = $(".shopping_cart_link");
    private ElementsCollection productNames = $$(".inventory_item_name");
    private SelenideElement sortDropdown = $(".product_sort_container");
    private ElementsCollection productPrices = $$(".inventory_item_price");

    public String getPageHeadingText(){
        return pageHeadingField.getText();
    }

    public void clickBikeLightItemTitle(){
        bikeLightItemTitle.click();
    }

    public void clickBikeLightItemImage(){
        bikeLightItemImage.click();
    }

    public void clickBikeLightAddToCartButton(){
        bikeLightAddToCartButton.click();
    }

    public void clickCartButton(){
        cartButton.click();
    }

    public List<String> getProductNames() {
        return productNames.asFixedIterable().stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public void sortByNameDescending() {
        sortDropdown.selectOption("Name (Z to A)");
    }

    public void sortByNameAscending() {
        sortDropdown.selectOption("Name (A to Z)");
    }

    public List<Double> getProductPrices() {
        return productPrices.asFixedIterable().stream()
                .map(price -> Double.parseDouble(price.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public void sortByPriceAscending() {
        sortDropdown.selectOption("Price (low to high)");
    }

    public void sortByPriceDescending() {
        sortDropdown.selectOption("Price (high to low)");
    }
}
