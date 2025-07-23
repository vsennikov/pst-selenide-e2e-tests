package pages.checkoutComponents;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.CheckoutPage;

import static com.codeborne.selenide.Selenide.$;

public class BillingAddressComponent {

    private final SelenideElement streetInput = $("[data-test='street']");
    private final SelenideElement cityInput = $("[data-test='city']");
    private final SelenideElement stateInput = $("[data-test='state']");
    private final SelenideElement countryInput = $("[data-test='country']");
    private final SelenideElement postcodeInput = $("[data-test='postal_code']");

    @Step("Fill billing address")
    public CheckoutPage fillBillingAddress(String street, String city, String state, String country, String postcode) {
        streetInput.setValue(street);
        cityInput.setValue(city);
        stateInput.setValue(state);
        countryInput.setValue(country);
        postcodeInput.setValue(postcode);
        return new CheckoutPage();
    }
}
