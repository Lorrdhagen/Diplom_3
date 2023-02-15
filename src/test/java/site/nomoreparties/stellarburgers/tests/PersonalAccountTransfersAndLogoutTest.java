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
import site.nomoreparties.stellarburgers.data.User;
import site.nomoreparties.stellarburgers.data.UserApi;
import com.codeborne.selenide.WebDriverRunner;

import static org.junit.Assert.assertEquals;

public class PersonalAccountTransfersAndLogoutTest {

    private WebDriver driver;
    private String accessToken;
    private static User user;
    private static UserApi userApi;
    private Header header;
    private MainPage mainPage;
    private LoginPage loginPage;
    private AccountProfilePage accountProfilePage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        //запуск тестов в Chrome
        //System.setProperty("webdriver.chrome.driver", "D:\\Games\\Newpapka\\Autotests\\webdrivers\\bin\\chromedriver.exe");

        // Запуск тестов в Yandex
        System.setProperty("webdriver.yandexdriver", "D:\\Games\\Newpapka\\Autotests\\webdrivers\\bin\\yandexdriver.exe");
        options.setBinary("C:\\Users\\LordHagen\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

        options.addArguments("test-type=browser");
        options.addArguments("chromeoptions.args", "--no-sandbox");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);

        user = User.getRandomUser();
        userApi = new UserApi();
        header = new Header();
        mainPage = new MainPage();
        loginPage = new LoginPage();
        accountProfilePage = new AccountProfilePage();
        accessToken = userApi.createUser(user);
        loginPage.open();
        loginPage.waitForLoadLoginPage();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();

    }

    @After
    public void tearDown() {
        if (!(accessToken == null)) {
            userApi.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на кнопку «Личный кабинет»")
    @Description("После выполнения выполнения перехода в личном кабинете проверяется наличие вкладки «Профиль»")
    public void personalAccountTransferByPersonalAccButtonOnHeader() {
        header.personalAccButtonClick();
        accountProfilePage.waitForLoadAccProfilePage();
        assertEquals("Профиль", accountProfilePage.getUserProfileText());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на кнопку «Конструктор»")
    @Description("После выполнения выполнения перехода, на главной странице проверяется наличие заголовка «Соберите бургер»")
    public void transferToConstructorFromPersonalAccByConstructorButton() {
        header.personalAccButtonClick();
        accountProfilePage.waitForLoadAccProfilePage();
        header.constructorButtonClick();
        mainPage.waitForLoadMainPage();
        assertEquals("Соберите бургер", mainPage.getAssembleTheBurgerText());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип «Stellar Burgers»")
    @Description("После выполнения выполнения перехода, на главной странице проверяется наличие заголовка «Соберите бургер»")
    public void transferToConstructorFromPersonalAccByStellarBurgersLogo() {
        header.personalAccButtonClick();
        accountProfilePage.waitForLoadAccProfilePage();
        header.stellarBurgerLogoClick();
        mainPage.waitForLoadMainPage();
        assertEquals("Соберите бургер", mainPage.getAssembleTheBurgerText());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    @Description("После выполнения выхода из аккаунта, проверяется наличие заголовка «Вход»")
    public void personalAccountExit() {
        header.personalAccButtonClick();
        accountProfilePage.waitForLoadAccProfilePage();
        accountProfilePage.exitButtonClick();
        loginPage.waitForLoadLoginPage();
        assertEquals("Вход", loginPage.getEntranceText());
    }
}
