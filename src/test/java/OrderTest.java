import io.github.bonigarcia.wdm.WebDriverManager;
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

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String name;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phone;
    private final String answer;

    public OrderTest(String name, String lastName, String address, String station, String phone, String answer) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.answer = answer;

    }

    @Parameterized.Parameters
    public static Object[][] testKeyses() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][]{
                {"Иван", "Иванов", "Московская", "черкиз", "89898988989", "//*[contains(text(), 'Заказ оформлен')]"},
                 { "Владимир", "Владимиров", "Питерская", "чехов", "89898833989", "//*[contains(text(), 'Заказ оформлен')]"},
        };
    }

    @Before

    public void testOrderFlow() {
        // драйвер для браузера Chrome
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // Разрешить внешние подключения
        options.addArguments("--disable-gpu"); // Отключение GPU для стабильности
        options.addArguments("--no-sandbox"); // Отключение песочницы
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

  /*  @Test
    public void testButtonNext() {

        //кликаем по верхней кнопке заказа
        HomePageSamokat homePageSamokat = new HomePageSamokat(driver);
        homePageSamokat.clickOrderUp();
        //заполняем форму заказа
        OrderSamokat orderSamokat = new OrderSamokat(driver);
        orderSamokat.order(name, lastName, address, station, phone);
        orderSamokat.clickCook();
        orderSamokat.next();

// заполняем форму аренды

        By textLocator = By.xpath("//*[contains(text(), 'Про аренду')]");

        // Ожидание появления текста на странице
        boolean isTextPresent = wait.until(driver -> driver.findElement(textLocator).isDisplayed());

        // Проверка, что текст появился
        assertTrue("Ожидаемый текст не найден на странице!", isTextPresent);
    }*/

    @Test
    public void testOrderButtonUp() {

        //кликаем по верхней кнопке заказа
        HomePageSamokat homePageSamokat = new HomePageSamokat(driver);
        homePageSamokat.clickOrderUp();
        //заполняем форму заказа
        OrderSamokat orderSamokat = new OrderSamokat(driver);
        orderSamokat.order(name, lastName, address, station, phone);
        orderSamokat.clickCook();
        orderSamokat.next();

// заполняем форму аренды
        RentaPage rentaPage = new RentaPage(driver);
        rentaPage.finishOrder();

        By textLocator = By.xpath(answer);

        // Ожидание появления текста на странице
        boolean isTextPresent = wait.until(driver -> driver.findElement(textLocator).isDisplayed());

        // Проверка, что текст появился
        assertTrue("Ожидаемый текст не найден на странице!", isTextPresent);
    }

    @Test
    public void testOrderButtonDown() {

        HomePageSamokat objLoginPage = new HomePageSamokat(driver);
        objLoginPage.clickOrderDown();
        OrderSamokat orderSamokat = new OrderSamokat(driver);
        orderSamokat.order(name, lastName, address, station, phone);
        orderSamokat.next();

        RentaPage rentaPage = new RentaPage(driver);
        rentaPage.finishOrder();

        // Локатор для текста
        By textLocator = By.xpath(answer);

        // Ожидание появления текста на странице
        // Ожидание появления текста на странице
        boolean isTextPresent = wait.until(driver -> driver.findElement(textLocator).isDisplayed());

        // Проверка, что текст появился
        assertTrue("Ожидаемый текст не найден на странице!", isTextPresent);
    }


    @After
    public void teardown() {
        // Закрой браузер
       driver.quit();
    }
}
