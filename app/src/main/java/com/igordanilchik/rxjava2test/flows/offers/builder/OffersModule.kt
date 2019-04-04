package com.igordanilchik.rxjava2test.flows.offers.builder

import com.igordanilchik.rxjava2test.common.mvp.SchedulersSet
import com.igordanilchik.rxjava2test.data.catalogue.CatalogueRepository
import com.igordanilchik.rxjava2test.flows.offers.model.IOffersModel
import com.igordanilchik.rxjava2test.flows.offers.model.OffersModel
import com.igordanilchik.rxjava2test.flows.offers.model.OffersSupplier
import com.igordanilchik.rxjava2test.flows.offers.presenter.OffersPresenter
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module
object OffersModule {

    @JvmStatic
    @Provides
    fun model(
        repository: CatalogueRepository,
        supplier: OffersSupplier
    ): IOffersModel =
        OffersModel(
            repository,
            supplier
        )

    @JvmStatic
    @Provides
    fun presenter(
        schedulersSet: SchedulersSet,
        model: IOffersModel
    ): OffersPresenter = OffersPresenter(
        schedulersSet,
        model
    )
}