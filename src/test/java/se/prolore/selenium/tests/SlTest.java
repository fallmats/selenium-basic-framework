package se.prolore.selenium.tests;
import org.junit.Test;

import java.io.IOException;

public class SlTest extends BaseTest {

    @Test
    public void enterFromStationNameAndCheckExpandedList() throws IOException, InterruptedException {
        driver.get("https://sl.se");

        sl.writeFromStation("Sluss");
        sl.waitUntilListHasExpanded("Slussen (Stockholm)");
        sl.clickStation("Slussen (Stockholm)");
        sl.waitForStation("Slussen (Stockholm)");

        sl.writeToStation("Hornstull");

        screenShot.takeScreenShot("enterFromStationNameAndCheckExpandedList");
    }

}
