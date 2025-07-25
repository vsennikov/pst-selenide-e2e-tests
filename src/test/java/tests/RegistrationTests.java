package tests;

import com.github.javafaker.Faker;
import config.ProjectConfig;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Epic("User Management")
@Feature("Registration Functionality")
public class RegistrationTests {

    private final ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
    private RegistrationPage registrationPage;

    @BeforeEach
    void setUp() {
        registrationPage = new RegistrationPage();
        registrationPage.openPage(config.baseUrl() + "auth/register");
    }

    @Test
    @Story("Successful registration")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("A new user should be able to register successfully with valid data")
    void userCanRegisterSuccessfully() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet()
                .password(8, 16, true, true, true);
        Date dob = faker.date().birthday(18, 65);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDob = formatter.format(dob);
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
		String country = "Austria";

        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setBirthDateInput(formattedDob)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(phone)
                .setEmailInput(email)
                .setPasswordInput(password)
                .clickRegisterButton();
        
    }

    @Test
    @Story("Registration validation")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("User should not be able to register with existing email")
    void userCannotRegisterWithExistingEmail() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String password = faker.internet().password(8, 16, true, true, true);
        Date dob = faker.date().birthday(18, 65);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDob = formatter.format(dob);
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
        String country = "Austria";

        String existingEmail = config.customerEmail();

        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setBirthDateInput(formattedDob)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(phone)
                .setEmailInput(existingEmail)
                .setPasswordInput(password)
                .clickRegisterButtonExpectingError()
                .checkValidationErrorForField("email", "A customer with this email address already exists.");
    }

    @Test
    @Story("Password validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("User should not be able to register with password shorter than 8 characters")
    void userCannotRegisterWithShortPassword() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String shortPassword = "1234567";
        Date dob = faker.date().birthday(18, 65);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDob = formatter.format(dob);
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
        String country = "Austria";

        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setBirthDateInput(formattedDob)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(phone)
                .setEmailInput(email)
                .setPasswordInput(shortPassword)
                .clickRegisterButtonExpectingError()
                .checkValidationErrorForField("password", "Password must be minimal 6 characters long.");
    }

    @Test
    @Story("Password validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("User should be able to register with password exactly 8 characters")
    void userCanRegisterWithMinimumValidPassword() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String minPassword = "Pass123!";
        Date dob = faker.date().birthday(18, 65);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDob = formatter.format(dob);
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
        String country = "Austria";

        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setBirthDateInput(formattedDob)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(phone)
                .setEmailInput(email)
                .setPasswordInput(minPassword)
                .clickRegisterButton();
    }

    @Test
    @Story("Field validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("User should not be able to register with empty email")
    void userCannotRegisterWithEmptyEmail() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String password = faker.internet().password(8, 16, true, true, true);
        Date dob = faker.date().birthday(18, 65);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDob = formatter.format(dob);
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
        String country = "Austria";

        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setBirthDateInput(formattedDob)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(phone)
                .setEmailInput("")
                .setPasswordInput(password)
                .clickRegisterButtonExpectingError()
                .checkValidationErrorForField("email", "Email is required.");
    }

    @Test
    @Story("Field validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("User should not be able to register with invalid email format")
    void userCannotRegisterWithInvalidEmailFormat() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String password = faker.internet().password(8, 16, true, true, true);
        Date dob = faker.date().birthday(18, 65);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDob = formatter.format(dob);
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
        String country = "Austria";

        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setBirthDateInput(formattedDob)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(phone)
                .setEmailInput("invalid-email-format")
                .setPasswordInput(password)
                .clickRegisterButtonExpectingError()
                .checkValidationErrorForField("email", "Please enter a valid email address.");
    }

    @Test
    @Story("Field validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("User should not be able to register with empty first name")
    void userCannotRegisterWithEmptyFirstName() {
        Faker faker = new Faker(new Locale("en"));
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16, true, true, true);
        Date dob = faker.date().birthday(18, 65);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDob = formatter.format(dob);
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
        String country = "Austria";

        registrationPage
                .setFirstNameInput("")
                .setLastNameInput(lastName)
                .setBirthDateInput(formattedDob)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(phone)
                .setEmailInput(email)
                .setPasswordInput(password)
                .clickRegisterButtonExpectingError()
                .checkValidationErrorForField("first-name", "First name is required");
    }

    @Test
    @Story("Field validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("User should not be able to register with empty last name")
    void userCannotRegisterWithEmptyLastName() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16, true, true, true);
        Date dob = faker.date().birthday(18, 65);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDob = formatter.format(dob);
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
        String country = "Austria";

        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput("") 
                .setBirthDateInput(formattedDob)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(phone)
                .setEmailInput(email)
                .setPasswordInput(password)
                .clickRegisterButtonExpectingError()
                .checkValidationErrorForField("last-name", "Last name is required");
    }

    @Test
    @Story("Date validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("User should not be able to register with invalid date format")
    void userCannotRegisterWithInvalidDateFormat() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16, true, true, true);
        String invalidDate = "invalid-date";
        String phone = faker.number().digits(10);
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
        String country = "Austria";

        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setBirthDateInput(invalidDate)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(phone)
                .setEmailInput(email)
                .setPasswordInput(password)
                .clickRegisterButtonExpectingError()
                .checkValidationErrorForField("dob", "Please enter a valid date in DD-MM-YYYY format");
    }

    @Test
    @Story("Phone validation")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("User should not be able to register with invalid phone number")
    void userCannotRegisterWithInvalidPhoneNumber() {
        Faker faker = new Faker(new Locale("en"));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16, true, true, true);
        Date dob = faker.date().birthday(18, 65);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDob = formatter.format(dob);
        String invalidPhone = "123";
        String address = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String postcode = faker.address().zipCode();
        String country = "Austria";

        registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setBirthDateInput(formattedDob)
                .setStreetInput(address)
                .setPostalCodeInput(postcode)
                .setCityInput(city)
                .setStateInput(state)
                .selectCountry(country)
                .setPhoneInput(invalidPhone)
                .setEmailInput(email)
                .setPasswordInput(password)
                .clickRegisterButtonExpectingError()
                .checkValidationErrorForField("phone", "Phone number must be at least 10 digits");
    }
}
