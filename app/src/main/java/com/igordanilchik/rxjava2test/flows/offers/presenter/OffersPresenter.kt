package com.igordanilchik.rxjava2test.flows.offers.presenter

import com.arellomobile.mvp.InjectViewState
import com.igordanilchik.rxjava2test.common.mvp.SchedulersSet
import com.igordanilchik.rxjava2test.common.mvp.presenter.AppBasePresenter
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.flows.offers.model.IOffersModel
import com.igordanilchik.rxjava2test.flows.offers.view.OffersView
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
            model.loadSubcategory(),
            {
                Timber.d("update meals UI")
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

    override fun onOfferClicked(offer: OfferEntity) = viewState.goToOffer(offer.id)
}
