package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

public class AccountProfilePage {

    public static final String ACCOUNT_PROFILE_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    private SelenideElement exitButton = $(By.xpath("//button[text()='Выход']"));
    private SelenideElement profileLink = $(By.xpath("//a[text()='Профиль']"));

    public void waitForLoadAccProfilePage() {
        profileLink.shouldBe(visible);
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
