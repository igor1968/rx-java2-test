package com.igordanilchik.rxjava2test.di.feature.timestamp

import com.igordanilchik.rxjava2test.data.common.Mapper
import com.igordanilchik.rxjava2test.data.common.prefs.AppPreferences
import com.igordanilchik.rxjava2test.data.common.timestamp.TimeStamp
import com.igordanilchik.rxjava2test.data.timestamp.dto.TimeStampJson
import com.igordanilchik.rxjava2test.data.timestamp.local.PreferenceTimeStampStorage
import com.igordanilchik.rxjava2test.data.timestamp.local.TimeStampLocalStorage
import com.igordanilchik.rxjava2test.di.common.Private
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module(includes = [TimeStampMapperModule::class])
object DefaultTimeStampModule {

    @Private
    @JvmStatic
    @Provides
    fun provideTimeStampStorage(
        appPreference: AppPreferences,
        timeStampMapper: Mapper<TimeStampJson, TimeStamp>,
        timeStampJsonMapper: Mapper<TimeStamp, TimeStampJson>
    ): TimeStampLocalStorage =
        PreferenceTimeStampStorage(
            appPreference,
            timeStampMapper,
            timeStampJsonMapper
        )
}