package se.prolore.selenium.tests;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import se.prolore.selenium.common.BrowserSetup;
import se.prolore.selenium.common.EnvironmentParser;
import se.prolore.selenium.common.ScreenShot;
import se.prolore.selenium.enums.Browser;
import se.prolore.selenium.modules.Prisjakt;
import se.prolore.selenium.modules.Sl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by maer08 on 2019-08-29.
 */

public class PrisjaktWatcherTest {

    protected WebDriver driver;
    public EnvironmentParser environment;
    protected ScreenShot screenShot;

    Prisjakt prisjakt;

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void starting(Description description) { System.out.println(description.getMethodName() + description.getDisplayName()); }

        @Override
        protected void failed (Throwable e, Description description)  {

            System.out.println("failed: " + description.getMethodName());
                    //e.printStackTrace();

            // The following try catch will fail if 'driver.quit()' is called in @After in BaseTest.
            // It will generate a NoSuchSessionException because failed is running after @After
            try {
                screenShot.takeScreenShot(description.getMethodName());
            } catch (Exception exception) { exception.printStackTrace(); }
        }

        @Override
        protected void finished(Description description) {
            if (driver != null) driver.quit();
        }

        @Override
        protected void succeeded(Description description) {  }

    };

    @Test
    public void hooverCategoryTest() throws IOException {
        driver.get(environment.getPrisjakt());

        prisjakt.expandDatorer();

        assertTrue(prisjakt.isDatorKomponenterVisible());
        prisjakt.clickDatorKomponenter();
        String pageCategory = prisjakt.getPageH1();
        assertEquals("Datorkomponenter",pageCategory);
        assertEquals("This should","Fail");
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
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        // Module setup
        prisjakt = new Prisjakt(driver);

    }

}
