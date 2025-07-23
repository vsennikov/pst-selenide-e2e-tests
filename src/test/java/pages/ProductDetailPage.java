package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ProductDetailPage {
    private final SelenideElement productName = $("[data-test='product-name']");
    private final SelenideElement addToCartButton = $("[data-test='add-to-cart']");
    private final SelenideElement successBanner = $("#toast-container");

    @Step("Verify that user is on the product Page of {selectedProduct}")
    public ProductDetailPage verifyOnPage(String selectedProduct) {
        productName.shouldBe(visible).shouldHave(exactText(selectedProduct));
        return this;
    }

    @Step("Click 'add to cart' button")
    public ProductDetailPage clickAddToCartButton(){
        addToCartButton.click();
        successBanner.shouldBe(visible);
        successBanner.click();
        return this;
    }
}
