package com.igordanilchik.daggertest.api;


import com.igordanilchik.daggertest.model.Catalogue;

import io.rx_cache.EvictProvider;
import rx.Observable;


public interface CacheProviders {
    Observable<Catalogue> getCatalogueEvictProvider(Observable<Catalogue> observable, EvictProvider evictProvider);
}
