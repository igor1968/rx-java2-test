package com.igordanilchik.rxjava2test.data.source.remote

import com.igordanilchik.rxjava2test.dto.inner.Catalogue
import io.reactivex.Observable

/**
 * @author Igor Danilchik
 */
interface IRemoteDataSource {
    val catalogue: Observable<Catalogue>
}