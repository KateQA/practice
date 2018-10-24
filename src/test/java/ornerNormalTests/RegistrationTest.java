package ornerNormalTests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RegistrationTest {

    public WebDriver driver;

    @BeforeTest
    public void setupDriver(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){

        this.driver.quit();
    }

    @Test
    public void correctRegistration() throws InterruptedException {

        By emailLocator = By.id("mail");//создание переменной emailLocator под типом By
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        driver.get("https://temp-mail.org/ru/");

        String emailBefore = driver.findElement(emailLocator).getAttribute("value");
        WebElement deleteCurrentEmailButton = driver.findElement(By.cssSelector("a[href*='option/delete']"));
        deleteCurrentEmailButton.click();
        new WebDriverWait(driver, 5).until((dr) -> !dr.findElement(emailLocator).getAttribute("value").equals(emailBefore));
        String newEmail = driver.findElement(emailLocator).getAttribute("value");

        driver.get("https://orner.com.ua/");

        WebElement profileButton = driver.findElement(By.cssSelector(".icon-nouser"));
        profileButton.click();
        WebElement nameField = driver.findElement(By.cssSelector("#register-username"));
        nameField.sendKeys("Motya");
        WebElement emailField = driver.findElement(By.cssSelector("#register-email"));
        emailField.sendKeys(newEmail);
        WebElement passwordField = driver.findElement(By.cssSelector("#register-password"));
        passwordField.sendKeys("123123");
        WebElement secondPasswordField = driver.findElement(By.cssSelector("#register-password-tomatch"));
        secondPasswordField.sendKeys("123123");
        WebElement registrationButton = driver.findElement(By.cssSelector(".register"));
        registrationButton.click();

        driver.get("https://temp-mail.org/ru/");

        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mails .link")))
                    .click();
        } catch (WebDriverException e) {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#mails .link")))
                    .click();
        }

        WebElement activationLink = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href *= '/active_user/']")));
        // выполняем JS скрипт в браузере чтоб наверняка кликнуть кнопку
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", activationLink);

        String newWindowHandle = new ArrayList<>(driver.getWindowHandles()).get(1);
        driver.close();
        driver.switchTo().window(newWindowHandle);

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-content-wrapper")));
    }
}
