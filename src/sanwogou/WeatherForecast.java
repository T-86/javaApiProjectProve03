package sanwogou;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeatherForecast {
    private List<WeatherForecastItem> list;

    public WeatherForecast(){
        list = new ArrayList<>();
    }


    public String toString() {
        StringBuilder weatherForcastString = new StringBuilder();
        for (WeatherForecastItem item : list){
            weatherForcastString.append(item.toString() + "\n");
        }
                  return weatherForcastString.toString();
             }


    public static HttpURLConnection connection;
    static Gson gson = new Gson();


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        URL url;
        String city_name;
        BufferedReader reader;
        String line;

        String api_key = "3397bbc1b73aee8a0ce2ec758bf5ceb2&units=metric";
        String base_api_url = "https://api.openweathermap.org/data/2.5/forecast?";
        System.out.println("Enter a city name: ");
        city_name = scanner.nextLine();
        String api_url = base_api_url + "q=" + city_name + "&appid=" + api_key;
        StringBuilder responseContent = new StringBuilder();

        try {


            url = new URL(api_url);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);

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

            WeatherForecast weatherForecastObject = gson.fromJson(responseContent.toString(), WeatherForecast.class);
            System.out.println(weatherForecastObject.list.get(0));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        }





}
