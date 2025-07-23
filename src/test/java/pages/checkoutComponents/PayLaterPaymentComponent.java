package pages.checkoutComponents;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.CheckoutPage;

import static com.codeborne.selenide.Selenide.$;

public class PayLaterPaymentComponent {

    private final SelenideElement installmentsSelector = $("[data-test='monthly-installments']");

    @Step("Select monthly installments option: {installmentsOption}")
    public CheckoutPage selectInstallments(String installmentsOption) {
        installmentsSelector.selectOption(installmentsOption);
        return new CheckoutPage();
    }
}