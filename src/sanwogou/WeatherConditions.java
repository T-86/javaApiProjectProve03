package sanwogou;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WeatherConditions {
    private int id;
    private String name;

    @SerializedName("main")
    private Map<String, Float> measurements;

    public WeatherConditions(){
        this.id = 0;
        this.name = null;
        this.measurements = new HashMap<>();
    }

    public Map<String, Float> getMeasurements() {
        return measurements;
    }

    @Override
    public String toString() {
        return "WeatherConditions{" +
                "\nid = " + id +
                ", \nname = " + name +
                ", \nmeasurements = " + measurements +
                '}';
    }

    public static HttpURLConnection connection;

    static Gson gson = new Gson();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // let's define a BufferedReader
        BufferedReader reader;
        String line;
        // stringbuffer will allows us to append each line to the result
        StringBuilder responseContent = new StringBuilder();
        // 2- Let's define the url
        URL url;
        // WeatherConditions api url form definition
        String city_name;
        String api_key = "3397bbc1b73aee8a0ce2ec758bf5ceb2&units=metric";
        String base_api_url = "https://api.openweathermap.org/data/2.5/weather?";
        System.out.println("Enter a city name: ");
        city_name = scanner.nextLine();
        String api_url = base_api_url + "q=" + city_name + "&appid=" + api_key;

        //if you want to change the temperature value from kelvin to celcius, just add &units=metric at the end of the  api_key
        try {


            url = new URL(api_url);

            // We now open the connection to the API endpoint
            connection = (HttpURLConnection) url.openConnection();

            // let's setup our Request
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            // once the request set, let's try to get the response from the server
            // 1- let's get our response code to check if the connection is successful
            int status = connection.getResponseCode();
            System.out.println(status);

            // The response we get from the endpoint is an inputstream and in order to read it we are going to use
            // an inputstream reader known as BufferedReaser

            // before then let's handle the case where status is not successful
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }

            WeatherConditions weatherConditionsObject = gson.fromJson(String.valueOf(responseContent), WeatherConditions.class);
            System.out.println(weatherConditionsObject);
            System.out.println("Temp = " + weatherConditionsObject.getMeasurements().get("temp") + " C");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

    }
}
