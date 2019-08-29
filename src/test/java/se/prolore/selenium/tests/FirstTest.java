package se.prolore.selenium.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

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
