package vn.eazy.example.data;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Harry on 1/7/17.
 */

public interface ApiService {
    @GET("news/{type}")
    Flowable<List<News>> getNewsById(@Path("type") int id);
}
