package ui_testing;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import ui_testing.page.*;

import static com.codeborne.selenide.Selenide.open;

public class CheckoutTests {
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
    public void goToCheckout(){
        open("/");

        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage();
        productsPage.clickBikeLightAddToCartButton();
        productsPage.clickCartButton();

        CartPage cartPage = new CartPage();
        cartPage.clickCheckoutButton();
    }

    @AfterEach
    public void closeBrowser() {
        Selenide.closeWebDriver();
    }

    @Test
    public void givenCorrectPersonalInformation_whenClickContinue_ShouldSeeCheckoutOverviewTitle(){
        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutYourInformationPage.enterFirstName("Sponge");
        checkoutYourInformationPage.enterLastName("Bob");
        checkoutYourInformationPage.enterPostalCode("13432");
        checkoutYourInformationPage.clickContinueButton();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();

        Assertions.assertThat(checkoutOverviewPage.getTitleText()).isEqualTo("Checkout: Overview");
    }

    @Test
    public void givenEmptyFirstName_whenClickContinue_ShouldSeeErrorMessage(){
        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutYourInformationPage.enterFirstName("");
        checkoutYourInformationPage.enterLastName("Bob");
        checkoutYourInformationPage.enterPostalCode("13432");
        checkoutYourInformationPage.clickContinueButton();

        Assertions.assertThat(checkoutYourInformationPage.getErrorMessageFieldText())
                .isEqualTo("Error: First Name is required");
    }

    @Test
    public void givenEmptyLastName_whenClickContinue_ShouldSeeErrorMessage(){
        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutYourInformationPage.enterFirstName("Sponge");
        checkoutYourInformationPage.enterLastName("");
        checkoutYourInformationPage.enterPostalCode("13432");
        checkoutYourInformationPage.clickContinueButton();

        Assertions.assertThat(checkoutYourInformationPage.getErrorMessageFieldText())
                .isEqualTo("Error: Last Name is required");
    }

    @Test
    public void givenEmptyPostalCode_whenClickContinue_ShouldSeeErrorMessage(){
        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutYourInformationPage.enterFirstName("Sponge");
        checkoutYourInformationPage.enterLastName("Bob");
        checkoutYourInformationPage.enterPostalCode("");
        checkoutYourInformationPage.clickContinueButton();

        Assertions.assertThat(checkoutYourInformationPage.getErrorMessageFieldText())
                .isEqualTo("Error: Postal Code is required");
    }

    //This test fails
    @Test
    public void givenNameIsWhitespace_whenClickContinue_ShouldSeeErrorMessage(){
        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutYourInformationPage.enterFirstName(" ");
        checkoutYourInformationPage.enterLastName("Bob");
        checkoutYourInformationPage.enterPostalCode("1234");
        checkoutYourInformationPage.clickContinueButton();

        Assertions.assertThat(checkoutYourInformationPage.getErrorMessageFieldText())
                .isEqualTo("Error: First Name is required");
    }

    @Test
    public void givenCorrectCredentials_whenClickContinue_ShouldSeeCorrectItemTitle(){
        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutYourInformationPage.enterFirstName("Sponge");
        checkoutYourInformationPage.enterLastName("Bob");
        checkoutYourInformationPage.enterPostalCode("1234");
        checkoutYourInformationPage.clickContinueButton();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();

        Assertions.assertThat(checkoutOverviewPage.getBikeLightItemTitle()).isEqualTo("Sauce Labs Bike Light");
    }

    @Test
    public void givenCheckoutOverviewPage_whenClickCancel_ShouldSeeProductsTitle(){
        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutYourInformationPage.enterFirstName("Sponge");
        checkoutYourInformationPage.enterLastName("Bob");
        checkoutYourInformationPage.enterPostalCode("1234");
        checkoutYourInformationPage.clickContinueButton();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutOverviewPage.clickCancelButton();

        ProductsPage productsPage = new ProductsPage();

        Assertions.assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }

    @Test
    public void givenCheckoutOverviewPage_whenClickFinish_ShouldSeeCheckoutCompleteTitle(){
        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutYourInformationPage.enterFirstName("Sponge");
        checkoutYourInformationPage.enterLastName("Bob");
        checkoutYourInformationPage.enterPostalCode("1234");
        checkoutYourInformationPage.clickContinueButton();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutOverviewPage.clickFinishButton();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

        Assertions.assertThat(checkoutCompletePage.getTitleText()).isEqualTo("Checkout: Complete!");
    }

    @Test
    public void givenCheckoutCompletePage_whenClickBackHome_ShouldSeeProductsTitle(){
        CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage();
        checkoutYourInformationPage.enterFirstName("Sponge");
        checkoutYourInformationPage.enterLastName("Bob");
        checkoutYourInformationPage.enterPostalCode("1234");
        checkoutYourInformationPage.clickContinueButton();

        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage();
        checkoutOverviewPage.clickFinishButton();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();
        checkoutCompletePage.clickBackHomeButton();

        ProductsPage productsPage = new ProductsPage();

        Assertions.assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }
}
