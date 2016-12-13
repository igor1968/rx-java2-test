package com.igordanilchik.daggertest;

import android.content.Context;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class AppModule {
    private Context context;

    AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    File provideCacheDirectory() {
        return context.getFilesDir();
    }
}
