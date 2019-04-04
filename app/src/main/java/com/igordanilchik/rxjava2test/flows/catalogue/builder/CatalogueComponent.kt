package com.igordanilchik.rxjava2test.flows.catalogue.builder

import com.igordanilchik.rxjava2test.flows.catalogue.presenter.CataloguePresenter
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [CatalogueModule::class])
interface CatalogueComponent {
    fun presenter(): CataloguePresenter

    @Subcomponent.Builder
    interface Builder {
        fun build(): CatalogueComponent
    }

}