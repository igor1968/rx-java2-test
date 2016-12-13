package com.igordanilchik.daggertest;

import com.igordanilchik.daggertest.data.Repository;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
class RepositoryModule {

    @Provides
    Repository provideRepository(File cacheDir, OkHttpClient httpClient) {
        return new Repository(cacheDir, httpClient);
    }
}
