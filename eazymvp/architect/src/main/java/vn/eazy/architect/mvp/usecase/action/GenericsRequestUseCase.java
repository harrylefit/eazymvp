package vn.eazy.architect.mvp.usecase.action;

import io.reactivex.Flowable;

/**
 * Created by Harry on 9/7/16.
 */

public interface GenericsRequestUseCase<T> {
    Flowable<T> request(T data);
}
