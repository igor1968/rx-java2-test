package com.igordanilchik.rxjava2test.di.feature.catalogue.repo.storage

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.xml.Catalogue
import com.igordanilchik.rxjava2test.data.catalogue.remote.CatalogueNetworkStorage
import com.igordanilchik.rxjava2test.data.catalogue.remote.CatalogueRemoteStorage
import com.igordanilchik.rxjava2test.data.common.Mapper
import com.igordanilchik.rxjava2test.data.common.api.ClientApi
import com.igordanilchik.rxjava2test.data.service.EndpointProvider
import com.igordanilchik.rxjava2test.di.common.data.ApiModule
import com.igordanilchik.rxjava2test.di.feature.catalogue.repo.mapper.CatalogueMapperModule
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module(
    includes = [
        CatalogueMapperModule::class,
        ApiModule::class
    ]
)
object CatalogueRemoteStorageModule {

    @JvmStatic
    @Provides
    internal fun provideStorage(
        api: ClientApi,
        endpointProvider: EndpointProvider,
        categoryMapper: Mapper<Catalogue, List<CategoryEntity>>,
        offerMapper: Mapper<Catalogue, List<OfferEntity>>
    ): CatalogueRemoteStorage =
        CatalogueNetworkStorage(
            api,
            endpointProvider,
            categoryMapper,
            offerMapper
        )
}