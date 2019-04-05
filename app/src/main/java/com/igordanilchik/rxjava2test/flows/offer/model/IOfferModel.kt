package com.igordanilchik.rxjava2test.flows.offer.model

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface IOfferModel {
    fun loadOffer(@CatalogueLoadingBehaviorType behavior: Int): Observable<OfferEntity>
}