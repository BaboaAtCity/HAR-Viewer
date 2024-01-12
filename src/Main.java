import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONTokener;
import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        /*JFrame f = new JFrame();
        JButton b=new JButton("click");
        b.setBounds(70,50,50, 50);
        f.add(b);

        f.setSize(200,200);
        f.setLayout(null);
        f.setVisible(true);*/
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
                JSONArray headersObj = requestObj.getJSONArray("headers"); // contains all headers of the request
                JSONObject domainObj = headersObj.getJSONObject(0); // contains the authority domain obj in name value format
                JSONObject pathObj = headersObj.getJSONObject(2); // contains the path obj in name value format
                Object path = pathObj.get("value");  // path part of the request url
                Object domain = domainObj.get("value"); // domain part of the request url
                System.out.println(domain.toString() + path.toString()); // prints the full request URL
                Object time = entry.get("startedDateTime"); // start time of request
                System.out.println(time);
                System.out.println();
                //count++;
            }
            System.out.println("there are "+ i + " entries");
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}