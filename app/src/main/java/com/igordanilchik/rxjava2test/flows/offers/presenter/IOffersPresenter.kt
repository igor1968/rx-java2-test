package com.igordanilchik.rxjava2test.flows.offers.presenter

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity

/**
 * @author Igor Danilchik
 */
interface IOffersPresenter {
    fun onOfferClicked(offer: OfferEntity)
    fun onRefresh()
}