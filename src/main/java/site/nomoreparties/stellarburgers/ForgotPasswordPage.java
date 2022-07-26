package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

import java.time.Duration;

public class ForgotPasswordPage {

    public static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.XPATH, using = "//label[text()='Email']")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Восстановить']")
    private SelenideElement recoverButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement enterButton;

    @FindBy(how = How.XPATH, using = "//h2[text()='Восстановление пароля']")
    private SelenideElement recoverPassText;

    public void waitForLoadForgotPassPage() {
        recoverPassText.shouldBe(Condition.visible, Duration.ofSeconds(1));
    }

    @Step("Клик по кнопке Войти")
    public void enterButtonClick() {
        enterButton.click();
    }
}
