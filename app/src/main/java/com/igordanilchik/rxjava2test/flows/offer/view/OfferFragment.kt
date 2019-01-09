package com.igordanilchik.rxjava2test.flows.offer.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.igordanilchik.rxjava2test.R
import com.igordanilchik.rxjava2test.common.mvp.view.BaseFragment
import com.igordanilchik.rxjava2test.data.Offers
import com.igordanilchik.rxjava2test.data.getParamByKey
import com.igordanilchik.rxjava2test.flows.offer.builder.OfferModule
import com.igordanilchik.rxjava2test.flows.offer.model.OfferSupplier
import com.igordanilchik.rxjava2test.flows.offer.presenter.OfferPresenter

/**
 * @author Igor Danilchik
 */
class OfferFragment : BaseFragment(), OfferView {

    @BindView(R.id.swipe_container)
    lateinit var swipeContainer: SwipeRefreshLayout
    @BindView(R.id.card_image)
    lateinit var image: ImageView
    @BindView(R.id.card_title)
    lateinit var title: TextView
    @BindView(R.id.card_price)
    lateinit var price: TextView
    @BindView(R.id.card_weight)
    lateinit var weight: TextView
    @BindView(R.id.card_description)
    lateinit var description: TextView
    @BindView(R.id.linear_layout)
    lateinit var linearLayout: LinearLayout

    @InjectPresenter
    lateinit var presenter: OfferPresenter

    override val layoutResID = R.layout.fragment_offer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeContainer.setOnRefreshListener(presenter::onRefresh)
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
   }


    override fun showOffer(offer: Offers.Meal) {
        setTitle(offer.name)

        title.text = offer.name
        price.text = getString(R.string.offer_price, offer.price)

        offer.getParamByKey(getString(R.string.param_name_weight)).let {
            weight.text = getString(R.string.offer_weight, it)
        }

        offer.pictureUrl?.let { url ->
            url.takeIf { it.isNotEmpty() }?.let {
                val options = RequestOptions()
                    .fitCenter()
                    .placeholder(context?.let { ctx -> ContextCompat.getDrawable(ctx, R.drawable.ic_image_black_24dp) })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                Glide.with(this)
                    .load(it)
                    .apply(options)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image)
            }
        } ?: run { image.visibility = View.GONE }

        description.text = offer.description
    }

    override fun showProgress() {
        swipeContainer.post { swipeContainer.isRefreshing = true }
    }

    override fun hideProgress() {
        swipeContainer.post { swipeContainer.isRefreshing = false }
    }

    override fun showError(throwable: Throwable) {
        Snackbar.make(linearLayout, "Error: " + throwable.message, Snackbar.LENGTH_LONG)
                .show()
    }

    @ProvidePresenter
    fun providePresenter(): OfferPresenter {
        val supplier = OfferSupplier(id = arguments?.let { OfferFragmentArgs.fromBundle(it).offerId } ?: 0)

        return appComponent().plusOfferComponent(OfferModule(supplier)).presenter()
    }


}