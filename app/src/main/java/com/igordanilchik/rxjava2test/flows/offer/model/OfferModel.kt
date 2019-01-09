package com.igordanilchik.rxjava2test.flows.offer.model

import com.igordanilchik.rxjava2test.data.Offers
import com.igordanilchik.rxjava2test.data.source.IRepository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Igor Danilchik
 */
class OfferModel(
        private val repository: IRepository,
        private val supplier: OfferSupplier
) : IOfferModel {

    private val id get() = supplier.id

    override fun loadOffer(): Observable<Offers.Meal> =
            repository.offers
                    .debounce(400, TimeUnit.MILLISECONDS)
                    .map { offers -> offers.meals.first { it.id == id } }

}