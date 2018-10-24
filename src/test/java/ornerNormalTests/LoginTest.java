package ornerNormalTests;

import etc.DataProviders;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.OrnerLoginPage;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    String mailString = "testkatytest@gmail.com";// такие поля с инфой храним в тесте, а не в пейдже, так легче будет исправить, изменить, удалить и тд.
    String passwordString = "A5fg48BW378O";
    String languageToSelect = "English";
    public WebDriver driver;
    OrnerLoginPage ornerLoginPage;

    @BeforeTest(alwaysRun = true)
    public void setupDriver(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\selenium\\drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        ornerLoginPage = PageFactory.initElements(driver, OrnerLoginPage.class);//для инициализации и нахождения элементов под аннотацией @FindBy используем PageFactory
    }

    @AfterTest(alwaysRun = true)
    public void quitDriver(){
        this.driver.quit();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "negativeValues")
    public void negativeTest(String email, boolean isEmailHighlighted, String password, boolean isPasswordHighlighted) {
        OrnerLoginPage loginPage = ornerLoginPage.get()
                .openLoginForm()
                .fillEmailField(email)
                .fillPasswordField(password)
                .clickEnterButton();

        Assert.assertEquals(loginPage.isEmailFieldHighlighted(), isEmailHighlighted, "Email field is not highlighted");
        Assert.assertEquals(loginPage.isPasswordFieldHighlighted(), isPasswordHighlighted, "Password field is not highlighted");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "positiveValues")
    public void correctLogin(String mailString, String passwordString) {
        ornerLoginPage.get()
                .login(mailString, passwordString);
        driver.manage().deleteAllCookies();

                //.selectLanguage(languageToSelect);
        //ornerLoginPage.waitingOfDisplayingUserFlag();
        //ornerLoginPage.userIconClick(); // для этого и писали return this в методах
        /*
                .userIconClick(); // для этого и писали return this в методах
        ornerLoginPage.alreadyHaveHrefClick();
        ornerLoginPage.fillEmailField(mailString);
        ornerLoginPage.fillPasswordField(passwordString);
        ornerLoginPage.clickEnterButton();
        ornerLoginPage.waitingOfDisplayingUserFlag();
        ornerLoginPage.selectLanguage(languageToSelect);*/
    }

        //new WebDriverWait(driver, 5)

        //.until(ExpectedConditions.visibilityOfElementLocated(userIconLocator))

        //.click();

        //

        //new WebDriverWait(driver, 7)

        //.until(ExpectedConditions.visibilityOfElementLocated(alreadyHaveHrefLocator))

        //.click();

        //

        //new WebDriverWait(driver, 5)

        //.until(ExpectedConditions.visibilityOfElementLocated(mailFieldLocator))

        //.sendKeys(mailString);

        //

        //WebElement passwordField = driver.findElement(passwordFieldLocator);

        //passwordField.sendKeys(passwordString);

        //

        //new WebDriverWait(driver, 5)

        //.until(ExpectedConditions.visibilityOfElementLocated(enterButtonLocator))

        //.click();

        //

        //new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(flagLocator));

        //

        /*if( driver.findElement((flagLocator)).isDisplayed()){
        System.out.println("User is Login!");
        }else{
        System.out.println("Ooops, something wrong, user is not login!");
        }*/

}
