package com.igordanilchik.rxjava2test.data.catalogue.mapper

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.json.OfferJson
import com.igordanilchik.rxjava2test.data.common.Mapper

/**
 * @author Igor Danilchik
 */
class OfferEntityJsonMapper : Mapper<OfferEntity, OfferJson> {

    override fun map(from: OfferEntity): OfferJson = from.let {
        OfferJson(
            id = it.id,
            url = it.url,
            price = it.price,
            name = it.name,
            description = it.description,
            categoryId = it.categoryId,
            pictureUrl = it.pictureUrl,
            parameters = from.properties?.map { param ->
                OfferJson.Param(name = param.name, value = param.value)
            }
        )
    }
}