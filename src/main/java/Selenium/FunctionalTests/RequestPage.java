package Selenium.FunctionalTests;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class RequestPage extends PageObject {

    @FindBy(linkText = "_test_Звернення громадян")
    private WebElement textOnPage;

    @FindBy(id = "region")
    private WebElement region;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(className = "icon-bank-id")
    private WebElement bankIDbutton;

    @FindBy(xpath = "//span[text()='ПриватБанк']")
    private WebElement privatBank;

    @FindBy(name = "phone")
    private WebElement loginPrivatBank;

    @FindBy(id = "passwordLikePassword")
    private WebElement passwordPrivatBank;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(xpath = "//div[text()='Увійти']")
    private WebElement submittPrivatBankData;

    @FindBy(xpath = "//div[text()='Крок 3. ']")
    private WebElement form;

    @FindBy(name = "sReason")
    private WebElement sReason;

    @FindBy(xpath = "//button[@class='btn btn-info'][text()='Замовити послугу']")
    private WebElement requestSubmit;

    @FindBy(xpath = "//div[contains(@class,'text-center')]/a")
    private WebElement order;

    public RequestPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }
    public WebElement getRegion() {return this.region;}
    public WebElement getCity() {return this.city;}
    public WebElement getBankIDbutton() {return this.bankIDbutton;}
    public WebElement getForm() {return this.form;}
    public WebElement getOrder(){return this.order;}

    public void waitForElement(WebElement elem){
        int retries = 0;
        final int MAX_STALE_ELEMENT_RETRIES = 5;
        while(true){
            try{
                _wait.until(ExpectedConditions.elementToBeClickable(elem));
                return;
            }
            catch(StaleElementReferenceException e){
                if(retries < MAX_STALE_ELEMENT_RETRIES){
                    retries += 1;
                    continue;
                }
                else {
                    throw e;
                }
            }
        }
    }

    public boolean isElementPresent(By selector) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        boolean returnVal = true;
        try{
            driver.findElement(selector);
        } catch (NoSuchElementException e){
            returnVal = false;
        }
        return returnVal;
    }

    public void fillForm(TestData data){
        email.clear();
        email.sendKeys(data.getEmail());
        new Select(driver.findElement(By.name("asDistrict"))).selectByIndex(2);
        new Select(driver.findElement(By.name("asHowCategory"))).selectByIndex(1);
        new Select(driver.findElement(By.name("asKindTr"))).selectByIndex(1);
        new Select(driver.findElement(By.name("asHowSocStatus"))).selectByIndex(1);
        new Select(driver.findElement(By.name("asHowasTopHeadSocStatus"))).selectByIndex(1);
        sReason.clear();
        sReason.sendKeys("test request");
        new Select(driver.findElement(By.name("asAnswerChannel"))).selectByIndex(1);
    }

    public void submitFilledForm(){
        waitForElement(requestSubmit);
        requestSubmit.click();
    }

    public void PrivatBankIdentification(TestData data) throws ParseException, IOException, JSONException {
        WebElement ulB = bankIDbutton.findElement(By.xpath("../..")).findElement(By.tagName("ul"));
        bankIDbutton.click();
        ulB.findElement(By.tagName("li")).findElement(By.tagName("a")).click();
        privatBank.click();
        loginPrivatBank.clear();
        loginPrivatBank.sendKeys(data.getLogin());
        passwordPrivatBank.clear();
        passwordPrivatBank.sendKeys(data.getPassword());
        submittPrivatBankData.click();
        WebElement SMSscreen = driver.findElement(By.id("pasOTP"));
        waitForElement(SMSscreen);
        driver.findElement(By.id("first-section")).sendKeys(data.getSMSCOde().substring(0,2));
        driver.findElement(By.id("second-section")).sendKeys(data.getSMSCOde().substring(2,4));
        driver.findElement(By.id("third-section")).sendKeys(data.getSMSCOde().substring(4));
        driver.findElement(By.id("confirmButton")).click();
    }

    public void selectRegion(String s){
        waitForElement(region);
        WebElement ulR = region.findElement(By.xpath("..")).findElement(By.tagName("ul"));
        region.click();
        List<WebElement> rList = ulR.findElements(By.tagName("li"));
        for(WebElement li : rList) {
            String text = li.findElement(By.tagName("a")).getText();
            if(text.equals(s)) {
                li.findElement(By.tagName("a")).click();
                break;
            }
        }
    }

    public void selectCity(String s){
        waitForElement(city);
        WebElement ulC = city.findElement(By.xpath("..")).findElement(By.tagName("ul"));
        city.click();
        List<WebElement> cList = ulC.findElements(By.tagName("li"));
        for(WebElement li : cList) {
            String text = li.findElement(By.tagName("a")).getText();
            if(text.equals(s)) {
                li.findElement(By.tagName("a")).click();
                break;
            }
        }
    }

    public boolean isOpened(){
        if(textOnPage.isDisplayed())
            return true;
        else return false;
    }
}
