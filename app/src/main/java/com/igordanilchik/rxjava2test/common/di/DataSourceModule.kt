package com.igordanilchik.rxjava2test.common.di

import com.igordanilchik.rxjava2test.common.preferences.IAppPreferences
import com.igordanilchik.rxjava2test.data.source.local.ILocalDataSource
import com.igordanilchik.rxjava2test.data.source.local.LocalDataSource
import com.igordanilchik.rxjava2test.data.source.remote.IRemoteDataSource
import com.igordanilchik.rxjava2test.data.source.remote.RemoteDataSource
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