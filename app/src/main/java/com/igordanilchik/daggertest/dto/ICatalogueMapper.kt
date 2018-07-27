package com.igordanilchik.daggertest.dto

import com.igordanilchik.daggertest.data.Categories
import com.igordanilchik.daggertest.data.Offers
import com.igordanilchik.daggertest.dto.inner.Catalogue

/**
 * @author Igor Danilchik
 */
interface ICatalogueMapper {
    fun mapToCategories(catalogue: Catalogue): Categories
    fun mapToOffers(catalogue: Catalogue): Offers
}