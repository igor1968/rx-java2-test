package com.igordanilchik.rxjava2test.data.source

import com.igordanilchik.rxjava2test.data.Categories
import com.igordanilchik.rxjava2test.data.Offers
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface IRepository {
    val categories: Observable<Categories>
    val offers: Observable<Offers>
//    fun subcategories(categoryId: Int): Observable<Subcategory>
}