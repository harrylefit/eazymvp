package vn.eazy.architect.mvp.di.component;

import android.app.Application;

import com.google.gson.Gson;

import java.io.File;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Component;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.OkHttpClient;
import vn.eazy.architect.mvp.di.module.AppModule;
import vn.eazy.architect.mvp.di.module.ClientModule;
import vn.eazy.architect.mvp.di.module.GlobalConfigModule;
import vn.eazy.architect.mvp.intergration.AppManager;
import vn.eazy.architect.mvp.intergration.IRepositoryManager;
import vn.eazy.architect.mvp.widget.imageloader.ImageLoader;

/**
 * Created by harryle on 6/7/17.
 */
@Singleton
@Component(modules = {AppModule.class, ClientModule.class, GlobalConfigModule.class})
public interface AppComponent {
    Application application();

    IRepositoryManager repositoryManager();

    RxErrorHandler rxErrorHandler();

    OkHttpClient okHttpClient();

    ImageLoader imageLoader();

    Gson gson();

    File cacheFile();

    AppManager appManager();

    Map<String,Object> extras();

}
