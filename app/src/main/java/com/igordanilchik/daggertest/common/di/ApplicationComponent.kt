package com.igordanilchik.daggertest.common.di

import com.igordanilchik.daggertest.common.mvp.view.BaseFragment
import com.igordanilchik.daggertest.flows.catalogue.builder.CatalogueComponent
import com.igordanilchik.daggertest.flows.catalogue.builder.CatalogueModule
import com.igordanilchik.daggertest.flows.location.builder.LocationComponent
import com.igordanilchik.daggertest.flows.location.builder.LocationModule
import com.igordanilchik.daggertest.flows.offer.builder.OfferComponent
import com.igordanilchik.daggertest.flows.offer.builder.OfferModule
import com.igordanilchik.daggertest.flows.offers.builder.OffersComponent
import com.igordanilchik.daggertest.flows.offers.builder.OffersModule
import com.igordanilchik.daggertest.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    HttpClientModule::class,
    RepositoryModule::class,
    DataSourceModule::class,
    AppPreferencesModule::class,
    MapperModule::class
])
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(baseFragment: BaseFragment)

    fun plusCatalogueComponent(catalogueModule: CatalogueModule): CatalogueComponent
    fun plusOffersComponent(offersModule: OffersModule): OffersComponent
    fun plusOfferComponent(offerModule: OfferModule): OfferComponent
    fun plusLocationComponent(locationModule: LocationModule): LocationComponent
}
