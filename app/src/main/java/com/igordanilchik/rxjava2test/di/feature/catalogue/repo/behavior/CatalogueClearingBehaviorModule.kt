package com.igordanilchik.rxjava2test.di.feature.catalogue.repo.behavior

import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueClearingBehavior
import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueClearingCacheBehavior
import com.igordanilchik.rxjava2test.data.catalogue.local.CatalogueLocalStorage
import com.igordanilchik.rxjava2test.data.timestamp.local.TimeStampLocalStorage
import com.igordanilchik.rxjava2test.di.feature.catalogue.repo.storage.CatalogueLocalStorageModule
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module(includes = [CatalogueLocalStorageModule::class])
object CatalogueClearingBehaviorModule {

    @JvmStatic
    @Provides
    fun provideClearLocalStrategy(
        catalogueLocalStorage: CatalogueLocalStorage,
        timeStampStorage: TimeStampLocalStorage
    ): CatalogueClearingBehavior = CatalogueClearingCacheBehavior(
        catalogueLocalStorage,
        timeStampStorage
    )
}