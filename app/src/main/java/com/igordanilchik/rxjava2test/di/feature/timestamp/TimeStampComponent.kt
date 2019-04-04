package com.igordanilchik.rxjava2test.di.feature.timestamp

import com.igordanilchik.rxjava2test.data.timestamp.local.TimeStampLocalStorage
import com.igordanilchik.rxjava2test.di.common.Private
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [DefaultTimeStampModule::class])
interface TimeStampComponent {

    @Private
    fun storage(): TimeStampLocalStorage

    @Subcomponent.Builder
    interface Builder {
        fun build(): TimeStampComponent
    }
}