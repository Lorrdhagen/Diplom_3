package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.focused;

public class RegisterPage {

    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    private ElementsCollection nameAndEmailInputs = $$(By.xpath("//input[@name='name']"));
    private SelenideElement passwordInput = $(By.xpath("//input[@name='Пароль']"));
    private SelenideElement registerButton = $(By.xpath("//button[text()='Зарегистрироваться']"));
    private SelenideElement enterButton = $(By.xpath("//a[text()='Войти']"));
    private SelenideElement passwordErrorText = $(By.xpath("//p[text()='Некорректный пароль']"));
    private SelenideElement registrationText = $(By.xpath("//h2[text()='Регистрация']"));

    public void waitForLoadRegisterPage() {
        registrationText.shouldBe(visible);
    }

    @Step("Открыть страницу регистрации")
    public void open() {
        Selenide.open(REGISTER_PAGE_URL);
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
        nameAndEmailInputs.get(0).shouldBe(focused).setValue(name);
        nameAndEmailInputs.get(1).click();
        nameAndEmailInputs.get(1).shouldBe(focused).setValue(email);
        passwordInput.click();
        passwordInput.shouldBe(focused).setValue(password);
        registerButton.click();
    }
}
