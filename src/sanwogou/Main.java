package sanwogou;

import com.google.gson.Gson;


public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();

        WeatherConditions weatherConditions = new WeatherConditions();
        weatherConditions.setUrlConnection();
        String jsonContent = weatherConditions.getDataStream();

        WeatherConditions weatherConditionsObject = gson.fromJson(jsonContent, WeatherConditions.class);

        System.out.println(weatherConditionsObject);
        System.out.println("Temp = " + weatherConditionsObject.getMeasurements().get("temp") + " C");

    }
    }
