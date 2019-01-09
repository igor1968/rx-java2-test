package com.igordanilchik.rxjava2test.flows.offer.builder

import com.igordanilchik.rxjava2test.data.source.IRepository
import com.igordanilchik.rxjava2test.flows.offer.model.IOfferModel
import com.igordanilchik.rxjava2test.flows.offer.model.OfferModel
import com.igordanilchik.rxjava2test.flows.offer.model.OfferSupplier
import com.igordanilchik.rxjava2test.flows.offer.presenter.OfferPresenter
import com.igordanilchik.rxjava2test.common.mvp.SchedulersSet
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module
class OfferModule(private val supplier: OfferSupplier) {

    @Provides
    fun presenter(
            schedulersSet: SchedulersSet,
            model: IOfferModel
    ): OfferPresenter = OfferPresenter(
            schedulersSet,
            model
    )

    @Provides
    fun model(repository: IRepository): IOfferModel =
            OfferModel(
                    repository,
                    supplier
            )

}