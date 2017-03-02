package Selenium.FunctionalTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    protected WebDriver driver;
    protected WebDriverWait _wait;

    public PageObject(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this._wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void onClick(WebElement element) {
        _wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
