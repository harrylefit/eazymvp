package vn.eazy.architect.mvp.usecase.action;

import android.os.Bundle;

import io.reactivex.Flowable;

/**
 * Created by Harry on 9/7/16.
 */

public interface BundleRequestUseCase<T> {
    Flowable<T> request(Bundle bundle);
}
