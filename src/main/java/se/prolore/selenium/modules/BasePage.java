package se.prolore.selenium.modules;


import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mats on 2017-09-26.
 */

public class BasePage {
    public Integer timeoutInSeconds;
    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage(WebDriver d) {
        driver = d;
        wait = new WebDriverWait(driver, 15);
        timeoutInSeconds = 15;
    }


    // ------------------------------------------------------------------------------------------------------------- //
    // WAITING
    // ------------------------------------------------------------------------------------------------------------- //

    /**
     * Wait for javascript document load
     *
     * @author mats
     */
    public void waitForLoad() {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Wait for jQuery to finish (also checks if jquery is loaded)
     *
     * @author mats
     */
    public void waitForJQuery() {
        // Only run if JQuery is loaded on the page
        Boolean jQueryIsUndefined = (Boolean) ((JavascriptExecutor)driver).executeScript("return window.jQuery === undefined");
        if (!jQueryIsUndefined) {
            new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return jQuery.active").equals("0"));
        } else {
            System.out.println("-- INFO: jQuery is not loaded");
        }
    }

    /**
     * Wait for Angular to finish (also checks if angular is loaded)
     *
     * @author mats
     */
    public void waitForAngular() {
        // Only run if Angular is loaded on the page
        Boolean angularIsUndefined = (Boolean) ((JavascriptExecutor)driver).executeScript("return window.angular === undefined");
        if (!angularIsUndefined) {
            new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                    (Boolean) ((JavascriptExecutor) wd).executeScript("(angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)"));
        }

    }



    // ------------------------------------------------------------------------------------------------------------- //
    // CLICKING
    // ------------------------------------------------------------------------------------------------------------- //

    /**
     * Finds a visible element and clicks it.
     *
     * @param locator Locator
     *
     * @author mats
     */
    public void clickElement(By locator) {
        findVisibleElement(locator).click();
    }

    /**
     * Finds a visible element and clicks it by using the Action class
     * Is useful when {@link #clickElement(By)} does not work.
     *
     * @param locator Locator
     *
     * @see #clickElement(By)
     *
     * @author mats
     */
    public void actionClick(By locator) {
        WebElement we = findVisibleElement(locator);

        actionClick(we);
    }

    /** See documentation for: {@link #actionClick(By)} */
    public void actionClick(WebElement webElement) {
        Actions action = new Actions(driver);

        action.moveToElement(webElement).click().build().perform();
    }

    /**
     * Hovers an element and then clicks it by using the Action class
     *
     * @param locator Locator
     *
     * @author mats
     * @author mats
     */
    public void hoverMouseClick(By locator) {
        Actions action = new Actions(driver);
        WebElement we = findVisibleElement(locator);
        action.moveToElement(we).click().build().perform();
    }

    /**
     * Finds a visible element and clicks it by using JavaScript
     * Is useful when {@link #actionClick(By)} does not work
     *
     * @param locator Locator
     *
     * @author mats
     * @author mats
     */
    public void javascriptMouseClick(By locator) {
        WebElement webElement = findVisibleElement(locator);
        JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
        javascriptexecutor.executeScript("arguments[0].click();", webElement);
    }

    /** See documentation for: {@link #javascriptMouseClick(By)} */
    public void javascriptMouseClick(WebElement webElement) {
        JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
        javascriptexecutor.executeScript("arguments[0].click();", webElement);
    }

    // ------------------------------------------------------------------------------------------------------------- //
    // TEXT MANIPULATION
    // ------------------------------------------------------------------------------------------------------------- //

    /**
     * Combines {@link #clearTextBox(By)} and {@link #enterText(By, String)} to be able to do both quickly
     *
     * @param locator Locator
     * @param text String to enter as value in the input box
     *
     * @author mats
     */
    public void clearAndEnterText(By locator, String text) {
        clearTextBox(locator);
        clickElement(locator);
        enterText(locator, text);
    }

