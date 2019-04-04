package com.igordanilchik.rxjava2test.data.catalogue.mapper

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.xml.Catalogue
import com.igordanilchik.rxjava2test.data.common.Mapper
import com.igordanilchik.rxjava2test.data.common.api.ClientApi

/**
 * @author Igor Danilchik
 */
class CatalogueToCategoryEntityMapper : Mapper<Catalogue, List<CategoryEntity>> {

    override fun map(from: Catalogue): List<CategoryEntity> = from.let { catalogue ->
        catalogue.shop.categories.map innerMap@{ category ->
            val url = catalogue.shop.offers.find { offer ->
                offer.categoryId == category.id && offer.pictureUrl != null
            }?.pictureUrl

            return@innerMap CategoryEntity(
                id = category.id,
                name = category.title,
                pictureUrl = fixUrl(url)
            )
        }
    }

    private fun fixUrl(pictureUrl: String?): String? = pictureUrl?.replace(ClientApi.API_BASE_URL, "")
}