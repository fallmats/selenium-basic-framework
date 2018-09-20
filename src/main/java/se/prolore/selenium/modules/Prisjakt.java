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

    public void expandDatorer () {
        hoverMouse(datorerCategory);
    }



}
