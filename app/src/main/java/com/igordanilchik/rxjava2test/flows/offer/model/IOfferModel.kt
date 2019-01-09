package com.igordanilchik.rxjava2test.flows.offer.model

import com.igordanilchik.rxjava2test.data.Offers
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface IOfferModel {
    fun loadOffer(): Observable<Offers.Meal>
}