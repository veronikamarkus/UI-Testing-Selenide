package ui_testing;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import ui_testing.page.CartPage;
import ui_testing.page.LoginPage;
import ui_testing.page.ProductPage;
import ui_testing.page.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class ProductsPageTests {

    @BeforeAll
    public static void configureSelenide(){
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--guest"); // To get rid of 'change password' pop up.

        Configuration.browserCapabilities = options;

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
       // Configuration.holdBrowserOpen = true;
        Configuration.headless = true;
        Configuration.baseUrl = "https://www.saucedemo.com";
    }

    @BeforeEach
    public void openPageAndLogin(){
        open("/");

        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }

    @AfterEach
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @Test
    public void givenProductsPage_whenClickItemTitle_thenShouldSeeItemsTitle(){
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickBikeLightItemTitle();

        ProductPage productPage = new ProductPage();

        Assertions.assertThat(productPage.getProductTitleText()).isEqualTo("Sauce Labs Bike Light");
    }

    @Test
    public void givenProductsPage_whenClickItemImage_thenShouldSeeItemsTitle(){
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickBikeLightItemImage();

        ProductPage productPage = new ProductPage();

        Assertions.assertThat(productPage.getProductTitleText()).isEqualTo("Sauce Labs Bike Light");
    }

    @Test
    public void givenProductsAddedToCart_whenClickCart_thenShouldSeeAddedItemTitle(){
        ProductsPage productsPage = new ProductsPage();
        productsPage.clickBikeLightAddToCartButton();
        productsPage.clickCartButton();

        CartPage cartPage = new CartPage();

        Assertions.assertThat(cartPage.getBikeLightItemTitle()).isEqualTo("Sauce Labs Bike Light");
    }

    @Test
    public void givenProductsPage_whenSortByNameDesc_thenShouldSeeSortedItemsByNameDesc() {
        ProductsPage productsPage = new ProductsPage();

        productsPage.sortByNameDescending();

        List<String> actualProductNames = productsPage.getProductNames();

        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        actualProductNames.sort(Collections.reverseOrder());

        Assertions.assertThat(actualProductNames).isEqualTo(expectedProductNames);
    }

    @Test
    public void givenProductsPage_whenSortByNameAsc_thenShouldSeeSortedItemsByNameAsc() {
        ProductsPage productsPage = new ProductsPage();

        productsPage.sortByNameAscending();

        List<String> actualProductNames = productsPage.getProductNames();

        List<String> expectedProductNames = new ArrayList<>(actualProductNames);
        Collections.sort(expectedProductNames);

        Assertions.assertThat(actualProductNames).isEqualTo(expectedProductNames);
    }

    @Test
    public void givenProductsPage_whenSortByPriceDesc_thenShouldSeeSortedItemsByPriceDesc() {
        ProductsPage productsPage = new ProductsPage();

        productsPage.sortByPriceDescending();

        List<Double> productPrices = productsPage.getProductPrices();

        List<Double> expectedPrices = new ArrayList<>(productPrices);

        expectedPrices.sort(Collections.reverseOrder());

        Assertions.assertThat(productPrices).isEqualTo(expectedPrices);
    }

    @Test
    public void givenProductsPage_whenSortByPriceAsc_thenShouldSeeSortedItemsByPriceAsc() {
        ProductsPage productsPage = new ProductsPage();

        productsPage.sortByPriceAscending();

        List<Double> productPrices = productsPage.getProductPrices();

        List<Double> expectedPrices = new ArrayList<>(productPrices);

        Collections.sort(expectedPrices);

        Assertions.assertThat(productPrices).isEqualTo(expectedPrices);
    }
}
