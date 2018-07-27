package com.igordanilchik.daggertest.flows.offers.presenter

import com.igordanilchik.daggertest.data.Offers

/**
 * @author Igor Danilchik
 */
interface IOffersPresenter {
    fun onOfferClicked(offer: Offers.Offer)
}