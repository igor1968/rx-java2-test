package com.igordanilchik.rxjava2test.common.di

import com.igordanilchik.rxjava2test.data.source.IRepository
import com.igordanilchik.rxjava2test.data.source.Repository
import com.igordanilchik.rxjava2test.data.source.local.ILocalDataSource
import com.igordanilchik.rxjava2test.data.source.remote.IRemoteDataSource
import com.igordanilchik.rxjava2test.dto.ICatalogueMapper
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
