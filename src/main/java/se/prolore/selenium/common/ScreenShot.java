package se.prolore.selenium.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

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

    public String takePartialScreenShot(String name, By locator) throws IOException {
        String fullPath = getScreenshotName(name);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        Point p = we.getLocation();
        driver = new Augmenter().augment(driver);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);



        int width = we.getSize().getWidth();
        int height = we.getSize().getHeight();

        BufferedImage img = ImageIO.read(srcFile);

        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width,
                height);

        ImageIO.write(dest, "png", srcFile);

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
