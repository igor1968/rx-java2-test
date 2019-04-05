package com.igordanilchik.rxjava2test.flows.catalogue.presenter

import com.arellomobile.mvp.InjectViewState
import com.igordanilchik.rxjava2test.common.mvp.SchedulersSet
import com.igordanilchik.rxjava2test.common.mvp.presenter.AppBasePresenter
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType.Companion.FORCE_REFRESH
import com.igordanilchik.rxjava2test.data.common.Constants.CatalogueLoadingBehaviorType.Companion.THROTTLING
import com.igordanilchik.rxjava2test.data.common.logger.CapLogger
import com.igordanilchik.rxjava2test.flows.catalogue.model.ICatalogueModel
import com.igordanilchik.rxjava2test.flows.catalogue.view.CatalogueView

/**
 * @author Igor Danilchik
 */

@InjectViewState
class CataloguePresenter(
    schedulersSet: SchedulersSet,
    val model: ICatalogueModel
) : AppBasePresenter<CatalogueView>(schedulersSet), ICataloguePresenter {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showEmptyState()
        loadData(THROTTLING)
    }

    private fun loadData(@CatalogueLoadingBehaviorType behavior: Int) {
        executeOn(
            ExecuteOn.IO_DESTROY,
            model.loadCategories(behavior),
            { categories ->
                CapLogger.d("update categories UI")
                viewState.hideEmptyState()
                viewState.showCategories(categories)
            },
            viewState::showError,
            {}
        ) {
            it.doOnSubscribe {
                viewState.showProgress()
            }.doFinally { viewState.hideProgress() }
        }
    }

    override fun onRefresh() = loadData(FORCE_REFRESH)

    override fun onCategoryClicked(category: CategoryEntity) =
        viewState.goToCategory(category.id, category.name)
}