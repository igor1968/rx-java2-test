package com.igordanilchik.rxjava2test.di.feature.catalogue.repo

import com.igordanilchik.rxjava2test.data.catalogue.CatalogueRepository
import com.igordanilchik.rxjava2test.di.common.Private
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [DefaultCatalogueRepositoryModule::class])
interface CatalogueRepositoryComponent {

    @Private
    fun repo(): CatalogueRepository

    @Subcomponent.Builder
    interface Builder {
        fun build(): CatalogueRepositoryComponent
    }
}