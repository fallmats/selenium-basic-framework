package se.prolore.selenium.tests;

import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import static org.junit.Assert.*;

/**
 * Created by maer08 on 2017-09-26.
 */

public class IntegrationTest extends BaseTest {

    @Test
    public void proloreUrlTest() throws Exception {
        System.out.println(environment.getProlore());
        driver.get(environment.getProlore());
        screenShot.takeScreenShot("proloreUrlTest");
        assertTrue("the prolore picture was not shown", integrations.isProloreVisible());
    }

    @Test
    public void checkUtbildning() throws Exception {
        driver.get(environment.getProlore());
        screenShot.takeScreenShot("checkUtbildning");
        integrations.goToUtbildning();
        assertEquals("Utbildning".toUpperCase(),integrations.getEntryTitle());

    }



}



