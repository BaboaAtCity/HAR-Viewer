import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Specify the path to your HAR file
            String filePath = "HAR/www.stackpath.com.har";

            // Create a FileReader object to read the file
            FileReader fileReader = new FileReader(filePath);

            // Create a JSONTokener to parse the file
            JSONTokener jsonTokener = new JSONTokener(fileReader);

            // Create a JSONObject from the JSONTokener
            JSONObject jsonObject = new JSONObject(jsonTokener);

            // Navigate the JSON structure
            JSONObject log = jsonObject.getJSONObject("log");
            JSONArray entries = log.getJSONArray("entries");

            // Process the entries as needed
            int count = 0;
            for (int i = 0; i < entries.length(); i++) {
                JSONObject entry = entries.getJSONObject(i);
                JSONObject x = entry.getJSONObject("request");
                JSONArray y = x.getJSONArray("headers");
                JSONObject z = y.getJSONObject(0);
                JSONObject a = y.getJSONObject(2);
                Object path = a.get("value");
                Object domain = z.get("value");
                System.out.println(domain.toString() + path.toString());
                Object time = entry.get("startedDateTime");
                System.out.println(time);
                System.out.println(" ");

                count++;
            }
            System.out.println("there are "+ count + " entries");
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}