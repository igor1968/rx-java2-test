package com.igordanilchik.rxjava2test.flows.offers.model

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity

/**
 * @author Igor Danilchik
 */
data class Subcategory(
    val meals: List<OfferEntity>,
    val categoryId: Int,
    val categoryName: String?
)
