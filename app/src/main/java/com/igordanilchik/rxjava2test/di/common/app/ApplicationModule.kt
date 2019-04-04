package com.igordanilchik.rxjava2test.di.common.app

import android.app.Application
import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.igordanilchik.rxjava2test.common.factory.FragmentFactory
import com.igordanilchik.rxjava2test.common.factory.ObjectMapperFactory
import com.igordanilchik.rxjava2test.common.mvp.SchedulersSet
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
            Schedulers.trampoline()
        )

}
