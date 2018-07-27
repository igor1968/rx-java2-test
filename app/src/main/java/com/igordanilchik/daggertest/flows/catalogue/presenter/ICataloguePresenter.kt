package com.igordanilchik.daggertest.flows.catalogue.presenter

import com.igordanilchik.daggertest.data.Categories

/**
 * @author Igor Danilchik
 */
interface ICataloguePresenter {
    fun onRefresh()
    fun onCategoryClicked(category: Categories.Category)
}