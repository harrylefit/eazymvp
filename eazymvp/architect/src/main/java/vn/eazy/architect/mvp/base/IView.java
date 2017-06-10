package vn.eazy.architect.mvp.base;

import android.content.Intent;

/**
 * Created by harryle on 6/7/17.
 */

public interface IView {
    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void lauchActivity(Intent intent);

    void killMySelf();
}
