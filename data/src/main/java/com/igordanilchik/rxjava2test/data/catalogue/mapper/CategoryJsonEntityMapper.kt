package com.igordanilchik.rxjava2test.data.catalogue.mapper

import com.igordanilchik.rxjava2test.data.catalogue.dto.entity.CategoryEntity
import com.igordanilchik.rxjava2test.data.catalogue.dto.json.CategoryJson
import com.igordanilchik.rxjava2test.data.common.Mapper

/**
 * @author Igor Danilchik
 */
class CategoryJsonEntityMapper : Mapper<CategoryJson, CategoryEntity> {

    override fun map(from: CategoryJson): CategoryEntity = from.let {
        CategoryEntity(
            id = it.id,
            name = it.name,
            pictureUrl = it.pictureUrl
        )
    }
}