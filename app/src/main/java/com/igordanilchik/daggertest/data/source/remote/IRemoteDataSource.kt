package com.igordanilchik.daggertest.data.source.remote

import com.igordanilchik.daggertest.dto.inner.Catalogue
import rx.Observable

/**
 * @author Igor Danilchik
 */
interface IRemoteDataSource {
    val catalogue: Observable<Catalogue>
}