package com.igordanilchik.rxjava2test.data.timestamp.local

import com.igordanilchik.rxjava2test.data.common.timestamp.TimeStamp

/**
 * @author Igor Danilchik
 */
interface TimeStampLocalStorage {
    fun save(timeStamp: TimeStamp)
    fun get(key: String): TimeStamp?
    fun removeAll()
    fun remove(timeStamp: TimeStamp)
    fun remove(key: String)
    fun removeAllKeys(prefix: String)
}
