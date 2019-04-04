package com.igordanilchik.rxjava2test.data.catalogue.mapper

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.OfferEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.json.OfferJson
import com.igordanilchik.rxjava2test.data.common.Mapper

/**
 * @author Igor Danilchik
 */
class OfferJsonEntityMapper : Mapper<OfferJson, OfferEntity> {

    override fun map(from: OfferJson): OfferEntity = from.let {
        OfferEntity(
            id = it.id,
            url = it.url,
            price = it.price,
            name = it.name,
            description = it.description,
            categoryId = it.categoryId,
            pictureUrl = it.pictureUrl,
            properties = from.parameters?.map { param ->
                OfferEntity.Property(name = param.name, value = param.value)
            }
        )
    }
}