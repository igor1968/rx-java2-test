package com.igordanilchik.rxjava2test.flows.catalogue.builder

import com.igordanilchik.rxjava2test.data.source.IRepository
import com.igordanilchik.rxjava2test.flows.catalogue.model.CatalogueModel
import com.igordanilchik.rxjava2test.flows.catalogue.model.ICatalogueModel
import com.igordanilchik.rxjava2test.flows.catalogue.presenter.CataloguePresenter
import com.igordanilchik.rxjava2test.repo.SchedulersSet
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