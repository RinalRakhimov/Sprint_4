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

    //локатор станции метро из списка
    private By metroStationChoosing;

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

    // локатор выбранной даты доставки
    private By setDate;

    // локатор поля "Срока аренды"
    private By rentDurationField = By.xpath(".//div[text()='* Срок аренды']");

    // локатор выбранного срока аренды
    private By setRent;

    // локатор чекбокса выбора цвета самоката
    private By checkbox;

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
    public OrderPage (WebDriver driver, String name, String surname, String address,
                      By metroStationChoosing, String phone, By setDate, By setRent, By checkbox, String setComments) {
        this.driver = driver;
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

    public void scrollToMetroStation() {
        WebElement element = driver.findElement(metroStationChoosing);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public void userDataFilling() {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(metroStationField).click();
        scrollToMetroStation();
        driver.findElement(metroStationChoosing).click();
        driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys(phone);
    }

    public void nextButtonClick() {
        driver.findElement(nextButton).click();
    }

    public void rentDetailsFilling() {
        driver.findElement(bringDateField).clear();
        driver.findElement(bringDateField).click();
        driver.findElement(setDate).click();
        driver.findElement(rentDurationField).click();
        driver.findElement(setRent).click();
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

    public boolean OrderConfirmationStatus() {

        return driver.findElement(orderConfirmIsVisible).isDisplayed();
    }

}
