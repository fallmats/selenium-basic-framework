package se.prolore.selenium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Sl extends BasePage{

    public Sl(WebDriver d) {
        super(d);
    }

    By fromInput = By.id("travelplanner_from");

    public void writeFromStation(String station) {
        clickElement(fromInput);
        enterText(fromInput, station);
    }

    public void waitUntilListHasExpanded(String station) {
        By expandedFromList = By.xpath("//span[contains(.,'"+ station +"')]");
        findVisibleElement(expandedFromList);
    }

    public void hitEnter() {
        enterText(fromInput, Keys.RETURN);
    }

    public void waitForStation(String station) {
        findVisibleElementByAttribute(fromInput, "value", station);
    }
}
