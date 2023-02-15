package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class ForgotPasswordPage {

    public static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    private SelenideElement emailInput = $(By.xpath("//label[text()='Email']"));
    private SelenideElement recoverButton = $(By.xpath("//button[text()='Восстановить']"));
    private SelenideElement enterButton = $(By.xpath("//a[text()='Войти']"));
    private SelenideElement recoverPassText = $(By.xpath("//h2[text()='Восстановление пароля']"));

    public void waitForLoadForgotPassPage() {
        recoverPassText.shouldBe(visible);
    }

    @Step("Открыть страницу восстановления пароля")
    public void open() {
        Selenide.open(FORGOT_PASSWORD_PAGE_URL);
    }

    @Step("Клик по кнопке Войти")
    public void enterButtonClick() {
        enterButton.click();
    }
}
