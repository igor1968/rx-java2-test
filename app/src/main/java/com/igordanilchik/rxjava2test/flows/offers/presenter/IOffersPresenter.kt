package com.igordanilchik.rxjava2test.flows.offers.presenter

import com.igordanilchik.rxjava2test.data.Offers

/**
 * @author Igor Danilchik
 */
interface IOffersPresenter {
    fun onOfferClicked(offer: Offers.Offer)
    fun onRefresh()
}