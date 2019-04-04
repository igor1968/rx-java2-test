package com.igordanilchik.rxjava2test.flows.offer.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.igordanilchik.rxjava2test.common.mvp.view.AppBaseView
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.flows.offer.router.OfferRouter

/**
 * @author Igor Danilchik
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface OfferView : AppBaseView, OfferRouter {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showOffer(offer: OfferEntity)

    fun showProgress()
    fun hideProgress()
    fun showError(throwable: Throwable)
}