package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

public class Header {

    @FindBy(how = How.XPATH, using = "//p[@Class='AppHeader_header__linkText__3q_va ml-2'][text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private SelenideElement stellarBurgersLogo;

    @FindBy(how = How.XPATH, using = "//p[@Class='AppHeader_header__linkText__3q_va ml-2'][text()='Личный Кабинет']")
    private SelenideElement personalAccountButton;

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
