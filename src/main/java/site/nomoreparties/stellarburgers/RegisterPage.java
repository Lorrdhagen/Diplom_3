package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

import java.time.Duration;

public class RegisterPage {

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private ElementsCollection nameAndEmailInputs;

    @FindBy(how = How.XPATH, using = "//input[@name='Пароль']")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement enterButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement passwordErrorText;

    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private SelenideElement registrationText;

    public void waitForLoadRegisterPage() {
        registrationText.shouldBe(Condition.visible, Duration.ofSeconds(4));
    }

    @Step("Получение текста ошибки при  некорректном пароле")
    public String getPaswordErrorText() {
        return passwordErrorText.getText();
    }

    @Step("Клик по кнопке Войти")
    public void enterButtonClick() {
        enterButton.click();
    }

    @Step("Заполнение полей Имя, Email, Пароль и клик по кнопке Зарегистрироваться")
    public void fillInputsAndRegister(String name, String email, String password) {
        nameAndEmailInputs.get(0).click();
        nameAndEmailInputs.get(0).shouldBe(Condition.focused).setValue(name);
        nameAndEmailInputs.get(1).click();
        nameAndEmailInputs.get(1).shouldBe(Condition.focused).setValue(email);
        passwordInput.click();
        passwordInput.shouldBe(Condition.focused).setValue(password);
        registerButton.click();
    }
}
