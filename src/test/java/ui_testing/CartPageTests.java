package ui_testing;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import ui_testing.page.CartPage;
import ui_testing.page.CheckoutYourInformationPage;
import ui_testing.page.LoginPage;
import ui_testing.page.ProductsPage;

import static com.codeborne.selenide.Selenide.open;

public class CartPageTests {
    @BeforeAll
    public static void configureSelenide(){
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--guest"); // To get rid of 'change password' pop up.

        Configuration.browserCapabilities = options;

        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        // Configuration.headless = true;
        Configuration.baseUrl = "https://www.saucedemo.com";
    }

    @BeforeEach
    public void openPageAndLogin(){
        open("/");

        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage();
        productsPage.clickBikeLightAddToCartButton();
        productsPage.clickCartButton();
    }

    @AfterEach
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @Test
    public void givenCartPage_whenClickContinueShopping_thenShouldSeeProductTitle(){
        CartPage cartPage = new CartPage();

        cartPage.clickContinueShoppingButton();

        ProductsPage productsPage = new ProductsPage();

        Assertions.assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }

    @Test
    public void givenCartPage_whenClickRemoveItem_thenShouldNotSeeItem(){
        CartPage cartPage = new CartPage();

        cartPage.clickRemoveBikeLightButton();

        try {
            cartPage.getBikeLightItemTitle();
            throw new AssertionError("The element was found, but it was expected to be missing.");

        } catch (com.codeborne.selenide.ex.ElementNotFound e) {
            System.out.println("Element not found as expected: " + e.getMessage());
        }
    }

    @Test
    public void givenCartPage_whenClickCheckout_thenShouldSeeYourInformationTitle(){
        CartPage cartPage = new CartPage();

        cartPage.clickCheckoutButton();

        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();

        Assertions.assertThat(checkoutYourInformationPage.getTitleText())
                .isEqualTo("Checkout: Your Information");
    }
}
