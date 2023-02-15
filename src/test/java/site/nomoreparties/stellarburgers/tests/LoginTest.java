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

public class LoginTest {

    private final String EXPECTED_LOGIN_TEXT = "Оформить заказ";

    private WebDriver driver;
    private String accessToken;
    private static User user;
    private static UserApi userApi;
    private Header header;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;

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

        user = User.getRandomUser();
        userApi = new UserApi();
        accessToken = userApi.createUser(user);
        mainPage = new MainPage();
        loginPage = new LoginPage();
    }

    @After
    public void tearDown() {
        if (!(accessToken == null)) {
            userApi.deleteUser(accessToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной странице")
    @Description("После выполнения входа на главной странице проверяется наличие кнопки Оформить заказ")
    public void loginByEnterAccountButtonOnMainPage() {
        mainPage.open();
        mainPage.waitForLoadMainPage();
        mainPage.enterAccountButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        assertEquals(EXPECTED_LOGIN_TEXT, mainPage.getOrderButtonText());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("После выполнения входа, на главной странице проверяется наличие кнопки Оформить заказ")
    public void loginByPersonalAccountButtonOnHeader() {
        header = new Header();
        mainPage.open();
        header.personalAccButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        assertEquals(EXPECTED_LOGIN_TEXT, mainPage.getOrderButtonText());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» на странице регистрации")
    @Description("После выполнения входа, на главной странице проверяется наличие кнопки Оформить заказ")
    public void loginByEnterButtonOnRegisterPage() {
        registerPage = new RegisterPage();
        registerPage.open();
        registerPage.waitForLoadRegisterPage();
        registerPage.enterButtonClick();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        assertEquals(EXPECTED_LOGIN_TEXT, mainPage.getOrderButtonText());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» на странице восстановления пароля")
    @Description("После выполнения входа, на главной странице проверяется наличие кнопки Оформить заказ")
    public void loginByEnterButtonOnForgotPasswordPage() {
        forgotPasswordPage = new ForgotPasswordPage();
        forgotPasswordPage.open();
        forgotPasswordPage.waitForLoadForgotPassPage();
        forgotPasswordPage.enterButtonClick();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        assertEquals(EXPECTED_LOGIN_TEXT, mainPage.getOrderButtonText());
    }
}
