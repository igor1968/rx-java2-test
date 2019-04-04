package com.igordanilchik.rxjava2test.data.catalogue.behavior

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.catalogue.local.CatalogueLocalStorage
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
class CatalogueCacheLoadingBehavior(
    private val localStorage: CatalogueLocalStorage
): CatalogueLoadingBehavior {

    override fun getCategories(): Observable<List<CategoryEntity>> = localStorage.getCategories()

    override fun getOffers(): Observable<List<OfferEntity>> = localStorage.getOffers()
}