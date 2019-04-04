package com.igordanilchik.rxjava2test.data.timestamp.mapper

import com.igordanilchik.rxjava2test.data.common.Mapper
import com.igordanilchik.rxjava2test.data.common.timestamp.TimeStamp
import com.igordanilchik.rxjava2test.data.timestamp.dto.TimeStampJson

/**
 * @author Igor Danilchik
 */
class TimeStampJsonMapper : Mapper<TimeStamp, TimeStampJson> {

    override fun map(
        from: TimeStamp
    ): TimeStampJson = from.let {
        TimeStampJson(
            it.key,
            it.timeInMillis
        )
    }
}