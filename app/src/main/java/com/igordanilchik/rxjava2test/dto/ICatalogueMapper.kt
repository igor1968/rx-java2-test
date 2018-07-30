package com.igordanilchik.rxjava2test.dto

import com.igordanilchik.rxjava2test.data.Categories
import com.igordanilchik.rxjava2test.data.Offers
import com.igordanilchik.rxjava2test.dto.inner.Catalogue

/**
 * @author Igor Danilchik
 */
interface ICatalogueMapper {
    fun mapToCategories(catalogue: Catalogue): Categories
    fun mapToOffers(catalogue: Catalogue): Offers
}