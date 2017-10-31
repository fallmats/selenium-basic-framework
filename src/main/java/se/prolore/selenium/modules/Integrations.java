package se.prolore.selenium.modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by mats on 2017-09-26.
 */
public class Integrations extends BasePage{

    public Integrations(WebDriver d) {
        super(d);
    }

    // locators
    By proloreLogo = By.id("site-logo");
    By utbildningLink = By.linkText("Utbildning");
    By utbildningHeader = By.cssSelector("h1.entry-title");


    public boolean isProloreVisible() {
        return isVisibleElement(proloreLogo,10);
    }

    public void goToUtbildning() { clickElement(utbildningLink); }

    public String getEntryTitle() {
        return getText(utbildningHeader);
    }







}
