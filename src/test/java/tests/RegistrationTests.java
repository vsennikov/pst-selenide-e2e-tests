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
        String country = faker.address().country();

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
                .clickRegisterButton()
                .verifyOnPage();

    }
}
