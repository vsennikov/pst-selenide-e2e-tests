package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AccountPage {

    private final SelenideElement pageTitle = $("h1");

    @Step("Verify that user is on the Account Page")
    public AccountPage verifyOnPage() {
        pageTitle.shouldBe(visible).shouldHave(text("My Account"));
        return this;
    }
}
