package com.igordanilchik.rxjava2test.flows.catalogue.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.igordanilchik.rxjava2test.common.mvp.view.AppBaseView
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.flows.catalogue.router.CatalogueRouter

/**
 * @author Igor Danilchik
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface CatalogueView : AppBaseView, CatalogueRouter {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCategories(categories: List<CategoryEntity>)

    fun showProgress()
    fun hideProgress()
    fun showEmptyState()
    fun hideEmptyState()
    fun showError(throwable: Throwable)
}