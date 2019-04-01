package com.igordanilchik.rxjava2test.data.source.local

import com.igordanilchik.rxjava2test.common.preferences.IAppPreferences
import com.igordanilchik.rxjava2test.data.Categories
import com.igordanilchik.rxjava2test.data.Offers
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
private const val KEY_CATEGORY = "key_category_"
private const val KEY_OFFER = "key_offer_"

class LocalDataSource(private val preferences: IAppPreferences): ILocalDataSource {

    override fun saveCategories(categories: Categories) =
            categories.categories.forEach { category -> preferences.putJSON(KEY_CATEGORY + category.id, category) }

    override fun saveOffers(offers: Offers) =
            offers.meals.forEach { offer -> preferences.putJSON(KEY_OFFER + offer.id, offer) }

    override fun getCategories(): Observable<Categories> =
            Observable.fromCallable {
                Categories(preferences.getAllObjects(KEY_CATEGORY, Categories.Category::class.java))
            }

    override fun getOffers(): Observable<Offers> =
            Observable.fromCallable {
                Offers(preferences.getAllObjects(KEY_OFFER, Offers.Meal::class.java))
            }
}