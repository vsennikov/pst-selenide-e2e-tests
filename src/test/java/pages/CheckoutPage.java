package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.checkoutComponents.BankTransferPaymentComponent;
import pages.checkoutComponents.BillingAddressComponent;
import pages.checkoutComponents.CreditCardPaymentComponent;
import pages.checkoutComponents.GiftCardPaymentComponent;
import pages.checkoutComponents.PayLaterPaymentComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {

    private final SelenideElement loggedInMessage = $("p");
    private final SelenideElement billingAddressTitle = $("h3");
    private final SelenideElement paymentSuccessMessage = $("[data-test='payment-success-message']");
    private final SelenideElement proceedToBillingButton = $("[data-test='proceed-2']");
    private final SelenideElement proceedToPaymentButton = $("[data-test='proceed-3']");
    private final SelenideElement confirmButton = $("[data-test='finish']");
    private final SelenideElement paymentMethodSelector = $("[data-test='payment-method']");


    @Step("Verify that user is on the Login step")
    public CheckoutPage verifyOnLoginStep() {
        loggedInMessage.shouldBe(visible).shouldHave(text("you are already logged in"));
        return this;
    }

    @Step("Verify that user is on the Billing step")
    public CheckoutPage verifyOnBillingStep() {
        billingAddressTitle.shouldBe(visible).shouldHave(text("Billing Address"));
        return this;
    }

    @Step("Verify that payment was successful")
    public CheckoutPage verifyPaymentSuccess() {
        paymentSuccessMessage.shouldBe(visible).shouldHave(text("Payment was successful"));
        return this;
    }

    @Step("Proceed to billing step")
    public BillingAddressComponent proceedToBilling() {
        proceedToBillingButton.click();
        verifyOnBillingStep();
        return new BillingAddressComponent();
    }

    @Step("Proceed to payment step")
    public CheckoutPage proceedToPayment() {
        proceedToPaymentButton.click();
        return this;
    }

    @Step("Select payment method: {paymentMethod}")
    public Object selectPaymentMethod(String paymentMethod) {
        paymentMethodSelector.selectOption(paymentMethod);

        switch (paymentMethod) {
            case "Credit Card":
                return new CreditCardPaymentComponent();
            case "Bank Transfer":
                return new BankTransferPaymentComponent();
            case "Gift Card":
                return new GiftCardPaymentComponent();
            case "Buy Now Pay Later":
                return new PayLaterPaymentComponent();
            default:
                return this;
        }
    }

    @Step("Confirm payment")
    public CheckoutPage confirmPayment() {
        confirmButton.click();
        return this;
    }
}