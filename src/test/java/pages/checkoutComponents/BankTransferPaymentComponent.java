package pages.checkoutComponents;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.CheckoutPage;

import static com.codeborne.selenide.Selenide.$;

public class BankTransferPaymentComponent {

    private final SelenideElement bankNameInput = $("[data-test='bank-name']");
    private final SelenideElement accountNameInput = $("[data-test='account-name']");
    private final SelenideElement accountNumberInput = $("[data-test='account-number']");

    @Step("Fill bank transfer details")
    public CheckoutPage fillDetails(String bankName, String accountName, String accountNumber) {
        bankNameInput.setValue(bankName);
        accountNameInput.setValue(accountName);
        accountNumberInput.setValue(accountNumber);
        return new CheckoutPage();
    }
}
