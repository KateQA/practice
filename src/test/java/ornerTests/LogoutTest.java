package ornerTests;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutTest {

    public static void main(String[] args) {

        final String mailString = "testkatytest@gmail.com";
        final String passwordString = "A5fg48BW378O";

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://orner.com.ua/");

        By alreadyHaveHrefLocator = By.id("already-have-an-account");
        By enterButtonLocator = By.cssSelector("#login > div.register-form > form > div.btn-register.btn-register");
        By flagLocator = By.cssSelector(".page-content-wrapper");

        WebElement profileButton = driver.findElement(By.cssSelector(".icon-nouser"));
        profileButton.click();

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

        Asserts.check(true, String.valueOf(driver.findElement(flagLocator).isEnabled()));

        if( driver.findElement(flagLocator).isEnabled()){
            System.out.println("It's OK, user is logout");
        }else{
            System.out.println("Nope, it's not OK(((00");
        }

        driver.quit();

    }


}
