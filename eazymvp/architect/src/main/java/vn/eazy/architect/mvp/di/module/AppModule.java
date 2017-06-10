package vn.eazy.architect.mvp.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.eazy.architect.mvp.intergration.IRepositoryManager;
import vn.eazy.architect.mvp.intergration.RepositoryManager;

/**
 * Created by harryle on 6/7/17.
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public Application providerApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    public Gson providerGson(Application application, @Nullable GsonConfiguration gsonConfiguration) {
        GsonBuilder builder = new GsonBuilder();
        if (gsonConfiguration != null) {
            gsonConfiguration.configGson(application, builder);
        }
        return builder.create();
    }

    @Singleton
    @Provides
    public Map<String, Object> provideExtras() {
        return new ArrayMap<>();
    }

    @Singleton
    @Provides
    public IRepositoryManager providerRepositoryManager(RepositoryManager repositoryManager) {
        return repositoryManager;
    }

    public interface GsonConfiguration {
        void configGson(Context context, GsonBuilder builder);
    }
}
