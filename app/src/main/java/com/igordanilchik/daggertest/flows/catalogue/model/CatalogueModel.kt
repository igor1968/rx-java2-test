package com.igordanilchik.daggertest.flows.catalogue.model

import com.igordanilchik.daggertest.data.Categories
import com.igordanilchik.daggertest.data.source.IRepository
import rx.Observable
import java.util.concurrent.TimeUnit

/**
 * @author Igor Danilchik
 */
class CatalogueModel(val repository: IRepository) : ICatalogueModel {

    override fun loadCategories(): Observable<Categories> =
            repository.categories.debounce(400, TimeUnit.MILLISECONDS)

}