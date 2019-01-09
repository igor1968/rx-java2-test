package com.igordanilchik.rxjava2test.flows.offers.view

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.igordanilchik.rxjava2test.R
import com.igordanilchik.rxjava2test.common.mvp.view.BaseFragment
import com.igordanilchik.rxjava2test.data.Offers
import com.igordanilchik.rxjava2test.flows.offers.model.Subcategory
import com.igordanilchik.rxjava2test.flows.offers.builder.OffersModule
import com.igordanilchik.rxjava2test.flows.offers.model.OffersSupplier
import com.igordanilchik.rxjava2test.flows.offers.presenter.OffersPresenter
import com.igordanilchik.rxjava2test.ui.adapter.OffersAdapter

/**
 * @author Igor Danilchik
 */
class OffersFragment : BaseFragment(), OffersView, OffersAdapter.OffersCallback {

    @BindView(R.id.swipe_container)
    lateinit var swipeContainer: SwipeRefreshLayout
    @BindView(R.id.offers_recycler_view)
    lateinit var recyclerView: RecyclerView

    @InjectPresenter
    lateinit var presenter: OffersPresenter

    override val layoutResID = R.layout.fragment_offers

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeContainer.setOnRefreshListener(presenter::onRefresh)
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onDestroyView() {
        recyclerView.adapter = null

        super.onDestroyView()
    }

    override fun onOfferClicked(offer: Offers.Meal) = presenter.onOfferClicked(offer)

    override fun showOffers(subcategory: Subcategory) {
        (recyclerView.adapter as? OffersAdapter)?.apply {
            appendOrUpdate(subcategory.meals)
        } ?: run {
            recyclerView.adapter = OffersAdapter(subcategory, this)
        }

        subcategory.categoryName?.let { setTitle(it) }
    }

    override fun showError(throwable: Throwable) {
        Snackbar.make(recyclerView, "Error: " + throwable.message, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showProgress() {
        swipeContainer.post { swipeContainer.isRefreshing = true }
    }

    override fun hideProgress() {
        swipeContainer.post { swipeContainer.isRefreshing = false }
    }

    override fun goToOffer(id: Int) {
        val directions = OffersFragmentDirections.toOfferFragment().setOfferId(id)
        view?.findNavController()?.navigate(directions)
    }

    @ProvidePresenter
    fun providePresenter(): OffersPresenter {
        val supplier = OffersSupplier(
            id = arguments?.let { OffersFragmentArgs.fromBundle(it).categoryId } ?: 0,
            name = arguments?.let { OffersFragmentArgs.fromBundle(it).categoryName })

        return appComponent().plusOffersComponent(OffersModule(supplier)).presenter()
    }
}