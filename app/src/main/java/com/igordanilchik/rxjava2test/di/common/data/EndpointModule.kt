package com.igordanilchik.rxjava2test.di.common.data

import com.igordanilchik.rxjava2test.data.service.DefaultEndpointProvider
import com.igordanilchik.rxjava2test.data.service.EndpointProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Igor Danilchik
 */
@Module
object EndpointModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideEndpoint(): EndpointProvider = DefaultEndpointProvider()
}