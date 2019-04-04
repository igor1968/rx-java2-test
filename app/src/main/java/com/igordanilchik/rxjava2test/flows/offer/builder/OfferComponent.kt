package com.igordanilchik.rxjava2test.flows.offer.builder

import com.igordanilchik.rxjava2test.flows.offer.model.OfferSupplier
import com.igordanilchik.rxjava2test.flows.offer.presenter.OfferPresenter
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [OfferModule::class])
interface OfferComponent {
    fun presenter(): OfferPresenter

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun offerArguments(arguments: OfferSupplier): Builder

        fun build(): OfferComponent
    }
}