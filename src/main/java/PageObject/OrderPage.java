package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private WebDriver driver;

    // локатор поля "Имя"
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");

    // локатор поля "Фамилия"
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    // локатор поля "Адрес"
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    // локатор поля "Станция метро"
    private By metroStationField = By.xpath(".//div[@class='select-search']");

    // локатор поля "Телефон"
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //локатор кнопки "Далее"
    private By nextButton = By.xpath(".//button[text()='Далее']");
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;

    // локатор поля "Когда привезти самокат"
    private By bringDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // локатор поля "Срока аренды"
    private By rentDurationField = By.xpath(".//div[text()='* Срок аренды']");

    //локатор поля "Комментарий для курьера"
    private By comments = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private String setComments;

    // локатор кнопки "Заказать"
    private By bookingButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    // локатор кнопки "Да" (подтверждение заказа)
    private By orderConfirmButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");

    // локатор всплывающего окна с сообщением об успешном создании заказа
    private By orderConfirmIsVisible = By.xpath(".//div[text()='Заказ оформлен']");

    // Добавили конструктор класса page object
    public OrderPage (WebDriver driver, String name, String surname, String address, String phone, String setComments) {
        this.driver = driver;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.setComments = setComments;
    }

    public void scrollToMetroStation(int stationIndex, String stationName) {
        By metroStationChoosing = By.xpath(".//button[@value='"+ stationIndex +"']/div[text()='"+ stationName +"']");
        WebElement element = driver.findElement(metroStationChoosing);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void userDataFilling(int stationIndex, String stationName) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationField).click();
        scrollToMetroStation(stationIndex, stationName);
        driver.findElement(By.xpath(".//button[@value='"+ stationIndex +"']/div[text()='"+ stationName +"']")).click();
        driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys(phone);
    }

    public void nextButtonClick() {
        driver.findElement(nextButton).click();
    }

    public void rentDetailsFilling(String color, String rentDuration, String date) {
        driver.findElement(bringDateField).clear();
        driver.findElement(bringDateField).click();
        // локатор выбранной даты доставки
        By setDate = By.xpath(".//div[@aria-label='"+ date +"']");
        driver.findElement(setDate).click();
        driver.findElement(rentDurationField).click();
        // локатор выбранного срока аренды
        By setRent = By.xpath(".//div[text()='"+ rentDuration +"']");
        driver.findElement(setRent).click();
        // локатор чекбокса выбора цвета самоката
        By checkbox = By.xpath(".//input[@id='"+ color +"']");
        driver.findElement(checkbox).click();
        driver.findElement(comments).clear();
        driver.findElement(comments).sendKeys(setComments);
    }

    public void clickBookingButton() {
        driver.findElement(bookingButton).click();
    }

    public void orderConfirmation() {
        driver.findElement(orderConfirmButton).click();
    }

    public boolean orderConfirmationStatus() {

        return driver.findElement(orderConfirmIsVisible).isDisplayed();
    }

}
