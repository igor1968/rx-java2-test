package com.igordanilchik.daggertest.flows.catalogue.builder

import com.igordanilchik.daggertest.flows.catalogue.presenter.CataloguePresenter
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [CatalogueModule::class])
interface CatalogueComponent {
    fun presenter(): CataloguePresenter
}