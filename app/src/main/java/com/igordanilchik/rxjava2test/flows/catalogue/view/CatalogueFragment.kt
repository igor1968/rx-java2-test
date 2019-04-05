package com.igordanilchik.rxjava2test.flows.catalogue.view

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.google.android.material.snackbar.Snackbar
import com.igordanilchik.rxjava2test.R
import com.igordanilchik.rxjava2test.common.mvp.view.BaseFragment
import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.flows.catalogue.presenter.CataloguePresenter
import com.igordanilchik.rxjava2test.ui.adapter.CategoriesAdapter
import kotlinx.android.synthetic.main.empty_state.*
import kotlinx.android.synthetic.main.fragment_catalogue.*

/**
 * @author Igor Danilchik
 */
class CatalogueFragment : BaseFragment(), CatalogueView, CategoriesAdapter.CategoriesCallback {

    @InjectPresenter
    lateinit var presenter: CataloguePresenter

    override val layoutResID = R.layout.fragment_catalogue

    override val baseTitle = R.string.farfor_title

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipe_container.setOnRefreshListener(presenter::onRefresh)
        swipe_container.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        catalogue_recycler_view.setHasFixedSize(true)
        catalogue_recycler_view.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        catalogue_recycler_view.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                activity,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun onDestroyView() {
        catalogue_recycler_view.adapter = null

        super.onDestroyView()
    }

    override fun onCategoryClicked(category: CategoryEntity) =
        presenter.onCategoryClicked(category)

    override fun showCategories(categories: List<CategoryEntity>) {
        (catalogue_recycler_view.adapter as? CategoriesAdapter)?.apply {
            appendOrUpdate(categories)
        } ?: run {
            catalogue_recycler_view.adapter = CategoriesAdapter(categories, this)
        }
    }

    override fun showError(throwable: Throwable) =
        Snackbar.make(catalogue_recycler_view, "Error: ${throwable.message}", Snackbar.LENGTH_LONG)
            .show()

    override fun showProgress() {
        swipe_container.post { swipe_container.isRefreshing = true }
    }

    override fun hideProgress() {
        swipe_container.post { swipe_container.isRefreshing = false }
    }

    override fun showEmptyState() {
        empty_state_container.visibility = View.VISIBLE
    }

    override fun hideEmptyState() {
        empty_state_container.visibility = View.GONE
    }

    override fun goToCategory(id: Int, name: String) {
        val directions = CatalogueFragmentDirections.toOffersFragment(name)
            .setCategoryId(id)
        view?.findNavController()?.navigate(directions)
    }

    @ProvidePresenter
    fun providePresenter(): CataloguePresenter =
        appComponent()
            .catalogueComponentBuilder()
            .build()
            .presenter()
}