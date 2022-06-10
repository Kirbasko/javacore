package lesson7;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lesson7.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    //http://dataservice.accuweather.com/forecasts/v1/daily/5day/293686?apikey=rwp2kN2qgVfPoQwhJ482L4Do4ACzvUa1
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAY = "5day";
    private static final String API_KEY = "wPIY7WXaqiCg3ex9VBfdCs4BywW1SW3w";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    public void getWeather(String selectedCity, Period period) throws IOException {
        switch (period) {
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();
                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponce = oneDayForecastResponse.body().string();

                String date = weatherResponce.split(":")[21];
                date = date.split("T")[0];
                date = date.replace("\"", "");


                String tempMin = weatherResponce.split(":")[28];
                tempMin = tempMin.split(",")[0];
                String tempMax = weatherResponce.split(":")[32];
                tempMax = tempMax.split(",")[0];

                System.out.println("Сегодня: " + date + ". " +
                        "Температура от " + tempMin + " до " + tempMax + " градусов.\n");

                break;

                //dataBaseRepository.saveWeatherToDataBase(new Weather()); //- тут после парсинга добавляем данные в БД

            case FIVE_DAYS:
                HttpUrl httpUrl5 = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request5 = new Request.Builder()
                        .url(httpUrl5)
                        .build();
                Response fiveDayForecastResponse = okHttpClient.newCall(request5).execute();
                String weatherResponce5 = fiveDayForecastResponse.body().string();

                for(int i = 1; i < 6; i++) {
                    String splitDays = weatherResponce5.split("\"Date\":\"")[i];
                    String date5 = splitDays.split("T")[0];


                    String tempMin5 = splitDays.split(":")[7];
                    tempMin5 = tempMin5.split(",")[0];
                    String tempMax5 = splitDays.split(":")[11];
                    tempMax5 = tempMax5.split(",")[0];

                    System.out.println(date5 + ". "  +
                            "Температура от " + tempMin5 + " до " + tempMax5 + " градусов.");
                }
                System.out.println();
                break;
        }
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dataBaseRepository.getSavedToDBWeather();
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }
}