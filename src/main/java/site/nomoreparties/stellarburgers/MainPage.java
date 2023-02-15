package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.exist;

public class MainPage {

    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    private ElementsCollection constructorTabs = $$(By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]"));
    private SelenideElement bunTab = $(By.xpath("//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default'][text()='Булки']"));
    private SelenideElement sauceTab = $(By.xpath("//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default'][text()='Соусы']"));
    private SelenideElement fillingsTab = $(By.xpath("//div[@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default'][text()='Начинки']"));
    private ElementsCollection ingredients = $$(By.xpath("//a[@class='BurgerIngredient_ingredient__1TVf6 ml-4 mr-4 mb-8']/p[@class='BurgerIngredient_ingredient__text__yp3dH']"));
    private ElementsCollection modals = $$(By.xpath("//section[contains(@class, 'Modal_modal__P3_V5')]"));
    private SelenideElement ingredientDetail = $(By.xpath("//div[@class='Modal_modal__contentBox__sCy8X pt-10 pb-15']/p[@class='text text_type_main-medium mb-8']"));
    private SelenideElement orderButton = $(By.xpath("//button[text()='Оформить заказ']"));
    private SelenideElement enterAccountButton = $(By.xpath("//button[text()='Войти в аккаунт']"));
    private SelenideElement assembleTheBurgerText = $(By.xpath("//h1[text()='Соберите бургер']"));
    private SelenideElement closeButton = $(By.xpath("//button[@class='Modal_modal__close_modified__3V5XS Modal_modal__close__TnseK']"));

    @Step("Открыть главную страницу")
    public void open() {
        Selenide.open(MAIN_PAGE_URL);
    }

    public void waitForLoadMainPage() {
        assembleTheBurgerText.shouldBe(visible);
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

    @Step("Проверка наличия ожидаемого класса у вкладки Булки")
    public boolean checkBunTabContainsClass(String className) {
        return getBunTab().getAttribute("class").contains(className);
    }

    @Step("Клик по вкладке Соусы")
    public void sauceTabClick() {
        sauceTab.click();
    }

    public SelenideElement getSauceTab() {
        return constructorTabs.get(1);
    }

    @Step("Проверка наличия ожидаемого класса у вкладки Соусы")
    public boolean checkSauceTabContainsClass(String className) {
        return getSauceTab().getAttribute("class").contains(className);
    }

    @Step("Клик по ингредиенту")
    public void ingredientClick(String type) {
        ingredients.filter(text(type)).first().should(exist).scrollTo().click();
        ingredientDetail.text().contains(type);
    }

    public SelenideElement getModals() {
        return modals.get(0);
    }

    @Step("Проверка наличия ожидаемого класса у модального окна Детали ингредиента")
    public boolean checkModalContainsClass(String className) {
        return getModals().getAttribute("class").contains(className);
    }

    @Step("Клик по вкладке Начинки")
    public void fillingsTabClick() {
        fillingsTab.click();
    }

    public SelenideElement getFillingTab() {
        return constructorTabs.get(2);
    }

    @Step("Проверка наличия ожидаемого класса у вкладки Начинки")
    public boolean checkFillingTabContainsClass(String className) {
        return getFillingTab().getAttribute("class").contains(className);
    }
}
