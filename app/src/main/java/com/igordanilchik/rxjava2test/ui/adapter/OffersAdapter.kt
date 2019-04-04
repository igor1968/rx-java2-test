package com.igordanilchik.rxjava2test.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.igordanilchik.rxjava2test.R
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.flows.offers.model.Subcategory
import com.igordanilchik.rxjava2test.ui.adapter.holder.OffersViewHolder
import com.igordanilchik.rxjava2test.ui.base.adapter.BaseAdapter
import com.igordanilchik.rxjava2test.ui.base.adapter.holder.BaseViewHolder

/**
 * @author Igor Danilchik
 */
class OffersAdapter(
    subcategory: Subcategory,
    private val callback: OffersCallback?
) : BaseAdapter<BaseViewHolder<OfferEntity, OffersAdapter.OffersCallback>, OfferEntity>(
    subcategory.meals,
    null
) {

    override val adapterID: String = OffersAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<OfferEntity, OffersCallback> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.offers_item, parent, false)

        return OffersViewHolder(v, null, callback)
    }

    interface OffersCallback {
        fun onOfferClicked(offer: OfferEntity)
    }
}