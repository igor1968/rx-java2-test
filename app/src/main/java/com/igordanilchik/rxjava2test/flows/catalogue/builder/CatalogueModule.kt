package com.igordanilchik.rxjava2test.flows.catalogue.builder

import com.igordanilchik.rxjava2test.common.mvp.SchedulersSet
import com.igordanilchik.rxjava2test.data.catalogue.CatalogueRepository
import com.igordanilchik.rxjava2test.flows.catalogue.model.CatalogueModel
import com.igordanilchik.rxjava2test.flows.catalogue.model.ICatalogueModel
import com.igordanilchik.rxjava2test.flows.catalogue.presenter.CataloguePresenter
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module
object CatalogueModule {

    @JvmStatic
    @Provides
    internal fun presenter(
        schedulersSet: SchedulersSet,
        model: ICatalogueModel
    ): CataloguePresenter = CataloguePresenter(
        schedulersSet,
        model
    )

    @JvmStatic
    @Provides
    internal fun model(
        repository: CatalogueRepository
    ): ICatalogueModel = CatalogueModel(repository)
}