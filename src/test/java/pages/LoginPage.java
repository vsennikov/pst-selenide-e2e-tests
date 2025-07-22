package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private final SelenideElement emailInput = $("#email");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("input[value='Login']");
    private final SelenideElement errorMessage = $("div.alert-danger");

    @Step("Open login page")
    public LoginPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Login as user, expecting to go to Account Page")
    public AccountPage successfulLogin(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
        return new AccountPage();
    }

    @Step("Login as user during checkout, expecting to go to Checkout Page")
    public CheckoutPage successfulLoginDuringCheckout(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
        return new CheckoutPage();
    }

    @Step("Attempt to login with email: {email}")
    public LoginPage attemptLogin(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
        return this;
    }

    @Step("Check for error message: {expectedText}")
    public LoginPage checkErrorMessage(String expectedText) {
        errorMessage.shouldHave(text(expectedText));
        return this;
    }

    @Step("Verify that user is on the Login Page")
    public LoginPage verifyOnPage() {
        loginButton.shouldBe(visible);
        return this;
    }
}
