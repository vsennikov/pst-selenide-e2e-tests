package pages;

import com.codeborne.selenide.SelenideElement;


import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("[data-test='first-name']");
    private final SelenideElement lastNameInput = $("[data-test='last-name']");
    private final SelenideElement birthDateInput = $("[data-test='dob']");
    private final SelenideElement streetInput = $("[data-test='street']");
    private final SelenideElement postalCodeInput = $("[data-test='postal_code']");
    private final SelenideElement cityInput = $("[data-test='city']");
    private final SelenideElement stateInput = $("[data-test='state']");
    private final SelenideElement countryInput = $("[data-test='country']");
    private final SelenideElement phoneInput = $("[data-test='phone']");
    private final SelenideElement emailInput = $("[data-test='email']");
    private final SelenideElement passwordInput = $("[data-test='password']");
    private final SelenideElement registerButton = $("[data-test='register-submit']");

    @Step("Open registration page")
    public RegistrationPage openPage(String url) {
        open(url);
        return this;
    }

    @Step("Set first name: {firstName}")
    public RegistrationPage setFirstNameInput(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Set last name: {lastName}")
    public RegistrationPage setLastNameInput(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Set date of birth: {date}")
    public RegistrationPage setBirthDateInput(String date) {
        birthDateInput.setValue(date);
        return this;
    }

    @Step("Set street: {street}")
    public RegistrationPage setStreetInput(String street) {
        streetInput.setValue(street);
        return this;
    }

    @Step("Set postalcode: {postalcode}")
    public RegistrationPage setPostalCodeInput(String postalcode) {
        postalCodeInput.setValue(postalcode);
        return this;
    }

    @Step("Set city: {city}")
    public RegistrationPage setCityInput(String city) {
        cityInput.setValue(city);
        return this;
    }

    @Step("Set state: {state}")
    public RegistrationPage setStateInput(String state) {
        stateInput.setValue(state);
        return this;
    }

    @Step("Select country: {country}")
    public RegistrationPage selectCountry(String country) {
        countryInput.selectOption(country);
        return this;
    }

    @Step("Set phone: {phone}")
    public RegistrationPage setPhoneInput(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    @Step("Set email: {email}")
    public RegistrationPage setEmailInput(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Set password: {password}")
    public RegistrationPage setPasswordInput(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Click Register Button")
    public LoginPage clickRegisterButton() {
        registerButton.click();
        return new LoginPage();
    }

    @Step("Click Register button expecting a validation error")
    public RegistrationPage clickRegisterButtonExpectingError() {
        registerButton.click();
        return this;
    }

    @Step("Check validation error for field '{fieldName}': {expectedErrorMessage}")
    public RegistrationPage checkValidationErrorForField(String fieldName, String expectedErrorMessage) {
        String errorLocator = String.format("[data-test='%s-error']", fieldName);

        $(errorLocator).shouldBe(visible).shouldHave(text(expectedErrorMessage));
        return this;
    }
}
