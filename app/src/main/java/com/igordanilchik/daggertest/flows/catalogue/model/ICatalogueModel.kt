package com.igordanilchik.daggertest.flows.catalogue.model

import com.igordanilchik.daggertest.data.Categories
import rx.Observable


/**
 * @author Igor Danilchik
 */
interface ICatalogueModel {
    fun loadCategories(): Observable<Categories>
}