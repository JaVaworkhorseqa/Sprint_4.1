import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;



class OrderSamokat {

    private WebDriver driver;
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By stationField = By.xpath(".//input[@placeholder='* Станция метро']");

    public OrderSamokat(WebDriver driver) {
        this.driver = driver;
    }
    public void clickCook() {
        WebElement element = driver.findElement(By.xpath(".//button[text()='да все привыкли']"));
        element.click();
    }
    public void clickNext() {
        WebElement element = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button"));
        element.click();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setStation(String stationName) {
        WebElement element = driver.findElement(stationField);
        element.click();
        element.sendKeys(stationName);
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        actions.sendKeys(Keys.RETURN).perform();
    }

    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void order(String name, String lastName, String address, String station, String phone) {
        setName(name);
        setLastName(lastName);
        setAddress(address);
        setStation(station);
        setPhone(phone);

    }
    public void cook(){
        clickCook();
    }

    public void next(){
        clickNext();
    }

}
