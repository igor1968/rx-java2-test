package com.igordanilchik.daggertest.flows.offer.builder

import com.igordanilchik.daggertest.flows.offer.presenter.OfferPresenter
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [OfferModule::class])
interface OfferComponent {
    fun presenter(): OfferPresenter
}