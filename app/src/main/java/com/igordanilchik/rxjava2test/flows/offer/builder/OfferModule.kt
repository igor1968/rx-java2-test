package com.igordanilchik.rxjava2test.flows.offer.builder

import com.igordanilchik.rxjava2test.common.mvp.SchedulersSet
import com.igordanilchik.rxjava2test.data.catalogue.CatalogueRepository
import com.igordanilchik.rxjava2test.flows.offer.model.IOfferModel
import com.igordanilchik.rxjava2test.flows.offer.model.OfferModel
import com.igordanilchik.rxjava2test.flows.offer.model.OfferSupplier
import com.igordanilchik.rxjava2test.flows.offer.presenter.OfferPresenter
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module
object OfferModule {

    @JvmStatic
    @Provides
    fun presenter(
        schedulersSet: SchedulersSet,
        model: IOfferModel
    ): OfferPresenter = OfferPresenter(
        schedulersSet,
        model
    )

    @JvmStatic
    @Provides
    fun model(
        repository: CatalogueRepository,
        supplier: OfferSupplier
    ): IOfferModel =
        OfferModel(
            repository,
            supplier
        )
}