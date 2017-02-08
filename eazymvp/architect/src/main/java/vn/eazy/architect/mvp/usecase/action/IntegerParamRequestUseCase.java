package vn.eazy.architect.mvp.usecase.action;

import io.reactivex.Flowable;

/**
 * Created by Harry on 9/7/16.
 */

public interface IntegerParamRequestUseCase<T> {
    Flowable<T> request(Integer... data);
}
