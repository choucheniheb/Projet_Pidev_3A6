/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class OpenCageGeocoder {

    private static final String API_KEY = "your-api-key-here";

    public static String getCountryName(double latitude, double longitude) throws IOException {
        String url = "https://api.opencagedata.com/geocode/v1/json?q="
                + URLEncoder.encode(latitude + "," + longitude, "UTF-8")
                + "&key=" + API_KEY
                + "&no_annotations=1&language=en";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        JSONArray results = jsonObject.getJSONArray("results");
        if (results.length() > 0) {
            JSONObject result = results.getJSONObject(0);
            JSONArray components = result.getJSONArray("components");
            for (int i = 0; i < components.length(); i++) {
                JSONObject component = components.getJSONObject(i);
                String country = component.optString("country");
                if (!country.isEmpty()) {
                    return country;
                }
            }
        }

        return null;
    }
}