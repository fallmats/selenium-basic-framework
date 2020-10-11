package se.prolore.selenium.common;


import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import se.prolore.exceptions.NoBrowserException;
import se.prolore.selenium.enums.Browser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mats on 2017-09-06.
 */
public class BrowserSetup {

    private String osName = System.getProperty("os.name").toUpperCase();

    public WebDriver startup(Browser browser) throws MalformedURLException, NoBrowserException {
        WebDriver driver;
        DesiredCapabilities capa = new DesiredCapabilities();
        capa.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capa.setCapability("nativeEvents", true);

        if (browser == Browser.FIREFOX) {
            throw new NoBrowserException("Firefox not usable at the moment, check availability on seleniumhq.org");
        } else if (browser == Browser.SAFARI) {
            throw new NoBrowserException("Safari not implemented yet");
        } else if (browser == Browser.IEXPLORER) {
            throw new NoBrowserException("Internet explorer not implemented yet");
        } else if (browser == Browser.REMOTE_CHROME) {


            //Use this settings if remote server needs proxy settings
            String PROXY = "http://yourproxy.yourdomain:8080";
            Proxy proxy = new Proxy();
            proxy.setHttpProxy(PROXY)
                    .setFtpProxy(PROXY)
                    .setSslProxy(PROXY);

            capa = DesiredCapabilities.chrome();
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");

            Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            capa.setCapability(ChromeOptions.CAPABILITY, options);
//            capa.setCapability(CapabilityType.PROXY, proxy);

            driver = remoteDriver(capa);
        } else {  //Default browser CHROME on local machine


            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless");

            //Check local machine OS
            if (isMac()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver");
            if (isWindows()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver.exe");
            if (isNix()) System.setProperty("webdriver.chrome.driver", "./lib/chromedriver-nix");

            Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
        }

        return driver;

    }

    private WebDriver remoteDriver(DesiredCapabilities capability) throws MalformedURLException {
        System.out.println("Making remote driver");

        // Exchange the remote url for your selenium Hub (now it require you to run selenium grid on local machine)
        String remote = System.getProperty("selenium.server.url", "http://127.0.0.1:4444/wd/hub");
        WebDriver driver = new RemoteWebDriver(new URL(remote), capability);
        return driver;
    }

    private boolean isMac() {
        return osName.contains("MAC");
    }

    private boolean isWindows() {
        return osName.contains("WIN");
    }

    private boolean isNix() {
        return osName.contains("NIX") || osName.contains("NUX") || osName.contains("AIX");
    }

}
