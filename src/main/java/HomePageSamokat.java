import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;





class HomePageSamokat {

    private WebDriver driver;

    private By buttonOrderUp = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    // создай локатор для кнопки редактирования профиля
    private By buttonOrderDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    private By cookie = By.xpath(".//button[text()='да все привыкли']");



    public boolean isTextPresent(String expectedText) {
        By textLocator = By.xpath("//*[contains(text(), '" + expectedText + "')]");
        return !driver.findElements(textLocator).isEmpty();
    }

    public HomePageSamokat(WebDriver driver) {
        this.driver = driver;
    }


// клики по елементам FAQ
    public void clickDrop(String text) {
        WebElement element = driver.findElement(By.id(text));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }


    // метод для нажатия на кнопку заказать сверху
    public void clickOrderUp() {
        driver.findElement(buttonOrderUp).click();
    }
    // метод для нажатия на кнопку заказать внизу
    public void clickOrderDown(){
        WebElement element = driver.findElement(buttonOrderDown);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        WebElement cook = driver.findElement(cookie);
        cook.click();
        element.click();
    }



}
