import PageObject.MainPage;
import PageObject.OrderPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestOfScooterOrder {


    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String rentDuration;

    private String date;
    private int stationIndex;
    private String stationName;
    private String color;
    private String setComments;

    public TestOfScooterOrder(String name, String surname, String address,
                              int stationIndex, String stationName, String phone, String date, String rentDuration, String color, String setComments) {

        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationIndex = stationIndex;
        this.stationName = stationName;
        this.phone = phone;
        this.date = date;
        this.rentDuration = rentDuration;
        this.color = color;
        this.setComments = setComments;
    }


    @Parameterized.Parameters(name = "Заказ самоката")
    public static Object[][] getTestData() {
        //Сгенерируй тестовые данные
        return new Object[][]{
                {"Иван", "Грозный", "г. Москва, Народная улица, 4с1", 1, "Бульвар Рокоссовского",
                        "+79378546748", "Choose пятница, 28-е июля 2023 г.",
                        "двое суток", "black", "Можно побыстрей? Хочу кататься!"},
                {"Ляпис", "Трубецкой", "г. Москва, Нижегородская улица, 7", 114, "Ясенево",
                        "+79773335555", "Choose вторник, 1-е августа 2023 г.",
                        "семеро суток", "grey", "Когда мне было 15 лет, я копил на новенький мопед..."},
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
    public void checkOrder() {


        MainPage objMainPage = new MainPage(driver);
        OrderPage objOrderPage = new OrderPage(driver, name, surname, address, phone, setComments);
        objMainPage.open();
        // кликаем по верхней кнопке "Заказать"
        objMainPage.upperOrderButtonClick();
        // проверяем, что страница заказа открылась
        assertTrue("Страница заказа не открылась", objOrderPage.nameFieldIsVisible());
        objMainPage.open();
        objMainPage.scrollToLowerOrderButton();
        // кликаем по нижней кнопке "Заказать"
        objMainPage.lowerOrderButtonClick();
        //проверяем, что страница открылась
        assertTrue("Страница заказа не открылась", objOrderPage.nameFieldIsVisible());


        objOrderPage.userDataFilling(stationIndex, stationName);
        objOrderPage.nextButtonClick();
        objOrderPage.rentDetailsFilling(color, rentDuration, date);
        objOrderPage.clickBookingButton();
        objOrderPage.orderConfirmation();
        assertTrue("Не удалось создать заказ", objOrderPage.orderConfirmationStatus());
        System.out.println("Заказ успешно оформлен!");

    }

    @After
    public void after() {
        // Закрой браузер
        driver.quit();
    }
}