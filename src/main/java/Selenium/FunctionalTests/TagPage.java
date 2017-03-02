package Selenium.FunctionalTests;

import Selenium.FunctionalTests.PageObject;
import Selenium.FunctionalTests.RequestPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TagPage extends PageObject {

    @FindBy (linkText = "_test_Звернення громадян")
    private WebElement linkToRequestPage;

    public TagPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    public RequestPage moveToRequestPage(){
        onClick(linkToRequestPage);
        return new RequestPage(driver, _wait);
    }
}
