package com.igordanilchik.daggertest;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.StrictMode;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        final boolean isDebuggable = 0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE);

        if (isDebuggable) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    //.penaltyDeath()
                    .build());
        }
        initComponents();
    }

    private void initComponents() {
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
