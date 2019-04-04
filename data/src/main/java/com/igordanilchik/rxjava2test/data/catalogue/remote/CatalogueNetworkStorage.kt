package com.igordanilchik.rxjava2test.data.catalogue.remote

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.xml.Catalogue
import com.igordanilchik.rxjava2test.data.common.Mapper
import com.igordanilchik.rxjava2test.data.common.api.ClientApi
import com.igordanilchik.rxjava2test.data.service.EndpointProvider
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
class CatalogueNetworkStorage(
    private val api: ClientApi,
    private val endpointProvider: EndpointProvider,
    private val categoryMapper: Mapper<Catalogue, List<CategoryEntity>>,
    private val offerMapper: Mapper<Catalogue, List<OfferEntity>>
): CatalogueRemoteStorage {

    private val catalogue: Observable<Catalogue>
        get() = api.getCatalogue(endpointProvider.shopEndpoint(), ClientApi.API_KEY)

    override fun getCategories(): Observable<List<CategoryEntity>> = catalogue.map(categoryMapper::map)

    override fun getOffers(): Observable<List<OfferEntity>> = catalogue.map(offerMapper::map)
}