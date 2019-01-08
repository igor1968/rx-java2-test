package com.igordanilchik.rxjava2test.flows.offers.presenter

import com.arellomobile.mvp.InjectViewState
import com.igordanilchik.rxjava2test.common.mvp.presenter.AppBasePresenter
import com.igordanilchik.rxjava2test.data.Offers
import com.igordanilchik.rxjava2test.flows.offers.model.IOffersModel
import com.igordanilchik.rxjava2test.flows.offers.view.OffersView
import com.igordanilchik.rxjava2test.repo.SchedulersSet
import timber.log.Timber

/**
 * @author Igor Danilchik
 */
@InjectViewState
class OffersPresenter(
        schedulersSet: SchedulersSet,
        private val model: IOffersModel
) : AppBasePresenter<OffersView>(schedulersSet), IOffersPresenter {

    override fun attachView(view: OffersView?) {
        super.attachView(view)

        loadData()
    }

    override fun onRefresh() = loadData()

    private fun loadData() {
        executeOn(
                ExecuteOn.IO_DESTROY,
                model.loadOffers(),
                {
                    Timber.d("update offers UI")
                    viewState.showOffers(it)
                },
                viewState::showError,
                {}
        ) {
            it.doOnSubscribe {
                viewState.showProgress()
            }.doFinally { viewState.hideProgress() }
        }
    }

    override fun onOfferClicked(offer: Offers.Offer) = viewState.goToOffer(offer.id)

}
