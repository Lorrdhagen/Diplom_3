package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

import java.time.Duration;

public class AccountProfilePage {

    public static final String ACCOUNT_PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    @FindBy(how = How.XPATH, using = "//button[text()='Выход']")
    private SelenideElement exitButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement profileLink;

    public void waitForLoadAccProfilePage() {
        profileLink.shouldBe(Condition.visible, Duration.ofSeconds(1));
    }

    @Step("Получение текста вкладки Профиль")
    public String getUserProfileText() {
        return profileLink.getText();
    }

    @Step("Клик по кнопке Выход")
    public void exitButtonClick() {
        exitButton.click();
    }
}
