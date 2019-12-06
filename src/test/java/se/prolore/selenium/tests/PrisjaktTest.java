package se.prolore.selenium.tests;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by maer08 on 2018-09-20.
 */

public class PrisjaktTest extends BaseTest {

    @Test
    public void hooverCategoryTest() throws IOException {
        driver.get(environment.getPrisjakt());

        prisjakt.expandDatorer();

        screenShot.takeScreenShot("hooverCategoryTest");
    }

    @Test
    public void mittTest() throws IOException {
        driver.get(environment.getPrisjakt());
        prisjakt.expandCategory("Spel & Film");
        screenShot.takeScreenShot("mittTest");
    }

}
