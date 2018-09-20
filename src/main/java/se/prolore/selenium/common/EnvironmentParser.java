package se.prolore.selenium.common;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;


/**
 * Created by mats on 2017-09-26.
 */
public class EnvironmentParser {

    String environment;
    String prolore;
    String prisjakt;


    public EnvironmentParser() throws Exception {
        // Lets get which environment we run towards
        File file = new File(System.getProperty("user.dir") + File.separator + "src/main/resources/environment.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) obj;

        environment = (String) jsonObject.get("environment");

        // Now lets get the url's for the choosen environment
        file = new File(System.getProperty("user.dir") + File.separator + "src/main/resources/"+ environment + ".json");
        obj = parser.parse(new FileReader(file));
        jsonObject = (JSONObject) obj;

        prolore = (String) jsonObject.get("prolore");
        prisjakt = (String) jsonObject.get("prisjakt");

    }

    public String getProlore() {
        return prolore;
    }

    public String getPrisjakt() {
        return prisjakt;
    }

}
