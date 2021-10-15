package sanwogou;

import com.google.gson.annotations.SerializedName;

import java.util.*;

public class WeatherForecastItem {
    private String dt_txt;

    @SerializedName("main")
    private Map<String, Float> measurements;
    private WeatherWind wind;
    private List<WeatherDescription> weather;


//    public WeatherForecastItem(Map<String, Float> measurements, WeatherWind wind, List<WeatherDescription> weather, String dt_txt){
//        this.dt_txt = dt_txt;
//        this.measurements = new HashMap<>();
//        this.wind = wind;
//        this.weather = new ArrayList<>();
//
//    }

    @Override
    public String toString() {
        return  "\ndt_txt: " + dt_txt +
                "\nTemp: " + measurements.get("temp_max") + " / " + measurements.get("temp_min") +
                "\nWeather: " + weather.get(0).getDescription() +
                "\nWind: " + wind.getSpeed();
    }
}
