package com.igordanilchik.daggertest.flows.offer.builder

import com.igordanilchik.daggertest.data.source.IRepository
import com.igordanilchik.daggertest.flows.offer.model.IOfferModel
import com.igordanilchik.daggertest.flows.offer.model.OfferModel
import com.igordanilchik.daggertest.flows.offer.model.OfferSupplier
import com.igordanilchik.daggertest.flows.offer.presenter.OfferPresenter
import com.igordanilchik.daggertest.repo.SchedulersSet
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