package clients;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestCient {
    public static void main(String[] args) {
        String url1 = "http://localhost:8080/auth/login?username=user1&password=user1";
        String url2 = "http://localhost:8080/itinerary/search?token=a4670a32-d837-44f7-8073-2370da53be8a&source=A&destination=E";
        String url3 = "http://localhost:8080/itinerary/getitinerary?token=a4670a32-d837-44f7-8073-2370da53be8a&id=c8b8c19f-89e4-4b95-a32f-1938532d1ae7";
        String url4 = "http://localhost:8080/book/ticket?token=a4670a32-d837-44f7-8073-2370da53be8a&username=user1&id=c8b8c19f-89e4-4b95-a32f-1938532d1ae7";

        System.out.println(getData(url4));
    }

    private static String getData(String urlToGO) {
        try {
            // Get the token
            URL url = new URL(urlToGO);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            Reader r = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(r);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }

            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
