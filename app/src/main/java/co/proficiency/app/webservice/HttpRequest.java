package co.proficiency.app.webservice;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import co.proficiency.app.generic.IConstant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {
    public static final String BASE_URL = IConstant.API_BASE_URL;
    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60000, TimeUnit.SECONDS)
                    .readTimeout(60000, TimeUnit.SECONDS).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    /**
     * JSON to Object  -> getObjectFromJSON
     */
    public Object getObjectFromJSON(String responseValue, Class<?> classname) {
        Gson gDataBean = new Gson();
        return gDataBean.fromJson(responseValue, classname);
    }

}
