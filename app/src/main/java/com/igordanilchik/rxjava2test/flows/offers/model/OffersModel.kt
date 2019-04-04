package com.igordanilchik.rxjava2test.flows.offers.model

import com.igordanilchik.rxjava2test.data.catalogue.CatalogueRepository
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType.Companion.THROTTLING
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Igor Danilchik
 */
class OffersModel(
    private val repository: CatalogueRepository,
    private val supplier: OffersSupplier
) : IOffersModel {

    private val id get() = supplier.id

    private val name get() = supplier.name

    override fun loadSubcategory(): Observable<Subcategory> =
        repository.getOffers(THROTTLING)
            .debounce(400, TimeUnit.MILLISECONDS)
            .map { offers -> offers.filter { offer -> offer.categoryId == id } }
            .onErrorReturn { emptyList() }
            .map { offers ->
                Subcategory(
                    categoryId = id,
                    categoryName = name,
                    meals = offers
                )
            }
}