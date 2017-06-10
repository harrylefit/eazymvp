package vn.eazy.architect.mvp.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.eazy.architect.mvp.di.component.AppComponent;

/**
 * Created by harryle on 6/8/17.
 */

public interface IFragment {
    void setupFragmentComponent(AppComponent appComponent);

    boolean useEventBus();

    View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    void initData(Bundle savedInstanceState);

    void setData(Object data);
}
