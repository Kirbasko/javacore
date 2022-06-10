package lesson7;


import lesson7.entity.Weather;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseRepository {
    private String insertWeather = "insert into weather (city, localdate, temperature) values (?, ?, ?)";
    private String getWeather = "select * from weather";
    private static final String DB_PATH = "jdbc:sqlite:weather.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean saveWeatherToDataBase(Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getDate());
            saveWeather.setDouble(3, weather.getTempMax());
            saveWeather.setDouble(4, weather.getTempMin());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Сохранение погоды в базу данных не выполнено!");
    }

    public void saveWeatherToDataBase(List<Weather> weatherList) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            for (Weather weather : weatherList) {
                saveWeather.setString(1, weather.getCity());
                saveWeather.setString(2, weather.getDate());
                saveWeather.setDouble(3, weather.getTempMax());
                saveWeather.setDouble(4, weather.getTempMin());
                saveWeather.addBatch();
            }
            saveWeather.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Weather> getSavedToDBWeather() {
        List<Weather> weathers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getWeather);
            while (resultSet.next()) {
                System.out.print(resultSet.getString("city"));
                System.out.println(" ");
                System.out.print(resultSet.getString("date"));
                System.out.println(" ");
                System.out.print(resultSet.getDouble("tempMax"));
                System.out.println(" ");
                System.out.print(resultSet.getDouble("tempMin"));
                System.out.println(" ");
                weathers.add(new Weather(resultSet.getString("city"),
                        resultSet.getString("date"),
                        resultSet.getDouble("tempMax"),
                        resultSet.getDouble("tempMin")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weathers;
    }

    public static void main(String[] args) throws SQLException {
        DataBaseRepository dataBaseRepository = new DataBaseRepository();
        System.out.println(dataBaseRepository.getSavedToDBWeather());
    }
}
