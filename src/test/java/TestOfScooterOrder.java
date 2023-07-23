import PageObject.OrderPage;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestOfScooterOrder {

    private By orderButtonUpper = By.xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    private final String name;
    private final String surname;
    private final String address;
    private final By metroStationChoosing;
    private final String phone;
    private By setDate;
    private By setRent;
    private By checkbox;
    private String setComments;

    public TestOfScooterOrder(String name, String surname, String address,
                              By metroStationChoosing, String phone, By setDate, By setRent, By checkbox, String setComments) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStationChoosing = metroStationChoosing;
        this.phone = phone;
        this.setDate = setDate;
        this.setRent = setRent;
        this.checkbox = checkbox;
        this.setComments = setComments;
    }


    @Parameterized.Parameters(name = "Заказ самоката")
    public static Object[][] getTestData() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][]{
                {"Иван", "Грозный", "г. Москва, Народная улица, 4с1", By.xpath(".//div[@class='select-search__select']/ul/li/button[@value='1']/div[text()='Бульвар Рокоссовского']"),
                        "+79378546748", By.xpath(".//div[@aria-label='Choose пятница, 28-е июля 2023 г.']"),
                        By.xpath(".//div[text()='двое суток']"), By.xpath(".//input[@id='black']"), "Можно побыстрей? Хочу кататься!"},
                {"Ляпис", "Трубецкой", "г. Москва, Нижегородская улица, 7", By.xpath(".//div[@class='select-search__select']/ul/li/button[@value='114']/div[text()='Ясенево']"),
                        "+79773335555", By.xpath(".//div[@aria-label='Choose вторник, 1-е августа 2023 г.']"),
                        By.xpath(".//div[text()='семеро суток']"), By.xpath(".//input[@id='grey']"), "Когда мне было 15 лет, я копил на новенький мопед..."},
        };

    }

    private WebDriver driver;

    String URL = "https://qa-scooter.praktikum-services.ru/";

    @org.junit.Test
    public void checkOrder() {
        driver = new ChromeDriver();
        driver.get(URL);
        driver.findElement(orderButtonUpper).click();
        OrderPage objOrderPage = new OrderPage(driver, name, surname, address, metroStationChoosing, phone, setDate, setRent, checkbox, setComments);
        objOrderPage.userDataFilling();
        objOrderPage.nextButtonClick();
        objOrderPage.rentDetailsFilling();
        objOrderPage.clickBookingButton();
        objOrderPage.orderConfirmation();
        assertTrue("Не удалось создать заказ", objOrderPage.OrderConfirmationStatus());
        System.out.println("Заказ успешно оформлен!");

    }

    @After
    public void after() {
        // Закрой браузер
        driver.quit();
    }
}