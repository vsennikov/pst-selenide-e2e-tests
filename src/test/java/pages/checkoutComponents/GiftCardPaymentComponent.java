package pages.checkoutComponents;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.CheckoutPage;

import static com.codeborne.selenide.Selenide.$;

public class GiftCardPaymentComponent {

    private final SelenideElement giftCardNumberInput = $("[data-test='gift-card-number']");
    private final SelenideElement validationCodeInput = $("[data-test='validation-code']");

    @Step("Fill gift card details")
    public CheckoutPage fillDetails(String cardNumber, String validationCode) {
        giftCardNumberInput.setValue(cardNumber);
        validationCodeInput.setValue(validationCode);
        return new CheckoutPage();
    }
}
