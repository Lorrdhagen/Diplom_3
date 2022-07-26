package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import site.nomoreparties.stellarburgers.*;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        //запуск тестов в Chrome
      //  System.setProperty("webdriver.chrome.driver", "D:\\Games\\Newpapka\\Autotests\\webdrivers\\bin\\chromedriver.exe");

        // Запуск тестов в Yandex
        System.setProperty("webdriver.yandexdriver", "D:\\Games\\Newpapka\\Autotests\\webdrivers\\bin\\yandexdriver.exe");
        options.setBinary("C:\\Users\\LordHagen\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);

        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.waitForLoadMainPage();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Переход кразделу «Начинки»")
    @Description("После перехода выполняется клик по начинке, в модальном окне проверяется  наличие заголовка «Детали ингредиента»")
    public void fillingsTabAndIngredientClickShouldSeeFillingsAndDetails() {
        mainPage.fillingsTabClick();
        Selenide.sleep(500);
        mainPage.immortalMollusksClick();
        Selenide.sleep(500);
        assertEquals("Детали ингредиента", mainPage.getIngredientDetailText());
    }

    @Test
    @DisplayName("Переход кразделу «Соусы»")
    @Description("После перехода выполняется клик по начинке, в модальном окне проверяется  наличие заголовка «Детали ингредиента»")
    public void sauceTabAndIngredientClickShouldSeeSaucesAndDetails() {
        mainPage.sauceTabClick();
        Selenide.sleep(500);
        mainPage.spaceSauceClick();
        Selenide.sleep(500);
        assertEquals("Детали ингредиента", mainPage.getIngredientDetailText());
    }

    @Test
    @DisplayName("Переход кразделу «Булки»")
    @Description("После перехода выполняется клик по начинке, в модальном окне проверяется  наличие заголовка «Детали ингредиента»")
    public void fillingsTabThenBunTabAndBunClickShouldSeeBunsAndDetails() {
        mainPage.fillingsTabClick();
        Selenide.sleep(500);
        mainPage.bunTabClick();
        Selenide.sleep(500);
        mainPage.craterBunClick();
        Selenide.sleep(500);
        assertEquals("Детали ингредиента", mainPage.getIngredientDetailText());
    }
}
