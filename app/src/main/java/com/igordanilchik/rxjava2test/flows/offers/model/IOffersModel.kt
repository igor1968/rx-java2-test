package com.igordanilchik.rxjava2test.flows.offers.model

import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface IOffersModel {
    fun loadSubcategory(): Observable<Subcategory>
}