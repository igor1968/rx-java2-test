package com.igordanilchik.rxjava2test.data.common.timestamp

import com.igordanilchik.rxjava2test.data.common.Constants

/**
 * @author Igor Danilchik
 */
data class TimeStamp(
    @Constants.TimeStampType val key: String,
    val timeInMillis: Long = TimeStampUtils.current
)