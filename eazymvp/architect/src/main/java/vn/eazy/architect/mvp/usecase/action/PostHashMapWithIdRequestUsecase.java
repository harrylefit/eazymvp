package vn.eazy.architect.mvp.usecase.action;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by Harry on 9/21/16.
 */

public interface PostHashMapWithIdRequestUsecase<T>{
    Flowable<T> request(String id, Map<String, String> param);
}
