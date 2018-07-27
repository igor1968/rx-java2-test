package com.igordanilchik.daggertest.common.di

import com.igordanilchik.daggertest.common.preferences.IAppPreferences
import com.igordanilchik.daggertest.data.source.local.ILocalDataSource
import com.igordanilchik.daggertest.data.source.local.LocalDataSource
import com.igordanilchik.daggertest.data.source.remote.IRemoteDataSource
import com.igordanilchik.daggertest.data.source.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

/**
 * @author Igor Danilchik
 */
@Module
class DataSourceModule {

    @Provides
    internal fun local(appPreferences: IAppPreferences): ILocalDataSource = LocalDataSource(appPreferences)

    @Provides
    internal fun remote(httpClient: OkHttpClient): IRemoteDataSource = RemoteDataSource(httpClient)

}