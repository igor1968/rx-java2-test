package com.igordanilchik.rxjava2test.flows.catalogue.model

import com.igordanilchik.rxjava2test.data.catalogue.CatalogueRepository
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType.Companion.THROTTLING
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Igor Danilchik
 */
class CatalogueModel(val repository: CatalogueRepository) : ICatalogueModel {

    override fun loadCategories(): Observable<List<CategoryEntity>> =
        repository.getCategories(THROTTLING)
            .debounce(400, TimeUnit.MILLISECONDS)
}