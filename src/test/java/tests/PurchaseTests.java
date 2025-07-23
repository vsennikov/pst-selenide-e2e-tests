package tests;
import com.github.javafaker.Faker;
import config.ProjectConfig;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProductsPage;
import pages.CartPage;
import pages.HeaderComponent;
import pages.CheckoutPage;
import pages.checkoutComponents.BillingAddressComponent;

import java.util.Locale;

@Epic("User Journey")
@Feature("Product Purchase")
public class PurchaseTests {

    private final ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private final Faker faker = new Faker(new Locale("en"));

    @BeforeEach
    void login() {
        new LoginPage()
                .openPage(config.baseUrl() + "auth/login")
                .successfulLogin(config.customerEmail(), config.customerPassword())
                .verifyOnPage();
    }

    @Test
    @Story("Full Purchase Cycle for Logged-in User")
    @DisplayName("User should be able to select, buy and confirm a product")
    void loggedInUserCanCompleteFullPurchaseCycle() {
        ProductsPage productsPage = new HeaderComponent().navigateToHomePage();

        productsPage
                .openProductPage("Pliers")
                .clickAddToCartButton();

        CheckoutPage checkoutPage = new HeaderComponent()
                .navigateToCartPage()
                .proceedToCheckoutAsLoggedInUser();

        BillingAddressComponent billingAddress = checkoutPage
                .verifyOnLoginStep()
                .proceedToBilling();

        billingAddress.fillBillingAddress(
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                "United States",
                faker.address().zipCode()
        );

        checkoutPage.proceedToPayment();
        checkoutPage.selectPaymentMethod("Cash on Delivery");
        checkoutPage.confirmPayment()
                .verifyPaymentSuccess();
    }
}