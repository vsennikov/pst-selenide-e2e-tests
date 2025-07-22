package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class HeaderComponent {
    private final SelenideElement cartLink = $("[data-test='nav-cart']");
    private final SelenideElement cartCounter = $("[data-test='cart-quantity']");

    @Step("Nabigate to Cart page")
    public CartPage navigateToCartPage(){
        cartLink.click();
        return new CartPage();
    }
}
