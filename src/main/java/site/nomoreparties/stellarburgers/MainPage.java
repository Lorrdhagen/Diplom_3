package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

import java.time.Duration;

public class MainPage {

    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = "//span[@class='text text_type_main-default'][text()='Булки']")
    private SelenideElement bunTab;

    @FindBy(how = How.XPATH, using = "//span[@class='text text_type_main-default'][text()='Соусы']")
    private SelenideElement sauceTab;

    @FindBy(how = How.XPATH, using = "//span[@class='text text_type_main-default'][text()='Начинки']")
    private SelenideElement fillingsTab;

    @FindBy(how = How.XPATH, using = "//img[@alt='Краторная булка N-200i']")
    private SelenideElement craterBun;

    @FindBy(how = How.XPATH, using = "//img[@alt='Мясо бессмертных моллюсков Protostomia']")
    private SelenideElement immortalsMollusks;

    @FindBy(how = How.XPATH, using = "//img[@alt='Соус фирменный Space Sauce']")
    private SelenideElement spaceSauce;

    @FindBy(how = How.XPATH, using = "//button[text()='Оформить заказ']")
    private SelenideElement orderButton;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement enterAccountButton;

    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement assembleTheBurgerText;

    @FindBy(how = How.XPATH, using = "//h2[text()='Детали ингредиента']")
    private SelenideElement ingredientDetail;

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
        craterBun.shouldBe(Condition.visible, Duration.ofSeconds(2));
    }

    @Step("Клик по Краторной булке")
    public void craterBunClick() {
        craterBun.click();
        ingredientDetail.shouldBe(Condition.visible, Duration.ofSeconds(2));
    }

    @Step("Клик по вкладке Соусы")
    public void sauceTabClick() {
        sauceTab.click();
        spaceSauce.shouldBe(Condition.visible, Duration.ofSeconds(2));
    }

    @Step("Клик по Космическому соусу")
    public void spaceSauceClick() {
        spaceSauce.click();
        ingredientDetail.shouldBe(Condition.visible, Duration.ofSeconds(2));
    }

    @Step("Клик по вкладке Начинки")
    public void fillingsTabClick() {
        fillingsTab.click();
        immortalsMollusks.shouldBe(Condition.visible, Duration.ofSeconds(2));
    }

    @Step("Клик по Бессмертным моллюскам")
    public void immortalMollusksClick() {
        immortalsMollusks.click();
        ingredientDetail.shouldBe(Condition.visible, Duration.ofSeconds(2));
    }

    @Step("Получение заголовка Детали ингридиента")
    public String getIngredientDetailText() {
        return ingredientDetail.getText();
    }
}
