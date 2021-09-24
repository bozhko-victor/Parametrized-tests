package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class ParametrazedTest {

    static final String URL1 = "https://asana.com/ru/create-account";

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @ValueSource(strings = {
            "sergion@fifi.ru",
            "mourini@guru.ru",
            "chibis@dodo.ru"

    })

    @ParameterizedTest(name = "Check Registration Form - {0}")
    void checkRegistrationFormTest(String companyName) {
        open(URL1);
        $(".col-xsmall-12").click();
        $(byName("email")).val(companyName);
        $(".signupForm-submit").click();
        $(".textStack").shouldHave(Condition.text("Подтвердите адрес электронной почты"));
    }

}
