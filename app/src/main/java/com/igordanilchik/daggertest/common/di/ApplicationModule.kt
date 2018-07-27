package com.igordanilchik.daggertest.common.di

import android.app.Application
import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.igordanilchik.daggertest.common.factory.FragmentFactory
import com.igordanilchik.daggertest.common.factory.ObjectMapperFactory
import com.igordanilchik.daggertest.repo.SchedulersSet
import dagger.Module
import dagger.Provides
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Singleton

@Module
class ApplicationModule(val context: Application) {

    @Provides
    @Singleton
    internal fun provideContext(): Context = context.applicationContext

    @Provides
    @Singleton
    internal fun provideApplication(): Application = context

    @Provides
    @Singleton
    internal fun provideObjectMapper(): ObjectMapper = ObjectMapperFactory.newInstance()

    @Provides
    @Singleton
    internal fun provideFragmentFactory(): FragmentFactory = FragmentFactory

    @Provides
    @Singleton
    internal fun provideSchedulersSet(): SchedulersSet =
            SchedulersSet(
                    Schedulers.io(),
                    Schedulers.computation(),
                    AndroidSchedulers.mainThread(),
                    Schedulers.immediate()
            )

}
