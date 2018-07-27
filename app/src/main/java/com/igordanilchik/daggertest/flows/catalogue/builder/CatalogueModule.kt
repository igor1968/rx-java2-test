package com.igordanilchik.daggertest.flows.catalogue.builder

import com.igordanilchik.daggertest.data.source.IRepository
import com.igordanilchik.daggertest.flows.catalogue.model.CatalogueModel
import com.igordanilchik.daggertest.flows.catalogue.model.ICatalogueModel
import com.igordanilchik.daggertest.flows.catalogue.presenter.CataloguePresenter
import com.igordanilchik.daggertest.repo.SchedulersSet
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module
class CatalogueModule {

    @Provides
    internal fun presenter(
            schedulersSet: SchedulersSet,
            model: ICatalogueModel
    ): CataloguePresenter = CataloguePresenter(
            schedulersSet,
            model
    )

    @Provides
    internal fun model(repository: IRepository): ICatalogueModel = CatalogueModel(repository)

}