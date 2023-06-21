package ru.gudimenko.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TrainingAppline {
    WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://training.appline.ru/user/login");
    }

    @Test
    public void test() {


//  Заполнить полее ввода имени
        WebElement loginField = driver.findElement(By.id("prependedInput"));
        loginField.click();
        loginField.clear();
        loginField.sendKeys("Taraskina Valeriya");

//  Заполнить поле ввода пароль
        WebElement passwordField = driver.findElement(By.id("prependedInput2"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("testing");

//  Нажать кнопку Войти
        WebElement buttonCome = driver.findElement(By.xpath("//button[@id = '_submit']"));
        buttonCome.click();


//  Проверка загловка
        WebElement titlePanel = driver.findElement(By.xpath("//h1[@class = 'oro-subtitle']"));
        Assert.assertEquals("Текст загловка не совпал", "Панель быстрого запуска", titlePanel.getText());

//
        WebElement dropdownExpenses = driver.findElement(By.xpath("//span[@class = 'title' and text() = 'Расходы']"));
        dropdownExpenses.click();

//
        WebElement bussnesTrip = driver.findElement(By.xpath("//span[@class = 'title' and text() = 'Командировки']"));
        bussnesTrip.click();


//   Переход на экран "Создать командировку"
        WebElement createBusinessTrip = driver.findElement(By.xpath("//a[contains(@class,'icons-holder-text')]"));
        createBusinessTrip.click();

        WebElement pageCreatebusinessTrips = driver.findElement(By.xpath("//h1[@class='user-name']"));
        Assert.assertEquals("Заголовок отсутствует или ошибка в тексте", "Создать командировку", pageCreatebusinessTrips.getText());

//выбрать подразделение
        WebElement subdivisionDropdown = driver.findElement(By.name("crm_business_trip[businessUnit]"));
        Select subdivisionSelect = new Select(subdivisionDropdown);
        subdivisionSelect.selectByVisibleText("Отдел внутренней разработки");

// заполнить поле "Принимающая организация"
        WebElement openListButton = driver.findElement(By.id("company-selector-show"));
        openListButton.click();
        WebElement chooseOrganization = driver.findElement(By.xpath("//span[@class='select2-chosen']"));
        chooseOrganization.click();
        WebElement fieldcChooseOrganization = driver.findElement(By.xpath("//input[contains(@class,'select2-input')]"));

        fieldcChooseOrganization.sendKeys("(Edge) Призрачная Организация Охотников");
        WebElement chooseOrganizationResult = driver.findElement(By.xpath("//span[contains(text(),'(Edge) Призрачная Организация Охотников')]"));

        chooseOrganizationResult.click();

// выбрать чекбокс
        WebElement checkboxTicket = driver.findElement(By.xpath("//input[@data-name='field__1']"));
        checkboxTicket.click();

// заполнить поля выезда и заезда
        WebElement fieldDepartureCity = driver.findElement(By.xpath("//input[@data-name='field__departure-city']"));
        fieldDepartureCity.sendKeys("Россия, Омск");


        WebElement fieldArrivalCity = driver.findElement(By.xpath("//input[@data-name='field__arrival-city']"));
        fieldArrivalCity.sendKeys("Россия, Воронеж");


//заполнить поля даты
        WebElement fieldDatesDeparture = driver.findElement(By.xpath("//input[contains(@name, 'date_selector_crm_business_trip_departureDatePlan')]"));
        fieldDatesDeparture.sendKeys("24.06.2023");
        driver.findElement(By.xpath("//html")).click();


        WebElement fieldDatesReturn = driver.findElement(By.xpath("//input[contains(@name, 'date_selector_crm_business_trip_returnDatePlan')]"));
        fieldDatesReturn.sendKeys("27.06.2023");
        driver.findElement(By.xpath("//html")).click();

// нажать на кнопку "Сохранить и закрыть"
        WebElement buttonSaveClose = driver.findElement(By.xpath("//button[@class='btn btn-success action-button']"));
        buttonSaveClose.click();

//проверка ошибки на странице
        WebElement validationError = driver.findElement(By.xpath("//span[@class='validation-failed']"));

        Assert.assertEquals("Проверка ошибки у поля не была пройдена",
                "Список командируемых сотрудников не может быть пустым", validationError.getText());


    }

    @After
    public void after() {
        driver.quit();
    }


}
