package vn.eazy.architect.mvp.usecase.action;

import io.reactivex.Flowable;

/**
 * Created by Harry on 9/7/16.
 */

public interface StringParamRequestUseCase<T> {
    Flowable<T> request(String... data);
}
