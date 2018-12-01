package se.prolore.selenium.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ExerciseTest {

    private String osName = System.getProperty("os.name").toUpperCase();
    WebDriver driver;

    @Before
    public void setup() {
        if (isMac()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver-osx");
        if (isWindows()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver-win.exe");
        if (isNix()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver-nix");


        DesiredCapabilities capa = new DesiredCapabilities();
        capa.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capa.setCapability("nativeEvents", true);

        driver = new ChromeDriver(capa);
    }

    @Test
    public void aFirstTest() {
        driver.get("https://www.phptravels.net/");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertTrue("Date picker is not shown", driver.findElement(By.className("datepicker-days")).isDisplayed());
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
