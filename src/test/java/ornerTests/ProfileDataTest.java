package ornerTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class ProfileDataTest {

    private static final String DIGITS = "0123456789";

    private static String getNumber(int length){
        Random r = new Random();
        String result = "";//начальное значение строки, чтобы было от чего отталкиваться, чтоб не было null или чего-то еще

        for (int i = 0; i < length; ++i){
            int availableCharsLength = DIGITS.length();//узнаем длину строки DIGITS (максимальный доступный индекс)
            int randomIndex = r.nextInt(availableCharsLength);//из доступных нам индексов символов выбираем один в пределах длины доступной строки
            char randomChar = DIGITS.charAt(randomIndex);//получаем именно символ, который расположен под индексом, выбранным нами ранее
            result = result + randomChar;//добавляем по одному символу после прохождения цикла к нужной нам строке, из цикла выйдем когда наберется 10 символов(DIGITS.length)
        }
        return result;
    }

    /*private static String getNumber2(int length) {
        RandomString randomStringGenerator = new RandomString(length, new Random(), RandomString.digits);

        return randomStringGenerator.nextString();
    }*/

    public static void main(String[] args) throws InterruptedException {

        final String mailString = "testkatytest@gmail.com";
        final String passwordString = "A5fg48BW378O";

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://orner.com.ua/");

            By alreadyHaveHrefLocator = By.id("already-have-an-account");
            By enterButtonLocator = By.cssSelector("#login > div.register-form > form > div.btn-register.btn-register");
            By flagLocator = By.cssSelector(".page-content-wrapper");
            By phoneFieldLocator = By.id("change-phone");
            By userIconLocator = By.cssSelector(".icon-nouser");

            driver.findElement(userIconLocator).click();

            // click(alreadyHaveHrefLocator, driver);
            new WebDriverWait(driver, 7)
                    .until(ExpectedConditions.visibilityOfElementLocated(alreadyHaveHrefLocator))
                    .click();

            // sendKeys(By.cssSelector("#login-email"), driver, mailString);
            new WebDriverWait(driver, 15)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#login-email")))
                    .sendKeys(mailString);

            WebElement passwordField = driver.findElement(By.cssSelector("#login-password"));
            passwordField.sendKeys(passwordString);

            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.visibilityOfElementLocated(enterButtonLocator))
                    .click();

            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(flagLocator));

            new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(userIconLocator))
                    .click();

            String telFieldBefore = driver.findElement(phoneFieldLocator).getAttribute("value");

            /*List<Integer> telNumber = new ArrayList<>();
            for (int i = 0; i < 10; i++) telNumber.add(i);
            Collections.shuffle(telNumber);
            System.out.println(telNumber);*/
            String number = getNumber(15);

            driver.findElement(phoneFieldLocator).sendKeys(number);

            /*new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(phoneFieldLocator))
                    .sendKeys(number);*/

            new WebDriverWait(driver, 5).until((dr) -> !dr.findElement(phoneFieldLocator).getAttribute("value").equals(telFieldBefore));
            String newNum = driver.findElement(phoneFieldLocator).getAttribute("value");
            System.out.println("The old numb is: " + telFieldBefore + " The new numb is: " + newNum);


        } finally {
            driver.quit();
        }
    }

    /*public static void click(By locator, WebDriver driver) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(locator))
                .click();
    }

    public static void sendKeys(By locator, WebDriver driver, CharSequence... keys) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(locator))
                .sendKeys(keys);
    }*/

}
