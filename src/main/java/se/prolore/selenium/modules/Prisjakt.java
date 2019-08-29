package se.prolore.selenium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mats on 2018-09-20.
 */
public class Prisjakt extends BasePage{

    public Prisjakt(WebDriver d) {
        super(d);
    }

    By datorerCategory = By.linkText("Datorer & Tillbehör");
    By datorKomponenter = By.linkText("Datorkomponenter");
    By pageH1 = By.cssSelector("span h1");

    public void expandDatorer () {
        hoverMouseClick(datorerCategory);
    }

    public boolean isDatorKomponenterVisible() {
        return isVisibleElement(datorKomponenter);
    }

    public void clickDatorKomponenter() {
        hoverMouseClick(datorKomponenter);
    }

    public String getPageH1() {
        return getText(pageH1);
    }


}
