
import config.ProjectConfig;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.aeonbits.owner.ConfigFactory;
import pages.LoginPage;

@Epic("User Authentication")
@Feature("Login Functionality")
public class LoginTests {

    private final ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
        loginPage.openPage(config.baseUrl() + "auth/login");
    }

    @Test
    @Story("Successful Login")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("User should be able to login with valid credentials")
    void userCanLoginSuccessfully() {
        loginPage.successfulLogin(config.customerEmail(), config.customerPassword())
                .verifyOnPage();
    }

    @ParameterizedTest(name = "Login from file with email: {0} should fail with message: {2}")
    @CsvFileSource(resources = "/login_test_data.csv", numLinesToSkip = 1)
    @Story("Failed Login from File")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("User should see an error message with invalid credentials from file")
    void loginWithInvalidCredentialsFromFile(String email, String password, String expectedErrorMessage) {
        loginPage.attemptLogin(email, password)
                .checkErrorMessage(expectedErrorMessage);
    }
}