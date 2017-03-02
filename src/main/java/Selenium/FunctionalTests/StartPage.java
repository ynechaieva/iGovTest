package Selenium.FunctionalTests;

import Selenium.FunctionalTests.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StartPage extends PageObject {

    @FindBy(linkText = "_ТестовыйТег")
    private WebElement linkToTagPage;

    public StartPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    public TagPage moveToTagPage(){
        onClick(linkToTagPage);
        return new TagPage(driver, _wait);
    }
}
