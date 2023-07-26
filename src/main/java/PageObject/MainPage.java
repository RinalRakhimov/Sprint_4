package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MainPage {

    private final WebDriver driver;

    public String URL = "https://qa-scooter.praktikum-services.ru/";

    // локатор для поиска элемента из списка вопросов
    private By FAQ = By.xpath(".//div[@class='accordion__item']");

    // локатор для поиска дочернего элемента списка вопросов с возможностью кликать на него
    private By FAQIncludedClickableButton = By.xpath(".//div[@class='accordion__heading']");

    // локатор для поиска среди элементов ответа
    private By responseFAQ = By.xpath(".//p");


    // Добавили конструктор класса page object
    public MainPage (WebDriver driver) {

        this.driver = driver;
    }
     public void open() {

        driver.get(URL);
     }

    //метод прокрутки до блока "Вопросы о важном"
    public void scrollToQuestionBlock() {
        WebElement element = driver.findElement(By.xpath(".//div[@class='Home_SubHeader__zwi_E' and text()='Вопросы о важном']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToOrderButton(String buttonClassName) {
        WebElement element = driver.findElement(By.xpath(".//button[@class='" + buttonClassName + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public WebElement getFAQElement(int index) {
        List<WebElement> itemList = driver.findElements(FAQ);
        return itemList.get(index);
    }

    //метод нажатия на стрелочку с вопросом
    public void questionButtonClick(int index) {

        WebElement item = getFAQElement(index);
        WebElement itemButton = item.findElement(FAQIncludedClickableButton);
        itemButton.click();
    }

    public String questionList(int index) {
        WebElement item = getFAQElement(index);
        WebElement questionElement = item.findElement(FAQIncludedClickableButton);
        return questionElement.getText();
    }

    public String responseList(int index) {
        WebElement item = getFAQElement(index);
        WebElement responseElement = item.findElement(responseFAQ);
        return responseElement.getText();
    }

    public void orderButtonClick(String buttonClassName) {
        driver.findElement(By.xpath(".//button[@class='"+ buttonClassName +"']")).click();
    }

}
