package vn.eazy.architect.mvp.delegate;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vn.eazy.architect.mvp.base.App;
import vn.eazy.architect.mvp.di.component.AppComponent;
import vn.eazy.architect.mvp.intergration.ActivityLifeCycle;
import vn.eazy.architect.mvp.intergration.ConfigModule;
import vn.eazy.architect.mvp.intergration.ManifestParser;

/**
 * Created by harryle on 6/10/17.
 */

public class AppDelegate implements App{
    private Application mApplication;
    private AppComponent mAppComponent;
    @Inject
    protected ActivityLifeCycle mActivityLifeCycle;
    private final List<ConfigModule> mModules;
    private List<Lifecycle> mLifecycles = new ArrayList<>();
    private List<Application.ActivityLifecycleCallbacks> mActivityLifecycleCallbackses = new ArrayList<>();

    public AppDelegate(Application application){
        this.mApplication = application;
        mModules = new ManifestParser(mApplication).parse();
        for(ConfigModule module : mModules){
            module.injectAppLifecycle(mApplication,mLifecycles);
            module.injectActivityLifecycle(mApplication,mActivityLifecycleCallbackses);
        }
    }

    public void onCreate(){
    }


    @Override
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public interface Lifecycle {
        void onCreate(Application application);

        void onTerminate(Application application);
    }
}
