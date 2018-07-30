package com.igordanilchik.rxjava2test.flows.offers.model

import com.igordanilchik.rxjava2test.data.Offers
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface IOffersModel {
    fun loadOffers(): Observable<Offers>
}