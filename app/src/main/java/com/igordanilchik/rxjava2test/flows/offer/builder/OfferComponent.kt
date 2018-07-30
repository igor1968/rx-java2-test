package com.igordanilchik.rxjava2test.flows.offer.builder

import com.igordanilchik.rxjava2test.flows.offer.presenter.OfferPresenter
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [OfferModule::class])
interface OfferComponent {
    fun presenter(): OfferPresenter
}