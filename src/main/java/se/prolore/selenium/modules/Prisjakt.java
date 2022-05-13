package se.prolore.selenium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by mats on 2018-09-20.
 */
public class Prisjakt extends BasePage {

    public Prisjakt(WebDriver d) {
        super(d);
    }

    By datorerCategory = By.linkText("Datorer & Tillbehör");
    By datorKomponenter = By.linkText("Datorkomponenter");
    By subPageDatorKomponenter = By.xpath("//h1[contains(.,'Datorkomponenter')]");
    public By objektivCategory = By.linkText("Objektiv");
    By pageH1 = By.cssSelector("span h1");
    public By systemkameror = By.cssSelector("li > a[href$='systemkameror']");

    public By fotoVideoH1 = By.xpath("//h1[contains(.,'Foto & Video')]");

    public void expandDatorer() {
        hoverMouseClick(datorerCategory);
    }

    public boolean isDatorKomponenterVisible() {
        return isVisibleElement(datorKomponenter);
    }

    public void clickDatorKomponenter() {
        hoverMouseClick(datorKomponenter);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(subPageDatorKomponenter));
    }

    public String getPageH1() {
        return getText(pageH1);
    }

    public void showCategory(String s) {
        By locator = By.linkText(s);
        clickElement(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fotoVideoH1));
    }

    public void expandCategory(String s) {
        By locator = By.linkText(s);
        hoverMouse(locator);
    }


    public String objektivSubCategory() {
        return findVisibleElement(objektivCategory).getText();

    }
}