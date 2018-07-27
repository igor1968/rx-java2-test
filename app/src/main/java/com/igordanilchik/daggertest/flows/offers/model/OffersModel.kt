package com.igordanilchik.daggertest.flows.offers.model

import com.igordanilchik.daggertest.data.Offers
import com.igordanilchik.daggertest.data.source.IRepository
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Igor Danilchik
 */
class OffersModel(
        private val repository: IRepository,
        private val supplier: OffersSupplier
) : IOffersModel {

    private val id get() = supplier.id

    override fun loadOffers(): Observable<Offers> =
            repository.offers
                    .debounce(400, TimeUnit.MILLISECONDS)
                    .map { offers -> offers.offers.filter { offer -> offer.categoryId == id } }
                    .onErrorReturn { emptyList() }
                    .map(::Offers)
}