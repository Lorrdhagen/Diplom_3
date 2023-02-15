package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.focused;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    private SelenideElement emailInput = $(By.xpath("//input[@name='name']"));
    private SelenideElement passwordInput = $(By.xpath("//input[@name='Пароль']"));
    private SelenideElement enterButton = $(By.xpath("//button[text()='Войти']"));
    private SelenideElement registerButton = $(By.xpath("//a[text()='Зарегистрироваться']"));
    private SelenideElement recoverPasswordButton = $(By.xpath("//a[text()='Восстановить пароль']"));
    private SelenideElement entranceText = $(By.xpath("//h2[text()='Вход']"));

    public void waitForLoadLoginPage() {
        entranceText.shouldBe(visible);
    }

    @Step("Открыть страницу входа")
    public void open() {
        Selenide.open(LOGIN_PAGE_URL);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void registerButtonClick() {
        registerButton.click();
    }

    @Step("Получение текста заголовка Вход")
    public String getEntranceText() {
        return entranceText.getText();
    }

    @Step("Заполнение полей Email, Пароль и клик по кнопке Войти")
    public void fillInputsAndLogin(String email, String password) {
        emailInput.click();
        emailInput.shouldBe(focused).setValue(email);
        passwordInput.click();
        passwordInput.shouldBe(focused).setValue(password);
        enterButton.click();
    }
}
