package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.actions;
import static org.openqa.selenium.By.linkText;

public class ParametrazedTest {

    static final String URL1 = "https://asana.com/ru/create-account";
    static final String URL2 = "https://englishtochka.ru/webinar/";

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

    @CsvSource({
            "Сурген, email@rambler.ru",
            "Патрик, email@yandex.ru",
            "Джимми, email@gmail.com"

    })
    @ParameterizedTest(name = "{1}")
    void checkFideBackFormTest(String name, String email) {
        open(URL2);
        $(byName("lead_name")).setValue(name);
        $(byName("lead_email")).setValue(email);
        $(".form:nth-child(2) span").click();
        $(".container").shouldHave(Condition.text("ВАЖНО!"));

    }

}
