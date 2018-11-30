package pages;

import annotation.FieldName;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.DriverManager;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


public class MortgageCalcPage extends BasePageObject {

    @FindBy(xpath = "//h1")
    @FieldName(name = "Ипотечный калькулятор")
    public WebElement mortCalculator;

    @FindBy(xpath = "//*[@id = 'form_city-button']/..")
    @FieldName(name = "Город")
    public WebElement city;

    @FindBy(xpath = "//*[@id = 'form_program-button']/..")
    @FieldName(name = "Вид ипотечной программы")
    public WebElement mortProgram;

    @FindBy(xpath = "//*[@id = 'form_category-button']/..")
    @FieldName(name = "Я являюсь")
    public WebElement clientCategory;

    @FindBy(xpath = "//*[@id = 'form_documents-button']/..")
    @FieldName(name = "Уровень доходов подтверждаю")
    public WebElement documents;

    @FindBy(xpath = "//div[@class = 'checkbox-block']//span[@class = 'checkbox-block__span']")
    @FieldName(name = "Знаю свою ипотечную программу")
    public WebElement isProgram;

    @FindBy(xpath = "//*[@id = 'form_credit_amount']")
    @FieldName(name = "В банке возьму")
    public WebElement creditAmount;

    @FindBy(xpath = "//*[@id = 'form_initial']")
    @FieldName(name = "Первоначальный взнос")
    public WebElement initialAmount;

    @FindBy(xpath = "//*[@id = 'form_period']")
    @FieldName(name = "Срок кредита")
    public WebElement period;

    @FindBy(xpath = "//*[@value = 'Рассчитать']")
    @FieldName(name = "Рассчитать")
    public WebElement calculate;

    @FindBy(xpath = "//*[@class = 'monthly-payment']")
    @FieldName(name = "Ежемесячный платеж")
    public WebElement monthlyPayment;

    @FindBy(xpath = "//*[contains (@class, 'total-payment')]")
    @FieldName(name = "Общая сумма выплат")
    public WebElement totalPayment;

    @FindBy(xpath = "//*[contains (@class, 'percent-sum')]")
    @FieldName(name = "Cумма выплат по процентам")
    public WebElement percentSum;

    @FindBy(xpath = "//*[contains (@class, 'interest-rate')]")
    @FieldName(name = "Процентная ставка")
    public WebElement interestRate;

    public void fillField(String name, String value) throws Exception {
        WebElement element = getField(name);
        fillField(element, value);
    }

     public void click(String name) throws Exception {
        WebElement element = getField(name);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void selectInList(String value) {
        click(wait.until(ExpectedConditions.elementToBeClickable(DriverManager.getDriver().findElement(By.xpath("//ul/li/div[text()='" + value + "']")))));
    }

    public void selectInput(String field, String value) throws Exception{
        //TODO
        Thread.sleep(2000);
        click(getField(field));
        selectInList(value);
    }

    public WebElement getField(String name) throws Exception {
        Class example = Class.forName("pages.MortgageCalcPage");
        List<Field> fields = Arrays.asList(example.getFields());
        for (Field field : fields) {
            if (field.getAnnotation(FieldName.class).name().equals(name)) {
                return wait.until(ExpectedConditions.visibilityOf(
                        DriverManager.getDriver().findElement(By.xpath(field.getAnnotation(FindBy.class).xpath()))));
            }
        }
        Assert.fail("Не объявлен элемент с наименованием " + name);
        return null;
    }
}
