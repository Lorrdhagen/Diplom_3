package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

import java.time.Duration;

public class LoginPage {

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement enterButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private SelenideElement recoverPasswordButton;

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement entranceText;

    public void waitForLoadLoginPage() {
        entranceText.shouldBe(Condition.visible, Duration.ofSeconds(4));
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
        emailInput.shouldBe(Condition.focused).setValue(email);
        passwordInput.click();
        passwordInput.shouldBe(Condition.focused).setValue(password);
        enterButton.click();
    }
}
