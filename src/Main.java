import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.swing.*;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("HAR file viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(900,400);
        frame.setVisible(true);

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

        String emoji;
        if (statusObj.toString().startsWith("2")) {
            emoji = "\uD83D\uDFE2";
        } else if (statusObj.toString().startsWith("4") || statusObj.toString().startsWith("5")) {
            emoji = "\uD83D\uDD34";
        } else {
            emoji = "\uD83D\uDFE1";
        }

        System.out.println(methodObj + " " + emoji + " " + statusObj);
        System.out.println(urlObj);
        System.out.println(time);
        System.out.println("Entry number: " + entryNumber);
        System.out.println();
    }

}