    /** See documentation for: {@link #clearAndEnterText(By, String)} */
    public void clearAndEnterText(WebElement we, String text) {
        we.clear();
        we.click();
        we.sendKeys(text);
    }

    /**
     * Clears an input text box of any current value
     *
     * @param locator Locator
     *
     * @author mats
     */
    public void clearTextBox(By locator) {
        findVisibleElement(locator).clear();
    }

    /**
     * Enters text into an input box with a text value
     *
     * @param locator Locator
     * @param text String to enter as value in the input box
     *
     * @author mats
     */
    public void enterText(By locator, String text) {
        findVisibleElement(locator).sendKeys(text);
    }

    public void enterText(By locator, Keys key) {
        findVisibleElement(locator).sendKeys(key);
    }

    /**
     * Sends text into an input box by using {@link #javascriptSendText(By, String)}
     * Also sends the ENTER key upon filling text value
     *
     * @param locator Locator
     * @param text String to enter as value in the input box
     *
     * @author mats
     */
    public void javascriptSendTextAndEnter(By locator, String text) {
        WebElement we = findVisibleElement(locator);

        javascriptSendText(we, text);

        we.sendKeys(Keys.RETURN);
    }

    /**
     * Sends text into an input box by using JavaScript
     * Is useful when normal {@link #clearAndEnterText(By, String)} does not work
     *
     * @param we Locator
     * @param text String to enter as value in the input box
     *
     * @author mats
     */
    public void javascriptSendText(WebElement we, String text) {
        JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
        javascriptexecutor.executeScript("arguments[0].value='" + text + "';", we);

        sleepSeconds(2);
    }

    /** See documentation for: {@link #javascriptSendText(WebElement, String)} */
    public void javascriptSendText(By locator, String text) {
        WebElement we = findVisibleElement(locator);

        javascriptSendText(we, text);
    }

    // ------------------------------------------------------------------------------------------------------------- //
    // WINDOW
    // ------------------------------------------------------------------------------------------------------------- //

    public void scrollToTop() {
        JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
        javascriptexecutor.executeScript("window.scrollTo(0, 0);");
    }

    public void scrollDown(int pixels) {
        JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
        String command = "window.scrollBy(0," + pixels + ")";
        javascriptexecutor.executeScript(command);
    }

