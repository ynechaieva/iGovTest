package Selenium.FunctionalTests;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

public class RequestPageTest extends FunctionalTest {

    @Test
    public void CreateRequest() throws ParseException, IOException, JSONException, InterruptedException {
        RequestPage reqp = new RequestPage(driver, _wait);
        TestData data = TestDataReader.Read(fileInit());
        String region = "Дніпропетровська";
        String city = "Дніпро (Дніпропетровськ)";
        driver.manage().deleteAllCookies();
        driver.get("https://test-version.igov.org.ua/");
        StartPage objStartPage = new StartPage(driver, _wait);
        TagPage objTagPage = objStartPage.moveToTagPage();
        RequestPage objRequestPage = objTagPage.moveToRequestPage();
        Assert.assertEquals(true, objRequestPage.isOpened());

        reqp.selectRegion(region);
        reqp.selectCity(city);
        reqp.waitForElement(reqp.getBankIDbutton());
        Assert.assertEquals(reqp.getBankIDbutton().getAttribute("class"),"icon-bank-id");

        reqp.PrivatBankIdentification(data);
        reqp.waitForElement(reqp.getForm());
        Assert.assertEquals(reqp.getForm().getText(),"Крок 3.");

        if(reqp.isElementPresent(By.linkText("Відмінити авто-заповнені дані"))){
            reqp.submitFilledForm();
        }
        else{
            reqp.fillForm(data);
            reqp.submitFilledForm();
        }
        reqp.waitForElement(reqp.getOrder());
        Assert.assertTrue(reqp.getOrder().isDisplayed());
    }
}
