package com.igordanilchik.rxjava2test.data.catalogue.mapper

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.xml.Catalogue
import com.igordanilchik.rxjava2test.data.common.Mapper
import com.igordanilchik.rxjava2test.data.common.api.ClientApi

/**
 * @author Igor Danilchik
 */
class CatalogueToOfferEntityMapper: Mapper<Catalogue, List<OfferEntity>> {

    override fun map(from: Catalogue): List<OfferEntity> {
        return from.let {catalogue ->
            catalogue.shop.offers.map innerMap@{ offer ->
                val params = offer.param?.map {
                    OfferEntity.Property(
                        name = it.key,
                        value = it.value
                    )
                }

                return@innerMap OfferEntity(
                    id = offer.id,
                    url = offer.url,
                    name = offer.name,
                    price = offer.price,
                    description = offer.description,
                    categoryId = offer.categoryId,
                    pictureUrl = fixUrl(offer.pictureUrl),
                    properties = params
                )
            }
        }
    }

    private fun fixUrl(pictureUrl: String?): String? = pictureUrl?.replace(ClientApi.API_BASE_URL, "")
}