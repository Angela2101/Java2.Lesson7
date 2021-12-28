import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        String cityName = "Penza";
        var cityId = RequestWeather.getCityIdRequest(cityName);
        System.out.println(RequestWeather.getCityIdRequest(cityName));
        var weather  = RequestWeather.getOneDailyWeatherCityRequest(cityId, cityName);
        System.out.println(weather);


        DbHandler dbHandler = new DbHandler();
        dbHandler.add(weather.getCity(), weather.getDate(), weather.getWeatherText(), weather.getTemperature());
        System.out.println(dbHandler.getAllWeather());
    }
}
