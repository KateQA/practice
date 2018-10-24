package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {

    public WebDriver driver;

    public String PAGE_URL;

    public BasePage(WebDriver driver){//конструктор класса
        this.driver = driver;
    }

    protected void clickElement(WebElement element, WebDriver driver){
        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOf(element))
                .click();

    }

    protected void sendTextToField(WebElement element, WebDriver driver, CharSequence... text){
        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);

    }

    protected void selectOptionWithText(WebElement select /*подставной параметр - корень выпадающего списка*/,
                                      String text /*текст который надо выбрать*/) {
        select.findElement(By.cssSelector("button.dropdown-toggle")).click(); // найти открывашку выпадающего списка и нажать ее

        WebElement displayedDropDownMenu = new WebDriverWait(driver, 5) // создаем ожидалку на 5 сек
                .ignoring(StaleElementReferenceException.class) // конфигурируем ожидалку чтоб игнорировать случаи с устареванием элемента
                .until((dr -> { // жесть с кастомным ExpectedCondition'ом - эта функция будет выполняться (1 или более раз) внутри ожидалки чтоб определить момент, когда условия ожидания будут соблюдены
                    WebElement dropdownMenu = select.findElement(By.cssSelector("ul[role='menu']")); // найти контейнер с опциями (выпадающий список)

                    if (dropdownMenu.isDisplayed()) { // проверяем, отображается ли список
                        return dropdownMenu; // если да, то возвращаем этот список - вернется как результат выполнения метода until()
                    } else {
                        return null; // знак, чтоб продолжать ожидание
                    }
                }));

        displayedDropDownMenu.findElements(By.cssSelector("li")) // ищем список опций на уже открытом выпадающем списке
                .stream() // превращаем List в Stream для процедур с элементами
                .filter((element) -> element.getText().equals(text)) // фильтруем чтоб оставить в стриме только элементы с нужным нам текстом
                .findFirst() // изъявлям желание взять первый элемент (возвращает "опциональный" элемент - его может не быть)
                .orElseThrow(() -> new NoSuchElementException("No option with text '" + text + "' found")) // на случай, если элемента, все же, нет - бросаем исключение
                .click(); // на найденном первом элементе выполняем клик

        Assert.assertEquals(driver.getCurrentUrl(), "https://orner.com.ua/en");
    }

    protected void waitForAnimation() {
        sleep(0.5);
    }

    protected void sleep(double seconds) {
        try {
            Thread.sleep((int) (seconds * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
