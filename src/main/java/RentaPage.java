import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


class RentaPage {

    private WebDriver driver;

    private By buttonDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // private By chooseDate = By.className("react-datepicker__day react-datepicker__day--022 react-datepicker__day--selected");

    private By periodRent = By.xpath(".//div[text()='* Срок аренды']");
    private By chooseRent = By.xpath(".//div[@class='Dropdown-menu']");

    private By chooseColor = By.id("grey");
    private By order = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");


    public RentaPage (WebDriver driver){
        this.driver = driver;
    }

    public void dateDelivery() {
        WebElement element = driver.findElement(buttonDate);
        element.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        actions.sendKeys(Keys.RETURN).perform();
    }

    public void rentalPeriod() {
        driver.findElement(periodRent).click();
        driver.findElement(chooseRent).click();
    }
    public void chooseGrey() {
        driver.findElement(chooseColor).click();
    }
    public void finalOrder() {
        driver.findElement(order).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//button[text()='Да']"))).click();

    }


    public void finishOrder() {
        dateDelivery();
        rentalPeriod();
        chooseGrey();
        finalOrder();

    }


}
