package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void testAnketaCard(){
        open("http://localhost:9999/");
        $("[data-test-id='citi'] input").setValue("Уфа");
        String curentDate = generateDate(24,"MM");
        $("[data-test-id= 'date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] input").sendKeys(curentDate);
        $("[data-test-id='name'] input").setValue("Романов Роман");
        $("[data-test-id='phone'] input").setValue("+79313313131");
        $("[data-test-id='agreement'] input").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactOwnText("Встреча успешно забронирована на " +curentDate));
        
    }
}

