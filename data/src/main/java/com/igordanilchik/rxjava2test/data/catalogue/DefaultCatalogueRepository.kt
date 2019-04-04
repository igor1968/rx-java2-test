package com.igordanilchik.rxjava2test.data.catalogue

import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueClearingBehavior
import com.igordanilchik.rxjava2test.data.catalogue.behavior.CatalogueLoadingBehavior
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType
import com.igordanilchik.rxjava2test.data.common.KeyValueFactory
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
class DefaultCatalogueRepository(
    private val loadingBehaviorFactory: KeyValueFactory<Int, CatalogueLoadingBehavior>,
    private val clearingBehavior: CatalogueClearingBehavior
) : CatalogueRepository {

    override fun getCategories(
        @CatalogueLoadingBehaviorType behavior: Int
    ): Observable<List<CategoryEntity>> = loadingBehaviorFactory
        .getInstance(behavior)
        .getCategories()

    override fun getOffers(
        @CatalogueLoadingBehaviorType behavior: Int
    ): Observable<List<OfferEntity>> = loadingBehaviorFactory
        .getInstance(behavior)
        .getOffers()

    override fun clear() = clearingBehavior.clear()

}