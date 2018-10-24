package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OrnerLoginPage extends BasePage{

    //WebDriver driver; при наследовании обявление здесь драйвера нам не нужно
    //OrnerLoginPage ornerLoginPage;
    // String mailString = "testkatytest@gmail.com";
    String passwordString = "A5fg48BW378O";

    @FindBy(css = ".icon-nouser")
    WebElement userIcon;

    @FindBy(id = "already-have-an-account")
    WebElement alreadyHaveHref;

    @FindBy(css = "#login-email")
    WebElement mailField;

    @FindBy(css = "#login-password")
    WebElement passwordField;

    @FindBy(css = "#login > div.register-form > form > div.btn-register.btn-register")
    WebElement enterButton;

    @FindBy(css = ".page-content-wrapper")
    WebElement accountFlag;

    @FindBy(css = ".header-address .bootstrap-select.language")
    WebElement dropdownLanguageMenu;

    @FindBy(css = "label[for='login-email']")
    WebElement emailFieldLabel;

    @FindBy(css = "label[for='login-password']")
    WebElement passwordFieldLabel;

    /*By userIconLocator = By.cssSelector(".icon-nouser");
    By alreadyHaveHrefLocator = By.id("already-have-an-account");
    By mailFieldLocator = By.cssSelector("#login-email");
    By passwordFieldLocator = By.cssSelector("#login-password");
    By enterButtonLocator = By.cssSelector("#login > div.register-form > form > div.btn-register.btn-register");
    By flagLocator = By.cssSelector(".page-content-wrapper");*/

    public OrnerLoginPage(WebDriver driver) {//конструткор класса с наследованием вебдрайвера из BasePage
        super(driver);
        this.PAGE_URL = "https://orner.com.ua/";
    }

    public OrnerLoginPage get() { //переход на нужную нас страницу с помощью драйвера тоже записываем в метод и храним в пейдже
        this.driver.get(PAGE_URL);

        return this;
    }

    /*public OrnerLoginPage userIconClick() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(userIcon))
                .click();

        return this;
    }*/

    public OrnerLoginPage userIconClick(){
        clickElement(userIcon, driver);

        return this;
    }

    /*public OrnerLoginPage alreadyHaveHrefClick() {
        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOf(alreadyHaveHref))
                .click();

        return this;//используем в методах пейджи return this для того чтобы в тестовой логике вызывать эти методы с единожды написанным экземпляром пейджи
        //в сигнатуре метода не пишем войд(для ретерна) и указываем пейджу перед непосредственным названием метода.
    }*/

    public void alreadyHaveHrefClick(){
        waitForAnimation();
        clickElement(alreadyHaveHref, driver);
    }


    /*public OrnerLoginPage fillEmailField(String mailString) {//передаем аргумент в виде уже инициализированной строки ,а не сразу значение в метод sendKeys,чтобы легче потом было исправить,изменить и тд
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(mailField)).sendKeys(mailString);

        return this;
    }*/

    public OrnerLoginPage fillEmailField(String mailString) {
        sendTextToField(mailField, driver, mailString);

        return this;
    }

    /*public OrnerLoginPage fillPasswordField(String passwordString) {
        passwordField.sendKeys(passwordString);
        return this;
    }*/

    public OrnerLoginPage fillPasswordField(String passwordString){
        sendTextToField(passwordField, driver, passwordString);

        return this;
    }

    public OrnerLoginPage clickEnterButton(){
        clickElement(enterButton, driver);

        return this;
    }

    /*public OrnerLoginPage clickEnterButton() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(enterButton))
                .click();

        return this;
    }*/

    public OrnerLoginPage waitingOfDisplayingUserFlag() {
        Assert.assertEquals(true, accountFlag.isDisplayed());
        return this;

    }

    public OrnerLoginPage selectLanguage(String text) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(dropdownLanguageMenu));
        selectOptionWithText(dropdownLanguageMenu, text);

        return this;
    }

    public OrnerLoginPage login(String email, String password) {
        openLoginForm();
        fillEmailField(email);
        fillPasswordField(password);
        clickEnterButton();
        waitingOfDisplayingUserFlag();

        return this;
    }

    public OrnerLoginPage openLoginForm() {
        userIconClick();
        alreadyHaveHrefClick();

        return this;
    }

    public boolean isEmailFieldHighlighted() {
        String classAttribute = emailFieldLabel.getAttribute("class");

        return classAttribute != null && classAttribute.contains("state-error");
    }

    public boolean isPasswordFieldHighlighted() {
        String classAttribute = passwordFieldLabel.getAttribute("class");

        return classAttribute != null && classAttribute.contains("state-error");
    }

}
