package se.prolore.selenium.tests;
import org.junit.Test;
import se.prolore.selenium.common.ImgCompare;

import java.io.IOException;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
    public void mouseOverShouldShowFrame() throws IOException, InterruptedException {
        driver.get(environment.getPrisjakt());
        //prisjakt.expandCategory("Foto & Video");
        prisjakt.showCategory("Foto & Video");
        String path1 = screenShot.takePartialScreenShot("shouldShowFotoVideo", prisjakt.systemkameror);
        //assertEquals("Objektiv", prisjakt.objektivSubCategory());
        prisjakt.hoverMouse(prisjakt.systemkameror);
        Thread.sleep(200);
        String path2 = screenShot.takePartialScreenShot("shouldShowFotoVideo", prisjakt.systemkameror);
        ImgCompare imgCompare = new ImgCompare();
        Double diffPercent= imgCompare.compare(path1,path2);

        assertThat("Pictures did not differ which means mouse over didnt show frame", diffPercent, is(greaterThan((double) 0)));

        diffPercent= imgCompare.compare(path1,path1);
        assertThat("This should fail since we compare the same picture", diffPercent, is(greaterThan((double) 0)));



    }

    @Test
    public void shouldDisplayCategoryAfterHooverClick() throws IOException {
        driver.get(environment.getPrisjakt());

        prisjakt.expandDatorer();
        prisjakt.clickDatorKomponenter();
        screenShot.takeScreenShot("shouldDisplayCategoryAfterHooverClick");

        assertEquals("Wrong subcategory page is shown","Datorkomponenter",prisjakt.getPageH1());
    }


}
