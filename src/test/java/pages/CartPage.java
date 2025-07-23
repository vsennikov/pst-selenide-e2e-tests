package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    private final ElementsCollection cartItemRows = $$("tbody tr");
    private final SelenideElement cartTotalValue = $("[data-test='total-price']");
    private final SelenideElement proceedToCheckoutButton = $("[data-test='proceed-1']");

    @Step("Get cart item by name: {productName}")
    public CartItem getCartItem(String productName) {
        SelenideElement itemRow = cartItemRows.findBy(text(productName))
                .shouldBe(visible);
        return new CartItem(itemRow);
    }

    @Step("Get cart total value")
    public double getCartTotalValue() {
        return Double.parseDouble(cartTotalValue.getText().replace("$", "").trim());
    }

    @Step("Click 'Proceed to checkout' as a Guest")
    public LoginPage proceedToCheckoutAsGuest() {
        proceedToCheckoutButton.click();
        return new LoginPage();
    }

    @Step("Click 'Proceed to checkout' as a Logged-in User")
    public CheckoutPage proceedToCheckoutAsLoggedInUser() {
        proceedToCheckoutButton.click();
        return new CheckoutPage();
    }
}
