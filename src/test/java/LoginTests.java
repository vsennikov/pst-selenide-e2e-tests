
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTests {

    @Test
    void smokeTest() {
        open("https://practicesoftwaretesting.com/#/");
        String pageTitle = title();
        assertThat(pageTitle).isEqualTo("Practice Software Testing - Toolshop - v5.0");
    }
}