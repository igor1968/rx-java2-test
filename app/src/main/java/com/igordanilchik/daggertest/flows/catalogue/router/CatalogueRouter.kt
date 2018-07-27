package com.igordanilchik.daggertest.flows.catalogue.router

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @author Igor Danilchik
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface CatalogueRouter {
    fun goToCategory(id: Int)
}