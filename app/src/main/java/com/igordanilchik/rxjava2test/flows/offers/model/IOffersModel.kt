package com.igordanilchik.rxjava2test.flows.offers.model

import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface IOffersModel {
    fun loadSubcategory(@CatalogueLoadingBehaviorType behavior: Int): Observable<Subcategory>
}