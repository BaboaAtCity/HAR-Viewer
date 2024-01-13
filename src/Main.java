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
                printEntryDetails(entry, i);
            }
            System.out.println("there are "+ i + " entries");
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void printEntryDetails(JSONObject entry, int entryNumber) {
        JSONObject requestObj = entry.getJSONObject("request");
        JSONObject responseObj = entry.getJSONObject("response");

        Object methodObj = requestObj.get("method");
        Object urlObj = requestObj.get("url");
        Object statusObj = responseObj.get("status");
        Object time = entry.get("startedDateTime");

        System.out.println(methodObj + " " + statusObj);
        System.out.println(urlObj);
        System.out.println(time);
        System.out.println("Entry number: " + entryNumber);
        System.out.println();
    }
}