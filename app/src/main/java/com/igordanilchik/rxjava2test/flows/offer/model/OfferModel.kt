package com.igordanilchik.rxjava2test.flows.offer.model

import com.igordanilchik.rxjava2test.data.catalogue.CatalogueRepository
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Igor Danilchik
 */
class OfferModel(
    private val repository: CatalogueRepository,
    private val supplier: OfferSupplier
) : IOfferModel {

    private val id get() = supplier.id

    override fun loadOffer(@CatalogueLoadingBehaviorType behavior: Int): Observable<OfferEntity> =
        repository.getOffers(behavior)
            .debounce(400, TimeUnit.MILLISECONDS)
            .map { offers -> offers.first { it.id == id } }
}