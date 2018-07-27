package com.igordanilchik.daggertest.data.source

import com.igordanilchik.daggertest.data.Categories
import com.igordanilchik.daggertest.data.Offers
import com.igordanilchik.daggertest.data.source.local.ILocalDataSource
import com.igordanilchik.daggertest.data.source.remote.IRemoteDataSource
import com.igordanilchik.daggertest.dto.ICatalogueMapper
import rx.Observable

/**
 * @author Igor Danilchik
 */
class Repository(
        private val localDataSource: ILocalDataSource,
        private val remoteDataSource: IRemoteDataSource,
        private val mapper: ICatalogueMapper
): IRepository {

    override val categories: Observable<Categories>
        get() = Observable.concat(
                localDataSource.getCategories(),
                remoteDataSource.catalogue.map { mapper.mapToCategories(it) }
                        .doOnNext { localDataSource.saveCategories(it) }
        )

    override val offers: Observable<Offers>
        get() = Observable.concat(
                localDataSource.getOffers(),
                remoteDataSource.catalogue.map { mapper.mapToOffers(it) }
                        .doOnNext { localDataSource.saveOffers(it) }
        )

}