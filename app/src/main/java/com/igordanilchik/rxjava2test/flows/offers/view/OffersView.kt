package com.igordanilchik.rxjava2test.flows.offers.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.igordanilchik.rxjava2test.common.mvp.view.AppBaseView
import com.igordanilchik.rxjava2test.flows.offers.model.Subcategory
import com.igordanilchik.rxjava2test.flows.offers.router.OffersRouter

/**
 * @author Igor Danilchik
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface OffersView: AppBaseView, OffersRouter {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showOffers(subcategory: Subcategory)
    fun showError(throwable: Throwable)
    fun showProgress()
    fun hideProgress()
}