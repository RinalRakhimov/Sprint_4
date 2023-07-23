import PageObject.MainPage;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class TestOfResponseList {


    private final By questionLocator;
    private final By answerLocator;
    private final String answerText;


    public TestOfResponseList(By questionLocator, By answerLocator, String answerText) {
        this.questionLocator = questionLocator;
        this.answerLocator = answerLocator;
        this.answerText = answerText;

    }


    @Parameterized.Parameters(name = "Ожидаемый текст: {2}")
    public static Object[][] getTestData() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][]{
                {By.xpath(".//div[@id='accordion__heading-0' and text()='Сколько это стоит? И как оплатить?']"), By.xpath(".//div[@id='accordion__panel-0']/p[text()='Сутки — 400 рублей. Оплата курьеру — наличными или картой.']"),
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {By.xpath(".//div[@id='accordion__heading-7' and text()='Я жизу за МКАДом, привезёте?']"), By.xpath(".//div[@id='accordion__panel-7']/p[text()='Да, обязательно. Всем самокатов! И Москве, и Московской области.']"),
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };

    }

    private WebDriver driver;

    String URL = "https://qa-scooter.praktikum-services.ru/";

    @org.junit.Test
    public void checkOfResponseList() {
        // драйвер для браузера Chrome

        driver = new ChromeDriver();
        // переход на страницу тестового приложения
        driver.get(URL);

        MainPage objMainPage = new MainPage(driver, answerText, questionLocator, answerLocator);

        objMainPage.scrollToQuestionBlock();
        objMainPage.waitForQuestionButtonClickable();
        objMainPage.questionButtonClick();

        assertEquals("Текст не соотвестсвует ожидаемому", answerText, driver.findElement(answerLocator).getText());
        System.out.println("При нажатии на стрелочку с вопросом: " + driver.findElement(questionLocator).getText());
        System.out.println("Открывается ожидаемый текст: " + driver.findElement(answerLocator).getText());
    }

    @After
    public void after() {
        // Закрой браузер
        driver.quit();
    }
}