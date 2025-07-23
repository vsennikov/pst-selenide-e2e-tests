package pages.checkoutComponents;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.CheckoutPage;

import static com.codeborne.selenide.Selenide.$;

public class CreditCardPaymentComponent {

    private final SelenideElement creditCardNumberInput = $("[data-test='credit_card_number']");
    private final SelenideElement expirationDateInput = $("[data-test='expiration_date']");
    private final SelenideElement cvvInput = $("[data-test='cvv']");
    private final SelenideElement cardHolderNameInput = $("[data-test='card_holder_name']");

    @Step("Fill credit card details")
    public CheckoutPage fillDetails(String number, String expiration, String cvv, String holderName) {
        creditCardNumberInput.setValue(number);
        expirationDateInput.setValue(expiration);
        cvvInput.setValue(cvv);
        cardHolderNameInput.setValue(holderName);
        return new CheckoutPage();
    }
}