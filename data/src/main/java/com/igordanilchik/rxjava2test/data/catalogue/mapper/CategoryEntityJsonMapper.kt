package com.igordanilchik.rxjava2test.data.catalogue.mapper

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.json.CategoryJson
import com.igordanilchik.rxjava2test.data.common.Mapper

/**
 * @author Igor Danilchik
 */
class CategoryEntityJsonMapper : Mapper<CategoryEntity, CategoryJson> {

    override fun map(from: CategoryEntity): CategoryJson = from.let {
        CategoryJson(
            id = it.id,
            name = it.name,
            pictureUrl = it.pictureUrl
        )
    }
}