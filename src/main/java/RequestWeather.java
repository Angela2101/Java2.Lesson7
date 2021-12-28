import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class RequestWeather {

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String API_KEY = "Ju94o7RnQ8yl8LUKiNlGeFnLYrPkr8MP";

    static public String getCityIdRequest (String cityName) throws IOException {
        String cityId = null;

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("locations")
                .addPathSegment("v1")
                .addPathSegment("cities")
                .addPathSegment("search")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", cityName)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        String responseJson = response.body().string();
        JsonNode cityIdNode = objectMapper.readTree(responseJson).at("/0/Key");
        cityId = cityIdNode.asText();

        return  cityId;
    }

    static public Weather getOneDailyWeatherCityRequest (String cityId, String cityName) throws IOException {

        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("1day")
                .addPathSegment(cityId)
                .addQueryParameter("apikey", API_KEY)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(httpUrl)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseJson = response.body().string();

        JsonNode dateNode = objectMapper.readTree(responseJson).at("/DailyForecasts/0/Date");
        var date = dateNode.asText().split("T");
        var temperatureNode  = objectMapper.readTree(responseJson).at("/DailyForecasts/0/Temperature/Minimum/Value");
        var temperature = Math.round((temperatureNode.asDouble() - 32) / 1.8);
        var textNode = objectMapper.readTree(responseJson).at("/Headline/Text");
        var weatherText = textNode.asText();


        Weather weather = new Weather(cityName, date[0], temperature, weatherText);

        return weather;
    }
}
