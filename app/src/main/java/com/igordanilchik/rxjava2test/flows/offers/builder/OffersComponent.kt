package com.igordanilchik.rxjava2test.flows.offers.builder

import com.igordanilchik.rxjava2test.flows.offers.model.OffersSupplier
import com.igordanilchik.rxjava2test.flows.offers.presenter.OffersPresenter
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [OffersModule::class])
interface OffersComponent {
    fun presenter(): OffersPresenter

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun offersArguments(arguments: OffersSupplier): Builder

        fun build(): OffersComponent
    }
}