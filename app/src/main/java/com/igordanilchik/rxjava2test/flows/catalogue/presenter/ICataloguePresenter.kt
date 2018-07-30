package com.igordanilchik.rxjava2test.flows.catalogue.presenter

import com.igordanilchik.rxjava2test.data.Categories

/**
 * @author Igor Danilchik
 */
interface ICataloguePresenter {
    fun onRefresh()
    fun onCategoryClicked(category: Categories.Category)
}