package ru.kpfu.itis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DgisApiService {
    private static final String API_KEY = "56efb4a4-eead-44fc-9d2a-2cc5b41942cd";
    private static final String BASE_URL = "https://routing.api.2gis.com/get_transit_directions";

    public static String getRoute(String pointA, String pointB, String transportType) throws IOException {
        String apiUrl = BASE_URL + "?key=" + API_KEY +
                "&points=" + pointA + "~" + pointB +
                "&type=" + transportType;

        return sendGetRequest(apiUrl);
    }

    private static String sendGetRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode != 200) {
            throw new IOException("Ошибка API: " + responseCode);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
}
