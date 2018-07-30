package com.igordanilchik.rxjava2test.common.di

import com.igordanilchik.rxjava2test.common.mvp.view.BaseFragment
import com.igordanilchik.rxjava2test.flows.catalogue.builder.CatalogueComponent
import com.igordanilchik.rxjava2test.flows.catalogue.builder.CatalogueModule
import com.igordanilchik.rxjava2test.flows.location.builder.LocationComponent
import com.igordanilchik.rxjava2test.flows.location.builder.LocationModule
import com.igordanilchik.rxjava2test.flows.offer.builder.OfferComponent
import com.igordanilchik.rxjava2test.flows.offer.builder.OfferModule
import com.igordanilchik.rxjava2test.flows.offers.builder.OffersComponent
import com.igordanilchik.rxjava2test.flows.offers.builder.OffersModule
import com.igordanilchik.rxjava2test.ui.activity.MainActivity
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
