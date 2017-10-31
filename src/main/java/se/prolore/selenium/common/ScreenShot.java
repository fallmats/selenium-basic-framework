package se.prolore.selenium.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import java.io.File;
import java.io.IOException;

/**
 * Created by mats on 2016-04-25.
 */

public class ScreenShot extends Core {
    private WebDriver driver;

    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }


    public String takeScreenShot(String testName)  throws IOException{

        String fullPath = getScreenshotName(testName);

        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(fullPath));
        return fullPath;
    }


    /** PRIVATE **/

    // Helper method to get filepath for screenshot with png extension.
    private String getScreenshotName(String testName) {
        String file = testName + "_" + getTimestamp() + ".PNG";
        return getFilePath("target/surefire-reports", file);
    }
}
