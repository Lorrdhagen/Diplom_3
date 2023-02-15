package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class Header {

    private SelenideElement constructorButton = $(By.xpath("//p[@Class='AppHeader_header__linkText__3q_va ml-2'][text()='Конструктор']"));
    private SelenideElement stellarBurgersLogo = $(By.className("AppHeader_header__logo__2D0X2"));
    private SelenideElement personalAccountButton = $(By.xpath("//p[@Class='AppHeader_header__linkText__3q_va ml-2'][text()='Личный Кабинет']"));

    @Step("Клик по кнопке Конструктор")
    public void constructorButtonClick() {
        constructorButton.click();
    }

    @Step("Клик по логотипу Stellar Burgers")
    public void stellarBurgerLogoClick() {
        stellarBurgersLogo.click();
    }

    @Step("Клик по кнопке Личный Кабинет")
    public void personalAccButtonClick() {
        personalAccountButton.click();
    }
}
