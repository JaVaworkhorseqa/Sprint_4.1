import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;



@RunWith(Parameterized.class)
public class CheckFAQText {
    private WebDriver driver;
    private WebDriverWait wait;

    private final String text;
    private final String expectedAnswer;

    public CheckFAQText(String text, String expectedAnswer){
        this.text = text;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] testKeyses() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][]{
                {"accordion__heading-0", "Сутки — 400 рублей"},
                {"accordion__heading-1", "один заказ — один самокат"},
                {"accordion__heading-2", "Отсчёт времени"},
                {"accordion__heading-3", "станем расторопнее"},
                {"accordion__heading-4", "Пока что нет"},
                {"accordion__heading-5", "Этого хватает"},
                {"accordion__heading-6", "Штрафа не будет"},
                {"accordion__heading-7", "Всем самокатов!"},
        };
    }

    @Before

    public void testHomePageSamokat() {
        // драйвер для браузера Chrome
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Разрешить внешние подключения
        options.addArguments("--headless"); // Запуск в headless-режиме
        options.addArguments("--disable-gpu"); // Отключение GPU для стабильности
        options.addArguments("--no-sandbox"); // Отключение песочницы
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }





    @Test
    public void testText() {


        HomePageSamokat objLoginPage = new HomePageSamokat(driver);
        objLoginPage.clickDrop(text);



        // Ожидание появления текста на странице
        boolean isTextPresent = objLoginPage.isTextPresent(expectedAnswer);

        // Проверка, что текст появился
        assertTrue("Ожидаемый текст не найден на странице!", isTextPresent);
    }



@After
public void teardown() {
    // Закрой браузер
    driver.quit();
}
}
