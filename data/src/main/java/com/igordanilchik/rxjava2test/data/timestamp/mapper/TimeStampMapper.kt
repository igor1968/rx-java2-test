package com.igordanilchik.rxjava2test.data.timestamp.mapper

import com.igordanilchik.rxjava2test.data.common.Mapper
import com.igordanilchik.rxjava2test.data.common.timestamp.TimeStamp
import com.igordanilchik.rxjava2test.data.timestamp.dto.TimeStampJson

/**
 * @author Igor Danilchik
 */
class TimeStampMapper : Mapper<TimeStampJson, TimeStamp> {

    override fun map(
        from: TimeStampJson
    ): TimeStamp = from.let {
        TimeStamp(
            it.key,
            it.timeInMillis
        )
    }
}