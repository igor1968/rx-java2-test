package com.igordanilchik.daggertest.api


import com.igordanilchik.daggertest.dto.inner.Catalogue

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ClientApi {

    @GET("/getyml")
    fun loadCatalogue(@Query("key") key: String): Observable<Catalogue>

    companion object {

        val API_BASE_URL = "http://ufa.farfor.ru"
        val API_KEY = "ukAXxeJYZN"
    }
}
