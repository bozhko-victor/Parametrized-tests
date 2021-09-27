package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    static final String URL1 = "https://asana.com/ru/create-account";
    static final String URL2 = "https://englishtochka.ru/webinar/";
    static final String URL3 = "https://lookfreedom.ru/napisat-nam/";

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }
}
