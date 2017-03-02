package Selenium.FunctionalTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;


public class FunctionalTest {
    protected static WebDriver driver;
    protected WebDriverWait _wait;

    @BeforeClass
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\Install\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        _wait = new WebDriverWait(driver, 30);
    }

    @BeforeTest
    public File fileInit(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("file/data.json").getFile());
        return file;
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.close();
    }

}