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
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private final String EXPECTED_TAB_CLASS = "tab_tab_type_current__2BEPc";
    private final String EXPECTED_MODAL_CLASS = "Modal_modal_opened__3ISw4";
    private final String SAUCE_NAME = "Соус с шипами Антарианского плоскоходца";
    private final String FILLING_NAME = "Сыр с астероидной плесенью";
    private final String BUN_NAME = "Краторная булка N-200i";


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
    @Description("После перехода выполняется клик по начинке, проверяется изменение класса раздела «Начинки» и модального окна ингридиента")
    public void fillingsTabAndIngredientClickShouldSeeFillingsAndDetails() {
        mainPage.fillingsTabClick();
        mainPage.ingredientClick(FILLING_NAME);
        assertTrue(mainPage.checkFillingTabContainsClass(EXPECTED_TAB_CLASS));
        assertTrue(mainPage.checkModalContainsClass(EXPECTED_MODAL_CLASS));
    }

    @Test
    @DisplayName("Переход кразделу «Соусы»")
    @Description("После перехода выполняется клик по начинке, проверяется изменение класса раздела «Соусы» и модального окна ингридиента")
    public void sauceTabAndIngredientClickShouldSeeSaucesAndDetails() {
        mainPage.sauceTabClick();
        assertTrue(mainPage.checkSauceTabContainsClass(EXPECTED_TAB_CLASS));
        mainPage.ingredientClick(SAUCE_NAME);
        assertTrue(mainPage.checkModalContainsClass(EXPECTED_MODAL_CLASS));

    }

    @Test
    @DisplayName("Переход кразделу «Булки»")
    @Description("После перехода выполняется клик по начинке, проверяется изменение класса раздела «Булки» и модального окна ингридиента")
    public void fillingsTabThenBunTabAndBunClickShouldSeeBunsAndDetails() {
        mainPage.sauceTabClick();
        mainPage.bunTabClick();
        mainPage.ingredientClick(BUN_NAME);
        assertTrue(mainPage.checkBunTabContainsClass(EXPECTED_TAB_CLASS));
        assertTrue(mainPage.checkModalContainsClass(EXPECTED_MODAL_CLASS));
    }
}
