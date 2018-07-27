package com.igordanilchik.daggertest.flows.offers.presenter

import com.arellomobile.mvp.InjectViewState
import com.igordanilchik.daggertest.common.mvp.presenter.AppBasePresenter
import com.igordanilchik.daggertest.data.Offers
import com.igordanilchik.daggertest.flows.offers.model.IOffersModel
import com.igordanilchik.daggertest.flows.offers.view.OffersView
import com.igordanilchik.daggertest.repo.SchedulersSet
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
            }.doOnUnsubscribe { viewState.hideProgress() }
        }
    }

    override fun onOfferClicked(offer: Offers.Offer) = viewState.goToOffer(offer.id)

}
