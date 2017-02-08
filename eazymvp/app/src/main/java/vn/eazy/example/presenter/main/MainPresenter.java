package vn.eazy.example.presenter.main;

import android.content.Context;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.eazy.architect.mvp.base.BasePresenter;
import vn.eazy.example.data.News;
import vn.eazy.example.repository.usecase.NewsAllUseCase;

/**
 * Created by Harry on 2/8/17.
 */

public class MainPresenter extends BasePresenter<MainView> {
    private final NewsAllUseCase newsAllUseCase;

    public MainPresenter(Context context) {
        super(context);
        newsAllUseCase = new NewsAllUseCase(context);
    }

    public void getNews() {
        compositeDisposable.add(newsAllUseCase.request().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<News>>() {
                    @Override
                    public void accept(List<News> newses) throws Exception {
                        if (isAttached()) {
                            getView().onGetAllNewsSuccess(newses);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (isAttached()) {
                            getView().showError();
                        }
                    }
                }));
    }
}
