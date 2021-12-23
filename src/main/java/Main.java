import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String cityName = "Kazan";
        var cityId = RequestWeather.getCityIdRequest(cityName);
        System.out.println(RequestWeather.getCityIdRequest(cityName));
        System.out.println(RequestWeather.getOneDailyWeatherCityRequest(cityId));
    }
}
