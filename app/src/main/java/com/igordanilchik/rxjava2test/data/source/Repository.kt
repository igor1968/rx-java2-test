package com.igordanilchik.rxjava2test.data.source

import com.igordanilchik.rxjava2test.data.Categories
import com.igordanilchik.rxjava2test.data.Offers
import com.igordanilchik.rxjava2test.data.source.local.ILocalDataSource
import com.igordanilchik.rxjava2test.data.source.remote.IRemoteDataSource
import com.igordanilchik.rxjava2test.dto.ICatalogueMapper
import io.reactivex.Observable

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