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
import site.nomoreparties.stellarburgers.data.UserCredebtials;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

public class LoginTest {

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
        registerPage = open(RegisterPage.REGISTER_PAGE_URL, RegisterPage.class);
        registerPage.waitForLoadRegisterPage();
        registerPage.fillInputsAndRegister(user.getName(), user.getEmail(), user.getPassword());
        UserCredebtials userCredebtials = new UserCredebtials(user.getEmail(), user.getPassword());
        accessToken = userApi.loginUser(userCredebtials);
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
        loginPage = page(LoginPage.class);
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        mainPage.waitForLoadMainPage();
        mainPage.enterAccountButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        assertEquals("Оформить заказ", mainPage.getOrderButtonText());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("После выполнения входа, на главной странице проверяется наличие кнопки Оформить заказ")
    public void loginByPersonalAccountButtonOnHeader() {
        header = page(Header.class);
        loginPage = page(LoginPage.class);
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        header.personalAccButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        assertEquals("Оформить заказ", mainPage.getOrderButtonText());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» на странице регистрации")
    @Description("После выполнения входа, на главной странице проверяется наличие кнопки Оформить заказ")
    public void loginByEnterButtonOnRegisterPage() {
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        open(RegisterPage.REGISTER_PAGE_URL);
        registerPage.waitForLoadRegisterPage();
        registerPage.enterButtonClick();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        assertEquals("Оформить заказ", mainPage.getOrderButtonText());
    }

    @Test
    @DisplayName("Вход через кнопку «Войти» на странице восстановления пароля")
    @Description("После выполнения входа, на главной странице проверяется наличие кнопки Оформить заказ")
    public void loginByEnterButtonOnForgotPasswordPage() {
        mainPage = page(MainPage.class);
        loginPage = page(LoginPage.class);
        forgotPasswordPage = open(ForgotPasswordPage.FORGOT_PASSWORD_PAGE_URL, ForgotPasswordPage.class);
        forgotPasswordPage.waitForLoadForgotPassPage();
        forgotPasswordPage.enterButtonClick();
        loginPage.fillInputsAndLogin(user.getEmail(), user.getPassword());
        mainPage.waitForLoadMainPage();
        assertEquals("Оформить заказ", mainPage.getOrderButtonText());
    }
}
