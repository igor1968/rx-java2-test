package com.igordanilchik.rxjava2test.flows.offers.model

import com.igordanilchik.rxjava2test.data.Offers
import com.igordanilchik.rxjava2test.data.source.IRepository
import io.reactivex.Observable
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