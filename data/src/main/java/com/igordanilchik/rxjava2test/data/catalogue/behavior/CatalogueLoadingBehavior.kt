package com.igordanilchik.rxjava2test.data.catalogue.behavior

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface CatalogueLoadingBehavior {
    fun getCategories(): Observable<List<CategoryEntity>>
    fun getOffers(): Observable<List<OfferEntity>>
}