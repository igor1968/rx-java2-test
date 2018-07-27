package com.igordanilchik.daggertest.flows.offer.model

import com.igordanilchik.daggertest.data.Offers
import com.igordanilchik.daggertest.data.source.IRepository
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Igor Danilchik
 */
class OfferModel(
        private val repository: IRepository,
        private val supplier: OfferSupplier
) : IOfferModel {

    private val id get() = supplier.id

    override fun loadOffer(): Observable<Offers.Offer> =
            repository.offers
                    .debounce(400, TimeUnit.MILLISECONDS)
                    .map { offers -> offers.offers.first { it.id == id } }

}