    /**
     * Uses JavaScript to scroll an element into view
     *
     * @param webElement Web Element
     *
     * @author mats
     */
    public void scrollToElement(WebElement webElement) {
        JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
        javascriptexecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    /** See documentation for: {@link #scrollToElement(WebElement)} */
    public void scrollToElement(By locator) {
        WebElement we = findPresentElement(locator);

        scrollToElement(we);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void goToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void goToLastOpenedWindow() {
        driver.switchTo().window(getLastWindowHandle());
    }

    public void closeWindow() {
        driver.close();
    }

    public void closeLastOpenedTab(String baseTab) {
        goToLastOpenedWindow();
        closeWindow();
        goToWindow(baseTab);
    }

    // ------------------------------------------------------------------------------------------------------------- //
    // GENERAL
    // ------------------------------------------------------------------------------------------------------------- //

    /**
     * Selects one value in options-list based on the text displayed in that option-list
     *
     * @param locator Locator
     * @param optionText Text to select
     *
     * @author mats
     */
    public void selectByText(By locator, String optionText) {
        Select select = new Select(findVisibleElement(locator));
        select.selectByVisibleText(optionText);
    }

    /**
     * Selects one value in options-list based on the value attribute set in HTML
     *
     * @param locator Locator
     * @param optionValue Text to select
     *
     * @author mats
     */
    public void selectByValue(By locator, String optionValue) {
        Select select = new Select(findVisibleElement(locator));
        select.selectByValue(optionValue);
    }

    public void sleepSeconds(int seconds) {
        //System.out.println("Pausing for " + seconds + "seconds.");
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void hoverMouse(By locator) {
        WebElement we = findVisibleElement(locator);

        Locatable hoverItem = (Locatable) we;
        Mouse mouse = ((HasInputDevices) driver).getMouse();
        mouse.mouseMove(hoverItem.getCoordinates());

        //JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
        //javascriptexecutor.executeScript("jQuery(\"arguments[0]\").mouseover();", we);

        /*Actions action = new Actions(driver);
        WebElement we = findVisibleElement(locator);
        action.moveToElement(we).build().perform();
        */
    }

    public void mouseOver(By locator) {
        Actions action = new Actions(driver);
        WebElement we = findVisibleElement(locator);
        action.moveToElement(we).build().perform();

    }



    public void mouseOverOnElementUsingRobot(By locator) {
        try {
            WebElement we = findVisibleElement(locator);
            Locatable hoverItem = (Locatable) we;
            Point coordinates = driver.findElement(locator).getLocation(); // hoverItem.getCoordinates().onScreen();
            Robot robot = new Robot();
            //robot.mouseMove(coordinates.getX(), coordinates.getY() );
            robot.mouseMove(coordinates.getX()+20, coordinates.getY() + 120);

        } catch (AWTException e) {
            System.out.println("Failed to mouseover on the element '" + locator + "'. " + e);
        }
    }

    public void getAbsolutePosition(By locator) {
        WebElement we = findVisibleElement(locator);
        JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
        Object o = javascriptexecutor.executeScript("return arguments[0].getBoundingClientRect()", we);
        System.out.println(o.toString());
    }

    public void jQueryHooverMouse(String csslocator) {
        JavascriptExecutor javascriptexecutor = (JavascriptExecutor)driver;
        javascriptexecutor.executeScript("jQuery(\"arguments[0]\").mouseover();", csslocator);
    }

    /**
     * Changes an element style to include a border with specified colour
     *
     * @param element Web Element
     * @param colour Colour to give border
     */
    public void highlightElement(WebElement element, String colour) {
        String script = "arguments[0].style.border='3px solid " + colour + "'";
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript(script, element);
        }
    }

    /** See documentation for: {@link #highlightElement(WebElement, String)} */
    public void highlightElement(WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
    }

    // ------------------------------------------------------------------------------------------------------------- //
    // RETURN WEB ELEMENTS
    // ------------------------------------------------------------------------------------------------------------- //

    /**
     * Finds one element that is present on the page
     * Will fail if there are more than one element with the same locator
     *
     * @param locator Locator
     * @param timeout Timeout in Seconds
     *
     * @return Web Element
     *
     * @author mats
     */
    public WebElement findPresentElement(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /** See documentation for: {@link #findPresentElement(By, int)} */
    public WebElement findPresentElement(By locator) {
        return findPresentElement(locator, timeoutInSeconds);
    }

    /**
     * Finds one element that is present on the page and also visible
     * Will fail if there are more than one element with the same locator
     *
     * @param locator Locator
     * @param timeout Timeout in Seconds
     *
     * @return Web Element
     *
     * @author mats
     */
    public WebElement findVisibleElement(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** See documentation for: {@link #findVisibleElement(By, int)} */
    public WebElement findVisibleElement(By locator) {
        return findVisibleElement(locator, timeoutInSeconds);
    }

    /**
     * Finds one element that has a specific attribute with a specific value
     *
     * @param locator Locator
     * @param attribute Attribute that holds value
     * @param attributeText The value that the attribute needs to have
     *
     * @return Web Element
     *
     * @author mats
     */
    public WebElement findVisibleElementByAttribute(By locator, String attribute, String attributeText) {
        // Set Up the return Web Element to be nothing
        WebElement returnElement = null;

        // Build a list of all web elements that has the same locator
        List<WebElement> weList = findAllDisplayedElements(locator, 60);

        // Check all elements in the list
        for(WebElement we : weList) {

            // If the element has the attribute we wish, and the value we want we can return that element
            if(we.getAttribute(attribute).equals(attributeText)) {
                returnElement = we;
                break;
            }
        }

        return returnElement;
    }

    public WebElement getParent(WebElement we) {
        WebElement parent = (WebElement) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].parentNode;", we);
        return parent;
    }

    // ------------------------------------------------------------------------------------------------------------- //
    // RETURN LISTS
    // ------------------------------------------------------------------------------------------------------------- //

    /**
     * Returns a list of all elements that are present on the page
     *
     * @param locator Locator
     *
     * @return List of Web Elements
     *
     * @author mats
     */
    public List<WebElement> findAllPresentElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * Returns a list of elements that are present and visible on the page
     * Fails if there are present elements that are not visible
     *
     * @param locator Locator
     *
     * @return List of Web Elements
     *
     * @author mats
     */
    public List<WebElement> findAllVisibleElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Returns a list of elements that are present and visible on the page
     * Does not fail if there are present elements that are not visible
     *
     * @param locator Locator
     *
     * @return List of Web Elements
     *
     * @author mats
     */
    public List<WebElement> findAllDisplayedElements(By locator, int timeoutInSeconds) {
        List<WebElement> weList = findAllPresentElements(locator);
        List<WebElement> visibleList = new ArrayList<>();

        for (WebElement item : weList) {
            if (item.isDisplayed()) {
                visibleList.add(item);
            }
        }

        return visibleList;
    }

    // ------------------------------------------------------------------------------------------------------------- //
    // RETURN BOOLEANS
    // ------------------------------------------------------------------------------------------------------------- //

    /**
     * Verifies if the element is visible or not
     *
     * @param locator Locator
     * @param timeout Timeout in Seconds
     *
     * @return boolean value of element visibility
     *
     * @author mats
     */
    public boolean isVisibleElement(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /** See documentation for: {@link #isVisibleElement(By, int)} */
    public boolean isVisibleElement(By locator) {
        return isVisibleElement(locator, timeoutInSeconds);
    }

    /**
     * Verifies if an element that has a specific attribute with a specific value is visible
     *
     * @param locator Starting locator from which we will build a list
     * @param attribute Attribute that holds value
     * @param attributeText The value that the attribute needs to have
     *
     * @return boolean value of element visibility
     *
     * @author mats
     */
    public boolean isVisibleElementByAttribute(By locator, String attribute, String attributeText) {
        // Set up the return to false
        boolean isVisible = false;

        // Build a list
        List<WebElement> weList = findAllDisplayedElements(locator, 60);

        // Check all elements in the list
        for(WebElement we : weList) {

            // If the element has the attribute we wish, and the value we want we can set return to true
            if(we.getAttribute(attribute).equals(attributeText)) {
                isVisible = true;
                break;
            }
        }

        return isVisible;
    }

    // ------------------------------------------------------------------------------------------------------------- //
    // RETURN STRINGS
    // ------------------------------------------------------------------------------------------------------------- //

    /**
     * Returns a string with the unique id of the current window that is opened
     *
     * @return String with window handle ID
     *
     * @author mats
     */
    public String getLastWindowHandle() {
        String currentWindowHandle = "";

        for(String winHandle : driver.getWindowHandles()) {
            currentWindowHandle = winHandle;
        }

        return currentWindowHandle;
    }

    public String getText(By locator) {
        return findVisibleElement(locator).getText();
    }

    public String getSelectText(By locator ) {
        Select select = new Select(findVisibleElement(locator));
        return select.getFirstSelectedOption().getText();
    }

    public String getAttributeValue(By locator) {
        return findVisibleElement(locator).getAttribute("value");
    }

    public String getAttributeValue(By locator, int timeoutInSeconds) {
        return findVisibleElement(locator, timeoutInSeconds).getAttribute("value");
    }
}

