package com.igordanilchik.rxjava2test.di.feature.catalogue.repo

import com.igordanilchik.rxjava2test.data.catalogue.CatalogueRepository
import com.igordanilchik.rxjava2test.data.catalogue.DefaultCatalogueRepository
import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueClearingBehavior
import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueLoadingBehavior
import com.igordanilchik.rxjava2test.data.common.KeyValueFactory
import com.igordanilchik.rxjava2test.di.common.Private
import com.igordanilchik.rxjava2test.di.feature.catalogue.repo.behavior.CatalogueClearingBehaviorModule
import com.igordanilchik.rxjava2test.di.feature.catalogue.repo.behavior.CatalogueLoadingBehaviorModule
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module(
    includes = [
        CatalogueLoadingBehaviorModule::class,
        CatalogueClearingBehaviorModule::class
    ]
)
object DefaultCatalogueRepositoryModule {

    @Private
    @JvmStatic
    @Provides
    internal fun provideRepo(
        loadingStrategyFactory: @JvmSuppressWildcards KeyValueFactory<Int, CatalogueLoadingBehavior>,
        clearingStrategy: CatalogueClearingBehavior
    ): CatalogueRepository =
        DefaultCatalogueRepository(
            loadingStrategyFactory,
            clearingStrategy
        )
}