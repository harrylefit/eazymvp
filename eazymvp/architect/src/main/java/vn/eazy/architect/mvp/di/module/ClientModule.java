package vn.eazy.architect.mvp.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.listener.ResponseErrorListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.eazy.architect.mvp.base.App;
import vn.eazy.architect.mvp.http.GlobalHttpHandler;
import vn.eazy.architect.mvp.http.RequestInterceptor;
import vn.eazy.architect.mvp.utils.DataHelper;

/**
 * Created by harryle on 6/7/17.
 */
@Module
public class ClientModule {
    private static final int TIME_OUT = 10;

    @Singleton
    @Provides
    Retrofit provideRetrofit(Application application, RetrofitConfiguration retrofitConfiguration, Retrofit.Builder builder
            , OkHttpClient okHttpClient, HttpUrl httpUrl) {
        builder.baseUrl(httpUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        if (retrofitConfiguration != null) {
            retrofitConfiguration.configRetrofit(application, builder);
        }
        return builder.build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Application application, @Nullable OkHttpConfiguration configuration, OkHttpClient.Builder builder
            , Interceptor interceptor, @Nullable List<Interceptor> interceptors, @Nullable final GlobalHttpHandler globalHttpHandler) {
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor);

        if (globalHttpHandler != null) {
            builder.addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    return chain.proceed(globalHttpHandler.onHttpRequestBefore(chain, chain.request()));
                }
            });
        }

        if (interceptors != null) {
            for (Interceptor _interceptor : interceptors) {
                builder.addInterceptor(_interceptor);
            }
        }

        if (configuration != null) {
            configuration.configOkHttp(application, builder);
        }
        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHtppClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    Interceptor provideInterceptor(RequestInterceptor requestInterceptor) {
        return requestInterceptor;
    }

    @Singleton
    @Provides
    RxCache provideRxCache(Application application, @Nullable RxCacheConfiguration configuration
            , @Named("RxCacheDirectory") File cacheDirectory) {
        RxCache.Builder builder = new RxCache.Builder();
        if (configuration != null) {
            configuration.configRxCache(application, builder);
        }
        return builder.persistence(cacheDirectory, new GsonSpeaker());
    }

    @Singleton
    @Provides
    @Named("RxCacheDirectory")
    File provideCacheDirectory(File cacheDir) {
        File cacheDirectory = new File(cacheDir, "RxCache");
        return DataHelper.makeDirs(cacheDirectory);
    }

    @Singleton
    @Provides
    RxErrorHandler proRxErrorHandler(Application application, ResponseErrorListener listener) {
        return RxErrorHandler.builder()
                .with(application)
                .responseErrorListener(listener)
                .build();
    }

    public interface RetrofitConfiguration {
        void configRetrofit(Context context, Retrofit.Builder builder);
    }

    public interface OkHttpConfiguration {
        void configOkHttp(Context context, OkHttpClient.Builder builder);
    }

    public interface RxCacheConfiguration {
        void configRxCache(Context context, RxCache.Builder builder);
    }
}
