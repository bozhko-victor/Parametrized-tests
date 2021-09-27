package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.actions;
import static com.codeborne.selenide.impl.Cleanup.of;
import static org.openqa.selenium.By.linkText;

public class ParametrazedTest extends TestBase {


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
    static Stream<Arguments> lookFreedomTest () {
        return Stream.of(
                Arguments.of(
                      "Fedor", "mymail@gmail.com", "acquaintance", "Good day!"
                ),
                Arguments.of(
                       "Sharik", "sharik@gmail.com", "acquaintance", "Good night!"
                ),
                Arguments.of(
                        "Fil", "foolfil@gmail.com", "acquaintance", "Good morning!"
                )
        );
    }

    @MethodSource("lookFreedomTest")
    @ParameterizedTest(name = "{0}")
        void lookFreedomFormTest(String name, String email, String topic, String message) {
        open(URL3);
        $(byName("your-name")).setValue(name);
        $(byName("your-email")).setValue(email);
        $(byName("your-subject")).setValue(topic);
        $(byName("your-message")).setValue(message);
        $(".wpcf7-submit").click();
        $(".wpcf7-response-output").
                shouldHave(Condition.text("Thank you for your message. It has been sent."));
    }

}
