package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import site.nomoreparties.stellarburgers.MainPage;
import site.nomoreparties.stellarburgers.LoginPage;
import site.nomoreparties.stellarburgers.RegisterPage;
import site.nomoreparties.stellarburgers.data.User;
import site.nomoreparties.stellarburgers.data.UserApi;
import site.nomoreparties.stellarburgers.data.UserCredebtials;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {

    private WebDriver driver;
    private String accessToken;
    private static User user;
    private static UserApi userApi;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;

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

        loginPage = page(LoginPage.class);
        registerPage = page(RegisterPage.class);
        user = User.getRandomUser();
        userApi = new UserApi();
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
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
    @DisplayName("Регистрация пользователя с валидными данными")
    @Description("Заполнение полей валидными данными, после регистрации проверяется наличие заголовка Вход")
    public void userRegistrationWithValidData() {
        mainPage.enterAccountButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.registerButtonClick();
        registerPage.waitForLoadRegisterPage();
        registerPage.fillInputsAndRegister(user.getName(), user.getEmail(), user.getPassword());
        loginPage.waitForLoadLoginPage();
        UserCredebtials userCredebtials = new UserCredebtials(user.getEmail(), user.getPassword());
        accessToken = userApi.loginUser(userCredebtials);
        assertEquals("Вход", loginPage.getEntranceText());
    }

    @Test
    @DisplayName("Регистрация пользователя с невалидным паролем")
    @Description("Регистрация с паролем менее шести символов, проверка сообщения об ошибке")
    public void shouldSeePasswordFieldErrorMessage() {
        mainPage.enterAccountButtonClick();
        loginPage.waitForLoadLoginPage();
        loginPage.registerButtonClick();
        registerPage.waitForLoadRegisterPage();
        user.setPassword("abc12");
        registerPage.fillInputsAndRegister(user.getName(), user.getEmail(), user.getPassword());
        UserCredebtials userCredebtials = new UserCredebtials(user.getEmail(), user.getPassword());
        accessToken = userApi.loginUser(userCredebtials);
        assertEquals("Некорректный пароль", registerPage.getPaswordErrorText());
    }
}
