package com.igordanilchik.daggertest.common.di

import com.igordanilchik.daggertest.data.source.IRepository
import com.igordanilchik.daggertest.data.source.Repository
import com.igordanilchik.daggertest.data.source.local.ILocalDataSource
import com.igordanilchik.daggertest.data.source.remote.IRemoteDataSource
import com.igordanilchik.daggertest.dto.ICatalogueMapper
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    internal fun repository(
            localDataSource: ILocalDataSource,
            remoteDataSource: IRemoteDataSource,
            mapper: ICatalogueMapper
    ): IRepository =
            Repository(
                    localDataSource,
                    remoteDataSource,
                    mapper
            )
}
