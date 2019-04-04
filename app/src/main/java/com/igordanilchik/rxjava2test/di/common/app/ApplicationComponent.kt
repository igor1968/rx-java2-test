package com.igordanilchik.rxjava2test.di.common.app

import com.igordanilchik.rxjava2test.common.mvp.view.BaseFragment
import com.igordanilchik.rxjava2test.di.common.data.EndpointModule
import com.igordanilchik.rxjava2test.di.common.data.NetworkModule
import com.igordanilchik.rxjava2test.di.common.data.PreferencesModule
import com.igordanilchik.rxjava2test.di.feature.catalogue.repo.CatalogueRepositoryModule
import com.igordanilchik.rxjava2test.di.feature.timestamp.TimeStampModule
import com.igordanilchik.rxjava2test.flows.catalogue.builder.CatalogueComponent
import com.igordanilchik.rxjava2test.flows.location.builder.LocationComponent
import com.igordanilchik.rxjava2test.flows.offer.builder.OfferComponent
import com.igordanilchik.rxjava2test.flows.offers.builder.OffersComponent
import com.igordanilchik.rxjava2test.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    EndpointModule::class,
    NetworkModule::class,
    PreferencesModule::class,
    TimeStampModule::class,
    CatalogueRepositoryModule::class
])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(baseFragment: BaseFragment)

    fun catalogueComponentBuilder(): CatalogueComponent.Builder
    fun offersComponentBuilder(): OffersComponent.Builder
    fun offerComponentBuilder(): OfferComponent.Builder
    fun locationComponentBuilder(): LocationComponent.Builder
}
