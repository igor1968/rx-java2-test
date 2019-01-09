package com.igordanilchik.rxjava2test.data

import com.fasterxml.jackson.annotation.JsonProperty

data class Offers(
    @JsonProperty("meals") val meals: List<Meal>
) {
    data class Meal(
            @JsonProperty("id") val id: Int,
            @JsonProperty("url") val url: String,
            @JsonProperty("name") val name: String,
            @JsonProperty("price") val price: String,
            @JsonProperty("description") val description: String?,
            @JsonProperty("pictureUrl") val pictureUrl: String?,
            @JsonProperty("categoryId") val categoryId: Int,
            @JsonProperty("parameters") val parameters: List<Param>?
    ) {
        data class Param(
            @JsonProperty("name") val name: String,
            @JsonProperty("value") val value: String
        )
    }
}

fun Offers.Meal.getParamByKey(key: String): String? = this.parameters?.find { it.name == key }?.value