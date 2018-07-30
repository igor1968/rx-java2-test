package com.igordanilchik.rxjava2test.flows.offers.builder

import com.igordanilchik.rxjava2test.flows.offers.presenter.OffersPresenter
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [OffersModule::class])
interface OffersComponent {
    fun presenter(): OffersPresenter
}