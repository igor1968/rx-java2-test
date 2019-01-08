package com.igordanilchik.rxjava2test.flows.offer.presenter

import com.arellomobile.mvp.InjectViewState
import com.igordanilchik.rxjava2test.common.mvp.presenter.AppBasePresenter
import com.igordanilchik.rxjava2test.flows.offer.model.IOfferModel
import com.igordanilchik.rxjava2test.flows.offer.view.OfferView
import com.igordanilchik.rxjava2test.repo.SchedulersSet
import timber.log.Timber

/**
 * @author Igor Danilchik
 */
@InjectViewState
class OfferPresenter(
        schedulersSet: SchedulersSet,
        private val model: IOfferModel
) : AppBasePresenter<OfferView>(schedulersSet), IOfferPresenter {


    override fun attachView(view: OfferView?) {
        super.attachView(view)

        loadData()
    }

    override fun onRefresh() = loadData()

    private fun loadData() {
        executeOn(
                ExecuteOn.IO_DESTROY,
                model.loadOffer(),
                {
                    Timber.d("update offers UI")
                    viewState.showOffer(it)
                },
                viewState::showError,
                {}
        ) {
            it.doOnSubscribe {
                viewState.showProgress()
            }.doFinally { viewState.hideProgress() }
        }
    }

}