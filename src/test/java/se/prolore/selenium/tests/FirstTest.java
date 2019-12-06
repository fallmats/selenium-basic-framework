package se.prolore.selenium.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FirstTest {

    private String osName = System.getProperty("os.name").toUpperCase();
    WebDriver driver;

    @Before
    public void setup() {
        if (isMac()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
        if (isWindows()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
        if (isNix()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver-nix");

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        //options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @Test
    public void aFirstTest() {
        driver.get("https://www.phptravels.net/");
    }

    @Test
    public void slTest() {
        driver.get("https://sl.se/");
        driver.manage().window().setSize(new Dimension(946, 680));

        driver.findElement(By.cssSelector("#Travelplanner[data-ng-show=\'stateService.getShowSearchTo() && stateService.getShowSearchExternal() == false\'] .input-field-wrapper #travelplanner_from")).click();
        driver.findElement(By.id("travelplanner_from")).sendKeys("med");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Medborgarplatsen (Stockholm)')]")));
        // Thread.sleep(1000);
        driver.findElement(By.id("travelplanner_from")).sendKeys(Keys.RETURN);

        wait.until(ExpectedConditions.attributeContains(By.cssSelector(".active .two-col-rel-left"), "value", "Medborgarplatsen (Stockholm)"));
        //Thread.sleep(1000);

        String station = driver.findElement(By.id("travelplanner_from")).getAttribute("value");
        assertEquals("Texten på Från fältet stämde inte med förväntat värde", "Medborgarplatsen (Stockholm)", station);
        System.out.println(station);
    }

    @After
    public void cleanup() {
        driver.quit();
    }


    private boolean isMac() { return osName.contains("MAC"); }
    private boolean isWindows() {
        return osName.contains("WIN");
    }
    private boolean isNix() {
        return osName.contains("NIX") || osName.contains("NUX") || osName.contains("AIX");
    }

}
