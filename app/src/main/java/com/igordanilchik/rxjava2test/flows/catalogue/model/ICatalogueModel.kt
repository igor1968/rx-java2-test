package com.igordanilchik.rxjava2test.flows.catalogue.model

import com.igordanilchik.rxjava2test.data.Categories
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface ICatalogueModel {
    fun loadCategories(): Observable<Categories>
}