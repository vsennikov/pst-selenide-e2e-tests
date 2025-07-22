package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {
    private final SelenideElement loggedInMessage = $("p");
    private final SelenideElement buttonAfterLogging = $("[data-test='proceed=2']");

    @Step("Verify that user is on the Login step")
    public CheckoutPage verifyOnLoginStep() {
       loggedInMessage.shouldBe(visible)
               .shouldHave(text("you are already logged in"));
        return this;
    }

}

