package se.prolore.selenium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Sl extends BasePage{

    public Sl(WebDriver d) {
        super(d);
    }

    By fromInput = By.id("id-0");
    By toInput = By.id("id-1");

    public void writeFromStation(String station) {
        clickElement(fromInput);
        enterText(fromInput, station);
    }

    public void waitUntilListHasExpanded(String station) {
        By expandedFromList = By.xpath("//li[contains(.,'"+ station +"')]");
        findVisibleElement(expandedFromList);
    }

    public void clickStation(String station) {
        By expandedFromList = By.xpath("//li[contains(.,'"+ station +"')]");
        findVisibleElement(expandedFromList).click();
    }

    public void hitEnter() {
        enterText(fromInput, Keys.RETURN);
    }

    public void waitForStation(String station) {
        findVisibleElementByAttribute(fromInput, "value", station);
    }

    public void writeToStation(String station) {
        clickElement(toInput);
        enterText(toInput, station);
    }
}
