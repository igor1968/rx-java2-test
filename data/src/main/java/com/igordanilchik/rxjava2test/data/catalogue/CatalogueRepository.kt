package com.igordanilchik.rxjava2test.data.catalogue

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface CatalogueRepository {
    fun clear()
    fun getCategories(@CatalogueLoadingBehaviorType behavior: Int): Observable<List<CategoryEntity>>
    fun getOffers(@CatalogueLoadingBehaviorType behavior: Int): Observable<List<OfferEntity>>
}