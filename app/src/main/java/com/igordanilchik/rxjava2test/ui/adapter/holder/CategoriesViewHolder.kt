package com.igordanilchik.rxjava2test.ui.adapter.holder

import android.view.View
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpDelegate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.igordanilchik.rxjava2test.R
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.ui.adapter.CategoriesAdapter
import com.igordanilchik.rxjava2test.ui.base.adapter.holder.BaseViewHolder
import kotlinx.android.synthetic.main.category_item.*

/**
 * @author Igor Danilchik
 */
class CategoriesViewHolder(
    contentView: View,
    parentDelegate: MvpDelegate<*>?,
    callback: CategoriesAdapter.CategoriesCallback?
) : BaseViewHolder<CategoryEntity, CategoriesAdapter.CategoriesCallback>(
    contentView,
    parentDelegate,
    callback
) {

    override fun render(item: CategoryEntity) {
        containerView.setOnClickListener { callback?.onCategoryClicked(item) }

        category_title.text = item.name

        val options = RequestOptions()
            .circleCrop()
            .placeholder(ContextCompat.getDrawable(containerView.context, R.drawable.ic_image_black_24dp))
            .diskCacheStrategy(DiskCacheStrategy.ALL)

        Glide.with(containerView.context)
            .load(item.pictureUrl)
            .apply(options)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(category_image)
    }
}