package se.prolore.selenium.tests;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class SlTest extends BaseTest {

    @Test
    public void enterFromStationNameAndCheckExpandedList() throws IOException, InterruptedException {
        driver.get("https://sl.se");

        sl.writeFromStation("Sluss");
        sl.waitUntilListHasExpanded("Slussen (Stockholm)");
        sl.hitEnter();
        sl.waitForStation("Slussen (Stockholm)");

        Thread.sleep(50);

        screenShot.takeScreenShot("enterFromStationNameAndCheckExpandedList");
    }

}
