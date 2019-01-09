package com.igordanilchik.rxjava2test.flows.offers.model

import com.igordanilchik.rxjava2test.data.Offers

/**
 * @author Igor Danilchik
 */
data class Subcategory(
    val meals: List<Offers.Meal>,
    val categoryId: Int,
    val categoryName: String?
)
