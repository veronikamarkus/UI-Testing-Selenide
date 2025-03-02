package ui_testing;

import com.codeborne.selenide.Configuration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui_testing.page.LoginPage;
import ui_testing.page.ProductsPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginPageTests {
    @BeforeAll
    public static void configureSelenide(){
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://www.saucedemo.com";
    }

    @Test
    public void givenCorrectCredentials_whenLogin_thenShouldSeeProductsTitle(){
        open("/");

        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage productsPage = new ProductsPage();

        Assertions.assertThat(productsPage.getPageHeadingText()).isEqualTo("Products");
    }

    @Test
    public void givenIncorrectCredentials_whenLogin_thenShouldSeeErrorMessage(){
        open("/");

        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("incorrect_sauce");
        loginPage.clickLoginButton();

        Assertions.assertThat(loginPage.getErrorMessageField())
                .isEqualTo("Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void givenLockedOutUser_whenLogin_thenShouldSeeLockedOutErrorMessage(){
        open("/");

        LoginPage loginPage = new LoginPage();

        loginPage.enterUserName("locked_out_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();

        Assertions.assertThat(loginPage.getErrorMessageField())
                .isEqualTo("Epic sadface: Sorry, this user has been locked out.");
    }
}
