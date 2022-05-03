package vaulin1506.com;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DisplayName("6-й урок, JUnit")
public class JUnitTests {

    @Disabled("#123")
    @DisplayName("Пример")
    @Test
    void ferstTest() {
        Assertions.assertTrue(3>2,"3>2");
        Assertions.assertFalse(2>3,"2>3");
        Assertions.assertEquals(123, 123, "123=123");
        Assertions.assertAll(
                () -> Assertions.assertTrue(3<2, "3<2"),
                () -> Assertions.assertFalse(2>3, "2>3")
        );
    }
    @Disabled("1234")
    @ValueSource(strings = {
            "qa.guru JUnit",
            "qa.guru selenide"
    })
    @ParameterizedTest(name = "Проверка поиска YouTube по {0}")
    void youTubeSerchTest(String testData){
        
        Selenide.open("https://www.youtube.com/");

        $(byXpath("//input[@id=\'search\']")).val(testData); // не смог найти рабочий css selector

        $(byXpath("//button[@id='search-icon-legacy']")).click();

        $$("#dismissible").find(Condition.text(testData)).shouldBe(visible);
    }

    @CsvSource(value = {
            "qa.guru JUnit | Дмитрий Тучс",
            "qa.guru | Станислав Васенков"
    },delimiter = '|')
    @ParameterizedTest(name = "Проверка поиска YouTube по {0}, лектор {1}")
    void youTubeComplexSerchTest(String testData, String expectedResult){
        Configuration.holdBrowserOpen = true;
        Selenide.open("https://www.youtube.com/");

        $(byXpath("//input[@id=\'search\']")).val(testData); // не смог найти рабочий css selector

        $(byXpath("//button[@id='search-icon-legacy']")).click();

        $$("#dismissible").find(Condition.text(expectedResult)).shouldBe(visible);
    }

}
