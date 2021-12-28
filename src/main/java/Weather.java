
public class Weather {
    private String city;
    private String date;
    private double temperature;
    private String weatherText;

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", date='" + date + '\'' +
                ", temperature=" + temperature +
                ", weatherText='" + weatherText + '\'' +
                '}';
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getWeatherText() {
        return weatherText;
    }

    public Weather(String city, String date, double temperature, String weatherText) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
        this.weatherText = weatherText;
    }
}
