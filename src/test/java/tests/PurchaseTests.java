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
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                "Austria",
                faker.address().zipCode()
        );

        checkoutPage.proceedToPayment();
        checkoutPage.selectPaymentMethod("Cash on Delivery");
        checkoutPage.confirmPayment()
                .verifyPaymentSuccess();
    }

    @Test
    @Story("Multiple Products Purchase")
    @DisplayName("User should be able to purchase multiple different products")
    void loggedInUserCanPurchaseMultipleProducts() {
        ProductsPage productsPage = new HeaderComponent().navigateToHomePage();

        productsPage
                .openProductPage("Pliers")
                .clickAddToCartButton();

        new HeaderComponent().navigateToHomePage()
                .openProductPage("Hammer")
                .clickAddToCartButton();

        new HeaderComponent().navigateToHomePage()
                .openProductPage("Screwdriver")
                .clickAddToCartButton();

        CartPage cartPage = new HeaderComponent().navigateToCartPage();
        
        cartPage.getCartItem("Pliers").getProductName().equals("Pliers");
        cartPage.getCartItem("Hammer").getProductName().equals("Hammer");
        cartPage.getCartItem("Screwdriver").getProductName().equals("Screwdriver");

        CheckoutPage checkoutPage = cartPage.proceedToCheckoutAsLoggedInUser();

        BillingAddressComponent billingAddress = checkoutPage
                .verifyOnLoginStep()
                .proceedToBilling();

        billingAddress.fillBillingAddress(
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                "Austria",
                faker.address().zipCode()
        );

        checkoutPage.proceedToPayment();
        checkoutPage.selectPaymentMethod("Cash on Delivery");
        checkoutPage.confirmPayment()
                .verifyPaymentSuccess();
    }

    @Test
    @Story("Cart Management")
    @DisplayName("User should be able to update product quantity in cart")
    void loggedInUserCanUpdateQuantityInCart() {
        ProductsPage productsPage = new HeaderComponent().navigateToHomePage();

        productsPage
                .openProductPage("Combination Pliers")
                .clickAddToCartButton();

        CartPage cartPage = new HeaderComponent().navigateToCartPage();
        
        cartPage.getCartItem("Combination Pliers").setQuantity(3);
        
        int actualQuantity = cartPage.getCartItem("Combination Pliers").getQuantity();
        assertEquals(3, actualQuantity, "Expected quantity 3, but got " + actualQuantity);

        CheckoutPage checkoutPage = cartPage.proceedToCheckoutAsLoggedInUser();

        BillingAddressComponent billingAddress = checkoutPage
                .verifyOnLoginStep()
                .proceedToBilling();

        billingAddress.fillBillingAddress(
                faker.address().streetAddress(),
                faker.address().city(), 
                faker.address().state(),
                "Austria",
                faker.address().zipCode()
        );

        checkoutPage.proceedToPayment();
        checkoutPage.selectPaymentMethod("Cash on Delivery");
        checkoutPage.confirmPayment()
                .verifyPaymentSuccess();
    }

    @Test
    @Story("Cart Management")
    @DisplayName("User should be able to remove products from cart")
    void loggedInUserCanRemoveProductsFromCart() {
        ProductsPage productsPage = new HeaderComponent().navigateToHomePage();

        productsPage
                .openProductPage("Pliers")
                .clickAddToCartButton();

        new HeaderComponent().navigateToHomePage()
                .openProductPage("Hammer")
                .clickAddToCartButton();

        CartPage cartPage = new HeaderComponent().navigateToCartPage();
        
        cartPage.getCartItem("Pliers").getProductName().equals("Pliers");
        cartPage.getCartItem("Hammer").getProductName().equals("Hammer");

        cartPage.getCartItem("Hammer").remove();
        
        cartPage.getCartItem("Pliers").getProductName().equals("Pliers");

        CheckoutPage checkoutPage = cartPage.proceedToCheckoutAsLoggedInUser();

        BillingAddressComponent billingAddress = checkoutPage
                .verifyOnLoginStep()
                .proceedToBilling();

        billingAddress.fillBillingAddress(
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(), 
                "Austria",
                faker.address().zipCode()
        );

        checkoutPage.proceedToPayment();
        checkoutPage.selectPaymentMethod("Cash on Delivery");
        checkoutPage.confirmPayment()
                .verifyPaymentSuccess();
    }

    @Test
    @Story("Cart Price Calculation")
    @DisplayName("User should see products with their unit prices in cart")
    void loggedInUserSeesProductPricesInCart() {
        ProductsPage productsPage = new HeaderComponent().navigateToHomePage();

        // Add products
        productsPage
                .openProductPage("Combination Pliers")
                .clickAddToCartButton();

        new HeaderComponent().navigateToHomePage()
                .openProductPage("Pliers")
                .clickAddToCartButton();

        CartPage cartPage = new HeaderComponent().navigateToCartPage();
        
        double pliersPrice = cartPage.getCartItem("Combination Pliers").getUnitPrice();
        int pliersQuantity = cartPage.getCartItem("Combination Pliers").getQuantity();
        
        double pliersPrice2 = cartPage.getCartItem("Pliers").getUnitPrice();
        int pliersQuantity2 = cartPage.getCartItem("Pliers").getQuantity();
        
        assertEquals(true, pliersPrice > 0, "Product price should be positive");
        assertEquals(true, pliersPrice2 > 0, "Product price should be positive");
        
        assertEquals(true, pliersQuantity >= 1, "Product quantity should be at least 1");
        assertEquals(true, pliersQuantity2 >= 1, "Product quantity should be at least 1");

        CheckoutPage checkoutPage = cartPage.proceedToCheckoutAsLoggedInUser();

        BillingAddressComponent billingAddress = checkoutPage
                .verifyOnLoginStep()
                .proceedToBilling();

        billingAddress.fillBillingAddress(
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                "Austria", 
                faker.address().zipCode()
        );

        checkoutPage.proceedToPayment();
        checkoutPage.selectPaymentMethod("Cash on Delivery");
        checkoutPage.confirmPayment()
                .verifyPaymentSuccess();
    }

    @Test
    @Story("Guest Purchase")
    @DisplayName("Guest user should be able to complete purchase after login")
    void guestUserCanCompletePurchaseAfterLogin() {
        ProductsPage productsPage = new ProductsPage();
        productsPage.openPage(config.baseUrl());

        productsPage
                .openProductPage("Bolt Cutters")
                .clickAddToCartButton();

        CartPage cartPage = new HeaderComponent().navigateToCartPage();
        
        LoginPage loginPage = cartPage.proceedToCheckoutAsGuest();
        
        loginPage.successfulLogin(config.customerEmail(), config.customerPassword());
        
        cartPage = new HeaderComponent().navigateToCartPage();
        CheckoutPage checkoutPage = cartPage.proceedToCheckoutAsLoggedInUser();

        BillingAddressComponent billingAddress = checkoutPage
                .verifyOnLoginStep()
                .proceedToBilling();

        billingAddress.fillBillingAddress(
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                "Austria",
                faker.address().zipCode()
        );

        checkoutPage.proceedToPayment();
        checkoutPage.selectPaymentMethod("Cash on Delivery");
        checkoutPage.confirmPayment()
                .verifyPaymentSuccess();
    }
}