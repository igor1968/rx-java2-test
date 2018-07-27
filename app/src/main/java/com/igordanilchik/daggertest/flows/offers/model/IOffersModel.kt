package com.igordanilchik.daggertest.flows.offers.model

import com.igordanilchik.daggertest.data.Offers
import rx.Observable

/**
 * @author Igor Danilchik
 */
interface IOffersModel {
    fun loadOffers(): Observable<Offers>
}