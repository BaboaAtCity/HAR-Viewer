import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONTokener;
import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            String filePath = "HAR/www.stackpath.com.har";

            FileReader fileReader = new FileReader(filePath);

            JSONTokener jsonTokener = new JSONTokener(fileReader);

            JSONObject jsonObject = new JSONObject(jsonTokener);

            JSONObject log = jsonObject.getJSONObject("log");
            JSONArray entries = log.getJSONArray("entries");

            int i;
            for (i = 0; i < entries.length(); i++) {

                JSONObject entry = entries.getJSONObject(i);
                JSONObject requestObj = entry.getJSONObject("request"); // contains full details of request (GET OR POST, headers)
                JSONObject responseObj = entry.getJSONObject("response"); // contains full details of response
                //JSONArray headersObj = requestObj.getJSONArray("headers"); // contains all headers of the request

                Object methodObj = requestObj.get("method");
                Object urlObj = requestObj.get("url");
                Object statusObj = responseObj.get("status");
                Object time = entry.get("startedDateTime"); // start time of request

                System.out.println(methodObj+ " " + statusObj);
                System.out.println(urlObj); // prints the full request URL
                System.out.println(time);
                System.out.println("entry number: " + i);
                System.out.println();
            }
            System.out.println("there are "+ i + " entries");
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}