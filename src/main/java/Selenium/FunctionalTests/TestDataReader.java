package Selenium.FunctionalTests;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestDataReader {

    public static TestData Read(File file) throws JSONException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(file));
        JSONObject jsonObj = (JSONObject) obj;
        String login = (String) jsonObj.get("login");
        String password = (String) jsonObj.get("password");
        String SMSCode = (String) jsonObj.get("smsCode");
        String email = (String) jsonObj.get("email");
        return TestData.newBuilder().setLogin(login).setPassword(password).setSMSCode(SMSCode).setEmail(email).build();
    }

}
