package com.igordanilchik.daggertest;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

public class DaggerApplication extends Application {

    public static DaggerApplication get(Context context) {
        return (DaggerApplication) context.getApplicationContext();
    }

    private AppComponent appComponent;
    private RepositoryComponent repositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjector();
        initLeakDetection();
    }

    private void initLeakDetection() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);

//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                        .detectAll()
//                        .penaltyLog()
//                        .build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                        .detectAll()
//                        .penaltyLog()
//                        //.penaltyDeath()
//                        .build());
        }
    }

    private void initInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpClientModule(new HttpClientModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public RepositoryComponent getRepositoryComponent() {
        if (repositoryComponent == null) {
            repositoryComponent = appComponent.plus(new RepositoryModule());
        }
        return repositoryComponent;
    }
}
