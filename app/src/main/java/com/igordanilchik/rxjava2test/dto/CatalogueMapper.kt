package com.igordanilchik.rxjava2test.dto

import com.igordanilchik.rxjava2test.data.Categories
import com.igordanilchik.rxjava2test.data.Offers
import com.igordanilchik.rxjava2test.dto.inner.Catalogue

/**
 * @author Igor Danilchik
 */
class CatalogueMapper : ICatalogueMapper {

    override fun mapToCategories(catalogue: Catalogue): Categories =
        Categories(
            catalogue.shop.categories.map { category ->
                val url = catalogue.shop.offers.find { offer ->
                    offer.categoryId == category.id && offer.pictureUrl != null
                }?.pictureUrl

                return@map Categories.Category(
                    id = category.id,
                    name = category.title,
                    pictureUrl = fixUrl(url)
                )
            }
        )

    override fun mapToOffers(catalogue: Catalogue): Offers =
        Offers(
            catalogue.shop.offers.map { offer ->
                val params = offer.param?.map { Offers.Offer.Param(name = it.key, value = it.value) }

                return@map Offers.Offer(
                    id = offer.id,
                    url = offer.url,
                    name = offer.name,
                    price = offer.price,
                    description = offer.description,
                    categoryId = offer.categoryId,
                    picture = fixUrl(offer.pictureUrl),
                    param = params
                )
            }
        )

    private fun fixUrl(pictureUrl: String?): String? = pictureUrl?.replace("http://ufa.farfor.ru", "")
}