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

import java.time.Duration;
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
        if (isNix()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        //options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @Test
    public void aFirstTest() {
        driver.get("https://www.sl.se/");
    }

    @Test
    public void slTest() {
        driver.get("https://sl.se/");
        driver.manage().window().setSize(new Dimension(946, 680));

        driver.findElement(By.cssSelector("input#id-0")).click();
        driver.findElement(By.cssSelector("input#id-0")).sendKeys("med");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#id-0-result-0")));

        driver.findElement(By.id("id-0-result-0")).click();

        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#id-0"), "value", "Medborgarplatsen (Stockholm)"));

        String station = driver.findElement(By.id("id-0")).getAttribute("value");
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
