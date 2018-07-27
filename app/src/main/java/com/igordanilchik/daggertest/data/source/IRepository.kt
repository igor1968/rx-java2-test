package com.igordanilchik.daggertest.data.source

import com.igordanilchik.daggertest.data.Categories
import com.igordanilchik.daggertest.data.Offers
import rx.Observable


/**
 * @author Igor Danilchik
 */
interface IRepository {
    val categories: Observable<Categories>
    val offers: Observable<Offers>
}