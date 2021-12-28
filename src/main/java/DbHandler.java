import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;

public class DbHandler {

    private final String PATH_TO_DB = "jdbc:sqlite:src/main/resources/db_Weather.db";

    private Connection connection;

    public DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(PATH_TO_DB);
    }

    public void add (String city, String date, String weatherText, double temperature){
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO weather('city', 'date', 'weatherText', 'temperature') " +
                        "VALUES(?, ?, ?, ?)"
        )) {
            statement.setObject(1, city);
            statement.setObject(2, date);
            statement.setObject(3, weatherText);
            statement.setObject(4, temperature);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Weather> getAllWeather(){
        ArrayList<Weather> weathers = new ArrayList<>();
        try(Statement statement = this.connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * from weather");
            while (resultSet.next()){
                weathers.add(new Weather(
                        resultSet.getString("city"),
                        resultSet.getString("date"),
                        resultSet.getDouble("temperature"),
                        resultSet.getString("weatherText")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weathers;
    }
}
