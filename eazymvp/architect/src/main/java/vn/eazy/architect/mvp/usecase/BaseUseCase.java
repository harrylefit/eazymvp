package vn.eazy.architect.mvp.usecase;

import android.content.Context;

/**
 * Created by Harry on 1/7/17.
 */

public abstract class BaseUseCase {
    protected Context context;

    public BaseUseCase(Context context) {
        this.context = context;
    }
}
