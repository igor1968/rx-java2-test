package com.igordanilchik.rxjava2test.flows.catalogue.model

import com.igordanilchik.rxjava2test.data.catalogue.CatalogueRepository
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Igor Danilchik
 */
class CatalogueModel(
    private val repository: CatalogueRepository
) : ICatalogueModel {

    override fun loadCategories(@CatalogueLoadingBehaviorType behavior: Int): Observable<List<CategoryEntity>> =
        repository.getCategories(behavior)
            .debounce(400, TimeUnit.MILLISECONDS)
}