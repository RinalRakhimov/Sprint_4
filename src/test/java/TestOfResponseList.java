import PageObject.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)

public class TestOfResponseList {


    private int index;
    private String questionText;
    private String answerText;


    public TestOfResponseList(int index, String questionText, String answerText) {
        this.index = index;
        this.questionText = questionText;
        this.answerText = answerText;

    }


    @Parameterized.Parameters()
    public static Object[][] getTestData() {
        //Сгенерируй тестовые данные
        return new Object[][]{
                {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
        };

    }

    private WebDriver driver;

    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver();
    }

    @Test
    public void checkOfResponseList() {

        // переход на страницу тестового приложения
        MainPage objMainPage = new MainPage(driver);

        objMainPage.open();
        objMainPage.scrollToQuestionBlock();
        objMainPage.getFAQElement(index);
        objMainPage.questionButtonClick(index);


        assertEquals("Текст вопроса не соотвестсвует ожидаемому", questionText, objMainPage.questionList(index));
        assertEquals("Текст ответа не соотвестсвует ожидаемому", answerText, objMainPage.responseList(index));

    }

    @After
    public void after() {
        // Закрой браузер
        driver.quit();
    }
}