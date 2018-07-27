package com.igordanilchik.daggertest.flows.offer.model

import com.igordanilchik.daggertest.data.Offers
import rx.Observable

/**
 * @author Igor Danilchik
 */
interface IOfferModel {
    fun loadOffer(): Observable<Offers.Offer>
}