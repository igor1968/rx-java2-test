package com.igordanilchik.daggertest.data.source.local

import com.igordanilchik.daggertest.data.Categories
import com.igordanilchik.daggertest.data.Offers
import rx.Observable

/**
 * @author Igor Danilchik
 */
interface ILocalDataSource {
    fun saveCategories(categories: Categories)
    fun saveOffers(offers: Offers)
    fun getCategories(): Observable<Categories>
    fun getOffers(): Observable<Offers>
}