package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

import java.time.Duration;

public class MainPage {

    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab__1SPyG')]")
    private ElementsCollection constructorTabs;

    @FindBy(how = How.XPATH, using = "//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default'][text()='Булки']")
    private SelenideElement bunTab;

    @FindBy(how = How.XPATH, using = "//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default'][text()='Соусы']")
    private SelenideElement sauceTab;

    @FindBy(how = How.XPATH, using = "//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default'][text()='Начинки']")
    private SelenideElement fillingsTab;

    @FindBy(how = How.XPATH, using = "//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']/p[@class='BurgerIngredient_ingredient__text__yp3dH']")
    private ElementsCollection ingredients;

    @FindBy(how = How.XPATH, using = "//section[contains(@class, 'Modal_modal__P3_V5')]")
    private ElementsCollection modals;

    @FindBy(how = How.XPATH, using = "//div[@class='Modal_modal__contentBox__sCy8X pt-10 pb-15']/p[@class='text text_type_main-medium mb-8']")
    private SelenideElement ingredientDetail;

    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement orderButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement enterAccountButton;

    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement assembleTheBurgerText;

    @FindBy(how = How.XPATH, using = "//button[@class='Modal_modal__close_modified__3V5XS Modal_modal__close__TnseK']")
    private SelenideElement closeButton;

    public void waitForLoadMainPage() {
        assembleTheBurgerText.shouldBe(Condition.visible, Duration.ofSeconds(4));
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void enterAccountButtonClick() {
        enterAccountButton.click();
    }

    @Step("Получение текста кнопки Оформить заказ")
    public String getOrderButtonText() {
        return orderButton.getText();
    }

    @Step("Получение текста заголовка Соберите бургер")
    public String getAssembleTheBurgerText() {
        return assembleTheBurgerText.getText();
    }


    @Step("Клик по вкладке Булки")
    public void bunTabClick() {
        bunTab.click();
    }

    public SelenideElement getBunTab() {
        return constructorTabs.get(0);
    }

    @Step("Клик по вкладке Соусы")
    public void sauceTabClick() {
        sauceTab.click();
    }

    public SelenideElement getSauceTab() {
        return constructorTabs.get(1);
    }

    @Step("Клик по ингредиенту")
    public void ingredientClick(String type) {
        ingredients.filter(Condition.text(type)).first().should(Condition.exist).scrollTo().click();
        ingredientDetail.text().contains(type);
    }

    public SelenideElement getModals() {
        return modals.get(0);
    }

    @Step("Клик по вкладке Начинки")
    public void fillingsTabClick() {
        fillingsTab.click();
    }

    public SelenideElement getFillingTab() {
        return constructorTabs.get(2);
    }
}
