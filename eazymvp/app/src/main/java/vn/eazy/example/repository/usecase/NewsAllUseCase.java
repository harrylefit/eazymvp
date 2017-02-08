package vn.eazy.example.repository.usecase;

import android.content.Context;

import java.util.List;

import io.reactivex.Flowable;
import vn.eazy.architect.mvp.usecase.BaseUseCase;
import vn.eazy.architect.mvp.usecase.action.NonParamRequestUseCase;
import vn.eazy.example.data.ApiHelper;
import vn.eazy.example.data.News;

/**
 * Created by Harry on 2/8/17.
 */

public class NewsAllUseCase extends BaseUseCase implements NonParamRequestUseCase<List<News>> {
    public NewsAllUseCase(Context context) {
        super(context);
    }

    @Override
    public Flowable<List<News>> request() {
        return ApiHelper.getInstance().getApiService().getNewsById(2);
    }
}
