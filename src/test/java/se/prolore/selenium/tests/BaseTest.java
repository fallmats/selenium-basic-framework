package se.prolore.selenium.tests;


import se.prolore.selenium.common.EnvironmentParser;
import se.prolore.selenium.common.ScreenShot;
import se.prolore.selenium.modules.Integrations;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import se.prolore.selenium.common.BrowserSetup;
import se.prolore.selenium.enums.Browser;
import se.prolore.selenium.modules.Prisjakt;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by maer08 on 2017-09-26.
 */

class BaseTest {

    // CORE

    protected WebDriver driver;
    protected String baseUrl;
    public EnvironmentParser environment;
    protected ScreenShot screenShot;

    // MODULES
    Integrations integrations;
    Prisjakt prisjakt;

    // GENERAL

    protected StringBuffer verificationErrors = new StringBuffer();

    private String osName = System.getProperty("os.name").toUpperCase();

    // METHODS

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @Before
    public void startup() throws  Exception{

        Browser browser;
        // Check if we are running locally or remote (Jenkins should run remote)
        String localRun = System.getProperty("local_run");
        if (localRun != null && localRun.contains("false")) {
            browser = Browser.REMOTE_CHROME;
        } else {
            browser = Browser.CHROME;
        }

        // Let's start a new browser
        BrowserSetup browserSetup = new BrowserSetup();
        try {
            driver = browserSetup.startup(browser);
        } catch (Exception e) {
            System.out.println("Failed starting a browser!");
            e.printStackTrace();
        }

        // environment setup
        environment = new EnvironmentParser();
        screenShot = new ScreenShot(driver);

        // general setup
        verificationErrors.setLength(0);

        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        verificationErrors.setLength(0);


        // Module setup
        integrations = new Integrations(driver);
        prisjakt = new Prisjakt(driver);

        //driver.get()
    }




}
