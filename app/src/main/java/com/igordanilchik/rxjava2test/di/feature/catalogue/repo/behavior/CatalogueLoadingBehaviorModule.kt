package com.igordanilchik.rxjava2test.di.feature.catalogue.repo.behavior

import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueCacheLoadingBehavior
import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueForceRefreshBehavior
import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueLoadingBehavior
import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueThrottlingBehavior
import com.igordanilchik.rxjava2test.data.catalogue.local.CatalogueLocalStorage
import com.igordanilchik.rxjava2test.data.catalogue.remote.CatalogueRemoteStorage
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType
import com.igordanilchik.rxjava2test.data.common.Constants.RequestThrottling.Companion.DEFAULT_CATALOGUE_THROTTLING_TIME_IN_SEC
import com.igordanilchik.rxjava2test.data.common.KeyValueFactory
import com.igordanilchik.rxjava2test.data.common.Throttling
import com.igordanilchik.rxjava2test.data.timestamp.local.TimeStampLocalStorage
import com.igordanilchik.rxjava2test.di.common.data.BehaviorFactory
import com.igordanilchik.rxjava2test.di.feature.catalogue.repo.storage.CatalogueLocalStorageModule
import com.igordanilchik.rxjava2test.di.feature.catalogue.repo.storage.CatalogueRemoteStorageModule
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap

/**
 * @author Igor Danilchik
 */
@Module(
    includes = [
        CatalogueLocalStorageModule::class,
        CatalogueRemoteStorageModule::class
    ]
)
object CatalogueLoadingBehaviorModule {

    @JvmStatic
    @Provides
    internal fun provideLoadingStrategyFactory(
        strategies: Lazy<Map<Int, CatalogueLoadingBehavior>>
    ): KeyValueFactory<Int, CatalogueLoadingBehavior> = BehaviorFactory(strategies)

    @JvmStatic
    @IntoMap
    @Provides
    @IntKey(CatalogueLoadingBehaviorType.LOCAL)
    internal fun provideLocalStrategy(
        localStorage: CatalogueLocalStorage
    ): CatalogueLoadingBehavior = CatalogueCacheLoadingBehavior(
        localStorage
    )

    @IntoMap
    @Provides
    @JvmStatic
    @IntKey(CatalogueLoadingBehaviorType.THROTTLING)
    internal fun provideThrottlingStrategy(
        remoteStorage: CatalogueRemoteStorage,
        localStorage: CatalogueLocalStorage,
        timeStampStorage: TimeStampLocalStorage
        //hotObservableExecutorsFactory: KeyValueFactory<Int, @JvmSuppressWildcards HotObservableExecutor<Eligibility>>,
        //throttlingTimeService: RequestThrottlingService
    ): CatalogueLoadingBehavior = CatalogueThrottlingBehavior(
        remoteStorage,
        localStorage,
        timeStampStorage,
        Throttling(DEFAULT_CATALOGUE_THROTTLING_TIME_IN_SEC)
        //hotObservableExecutorsFactory,
        //throttlingTimeService.eligibilityThrottling()
    )

    @IntoMap
    @Provides
    @JvmStatic
    @IntKey(CatalogueLoadingBehaviorType.FORCE_REFRESH)
    internal fun provideForceRefreshStrategy(
        remoteStorage: CatalogueRemoteStorage,
        localStorage: CatalogueLocalStorage,
        timeStampStorage: TimeStampLocalStorage
        //hotObservableExecutorsFactory: KeyValueFactory<Int, @JvmSuppressWildcards HotObservableExecutor<Eligibility>>
    ): CatalogueLoadingBehavior = CatalogueForceRefreshBehavior(
        remoteStorage,
        localStorage,
        timeStampStorage
        //hotObservableExecutorsFactory
    )
}