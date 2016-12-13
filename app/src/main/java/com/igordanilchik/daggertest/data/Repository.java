package com.igordanilchik.daggertest.data;

import android.support.annotation.NonNull;

import com.igordanilchik.daggertest.api.CacheProviders;
import com.igordanilchik.daggertest.api.ClientApi;
import com.igordanilchik.daggertest.model.Catalogue;

import java.io.File;

import javax.inject.Inject;

import io.rx_cache.EvictProvider;
import io.rx_cache.internal.RxCache;
import io.victoralbertos.jolyglot.JacksonSpeaker;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import rx.Observable;


public class Repository {
    private static final String LOG_TAG = Repository.class.getSimpleName();

    @NonNull
    private final CacheProviders providers;
    @NonNull
    private final ClientApi restApi;

    @Inject
    public Repository(File cacheDir, OkHttpClient httpClient) {
        providers = new RxCache.Builder()
                .persistence(cacheDir, new JacksonSpeaker())
                .using(CacheProviders.class);

        restApi = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(ClientApi.API_BASE_URL)
                .client(httpClient)
                .build()
                .create(ClientApi.class);
    }

    public Observable<Catalogue> getCatalogue(final boolean update) {
        return providers.getCatalogueEvictProvider(getRemoteCatalogue(), new EvictProvider(update));
    }

    private Observable<Catalogue> getRemoteCatalogue() {
        return restApi.loadCatalogue(ClientApi.API_KEY);
    }
}