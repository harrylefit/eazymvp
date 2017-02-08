package vn.eazy.example.presenter.main;

import java.util.List;

import vn.eazy.architect.mvp.base.BasePresenter;
import vn.eazy.example.data.News;

/**
 * Created by Harry on 2/8/17.
 */

public interface MainView extends BasePresenter.View{
    void onGetAllNewsSuccess(List<News> news);
    void showError();
}
