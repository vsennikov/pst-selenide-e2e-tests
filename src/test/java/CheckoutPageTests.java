import com.github.javafaker.Faker;
import config.ProjectConfig;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import pages.checkoutComponents.BillingAddressComponent;

import java.util.Locale;

@Epic("User Journey")
@Feature("Checkout Functionality")
public class CheckoutPageTests {

    private final ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private final Faker faker = new Faker(new Locale("en"));

    @Test
    @Story("Guest Checkout")
    @DisplayName("Guest user should be redirected to login and then to billing step")
    void guestUserShouldBeRedirectedToLoginDuringCheckout() {
        new ProductsPage()
                .openPage(config.baseUrl())
                .openProductPage("Combination Pliers")
                .clickAddToCartButton();

        new HeaderComponent().navigateToCartPage();

        new CartPage()
                .proceedToCheckoutAsGuest()
                .successfulLoginDuringCheckout(config.customerEmail(), config.customerPassword())
                .proceedToBilling();
    }

    @Test
    @Story("Logged-in User Checkout")
    @DisplayName("Logged-in user should be able to complete the purchase")
    void loggedInUserCanCompletePurchase() {
        new LoginPage()
                .openPage(config.baseUrl() + "auth/login")
                .successfulLogin(config.customerEmail(), config.customerPassword());

        new ProductsPage()
                .openPage(config.baseUrl())
                .openProductPage("Pliers")
                .clickAddToCartButton();

        new HeaderComponent().navigateToCartPage();

        CheckoutPage checkoutPage = new CartPage().proceedToCheckoutAsLoggedInUser();

        checkoutPage.verifyOnLoginStep()
                .proceedToBilling();

        BillingAddressComponent billingAddress = new BillingAddressComponent();
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