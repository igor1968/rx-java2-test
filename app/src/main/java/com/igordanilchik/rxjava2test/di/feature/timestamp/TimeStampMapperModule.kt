package com.igordanilchik.rxjava2test.di.feature.timestamp

import com.igordanilchik.rxjava2test.data.common.Mapper
import com.igordanilchik.rxjava2test.data.common.timestamp.TimeStamp
import com.igordanilchik.rxjava2test.data.timestamp.dto.TimeStampJson
import com.igordanilchik.rxjava2test.data.timestamp.mapper.TimeStampJsonMapper
import com.igordanilchik.rxjava2test.data.timestamp.mapper.TimeStampMapper
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module
object TimeStampMapperModule {

    @JvmStatic
    @Provides
    fun provideTimeStampMapper(): Mapper<TimeStampJson, TimeStamp> = TimeStampMapper()

    @JvmStatic
    @Provides
    fun provideTimeStampJsonMapper(): Mapper<TimeStamp, TimeStampJson> = TimeStampJsonMapper()
}