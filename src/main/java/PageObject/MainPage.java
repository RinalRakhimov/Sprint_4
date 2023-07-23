package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {

    private WebDriver driver;

    //локатор стрелочки с вопросом в блоке "Вопросы о важном"
    private By questionLocator;
    //локатор текста ответа
    private By answerLocator;
    // кнопка "Заказать" вверху страницы
    private By orderButtonUpper = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");

    private final String answerText;

    // Добавили конструктор класса page object
    public MainPage (WebDriver driver, String answerText, By questionLocator, By answerLocator){
        this.driver = driver;
        this.answerText = answerText;
        this.answerLocator = answerLocator;
        this.questionLocator = questionLocator;
    }

    //метод прокрутки до блока "Вопросы о важном"
    public void scrollToQuestionBlock() {
        WebElement element = driver.findElement(By.xpath(".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //метод ожидания кликабельности стрелочки с вопросом
    public void waitForQuestionButtonClickable() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(questionLocator));
    }

    //метод нажатия на стрелочку с вопросом
    public void questionButtonClick() {
        driver.findElement(questionLocator).click();
    }

}
