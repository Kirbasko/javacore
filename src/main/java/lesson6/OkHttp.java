package lesson6;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttp {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://dataservice.accuweather.com/forecasts/v1/daily/5day/293686?apikey=rwp2kN2qgVfPoQwhJ482L4Do4ACzvUa1")
                .build();
        Response response = client.newCall(request).execute();
        String body = response.body().string();
        System.out.println(body);

    }
}
