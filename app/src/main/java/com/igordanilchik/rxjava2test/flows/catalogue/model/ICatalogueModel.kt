package com.igordanilchik.rxjava2test.flows.catalogue.model

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface ICatalogueModel {
    fun loadCategories(@CatalogueLoadingBehaviorType behavior: Int): Observable<List<CategoryEntity>>
}