package com.igordanilchik.rxjava2test.flows.offers.builder

import com.igordanilchik.rxjava2test.data.source.IRepository
import com.igordanilchik.rxjava2test.flows.offers.model.IOffersModel
import com.igordanilchik.rxjava2test.flows.offers.model.OffersModel
import com.igordanilchik.rxjava2test.flows.offers.model.OffersSupplier
import com.igordanilchik.rxjava2test.flows.offers.presenter.OffersPresenter
import com.igordanilchik.rxjava2test.repo.SchedulersSet
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