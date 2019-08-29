package se.prolore.selenium.tests;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by maer08 on 2019-08-29.
 */

public class PrisjaktWatcherTest extends BaseTest {

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

}
