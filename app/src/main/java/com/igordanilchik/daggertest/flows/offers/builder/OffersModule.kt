package com.igordanilchik.daggertest.flows.offers.builder

import com.igordanilchik.daggertest.data.source.IRepository
import com.igordanilchik.daggertest.flows.offers.model.IOffersModel
import com.igordanilchik.daggertest.flows.offers.model.OffersModel
import com.igordanilchik.daggertest.flows.offers.model.OffersSupplier
import com.igordanilchik.daggertest.flows.offers.presenter.OffersPresenter
import com.igordanilchik.daggertest.repo.SchedulersSet
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module
class OffersModule(private val supplier: OffersSupplier) {

    @Provides
    fun model(repository: IRepository): IOffersModel =
            OffersModel(
                    repository,
                    supplier
            )

    @Provides
    fun presenter(
            schedulersSet: SchedulersSet,
            model: IOffersModel
    ): OffersPresenter = OffersPresenter(
            schedulersSet,
            model
    )
}