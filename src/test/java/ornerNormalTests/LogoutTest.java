package ornerNormalTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LogoutTest {

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
    public void correctLogout(){
        final String mailString = "testkatytest@gmail.com";
        final String passwordString = "A5fg48BW378O";

        By alreadyHaveHrefLocator = By.id("already-have-an-account");
        By enterButtonLocator = By.cssSelector("#login > div.register-form > form > div.btn-register.btn-register");
        By flagLocator = By.cssSelector(".page-content-wrapper");

        driver.get("https://orner.com.ua/");

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icon-nouser")))
                .click();

        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOfElementLocated(alreadyHaveHrefLocator))
                .click();

        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login-email")))
                .sendKeys(mailString);

        WebElement passwordField = driver.findElement(By.cssSelector("#login-password"));
        passwordField.sendKeys(passwordString);

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(enterButtonLocator))
                .click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated((flagLocator)));

        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icon-nouser")))
                .click();

        WebElement exitButton = driver.findElement(By.cssSelector("a[href*='/logout']"));
        exitButton.click();

        if( driver.findElement(flagLocator).isEnabled()){
            System.out.println("It's OK, user is logout");
        }else{
            System.out.println("Nope, it's not OK(((00");
        }
    }
}
