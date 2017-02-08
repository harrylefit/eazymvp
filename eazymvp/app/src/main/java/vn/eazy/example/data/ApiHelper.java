package vn.eazy.example.data;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Harry on 1/7/17.
 */

public class ApiHelper {
    public static final String BASE_URL = "http://128.199.231.251//eazydota/api/";
    private static ApiHelper instance;

    private Cache cache;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private ApiService apiService;

    public static ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }

    public Cache getCache() {
        if (cache == null) {
            cache = new Cache(new File("cache"), 1024L * 1024L * 100L);
        }
        return cache;
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder().cache(getCache()).build();
        }
        return okHttpClient;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper(new JsonFactory())
                            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return retrofit;
    }


    public ApiService getApiService() {
        if (apiService == null) {
            apiService = getRetrofit().create(ApiService.class);
        }
        return apiService;
    }
}
