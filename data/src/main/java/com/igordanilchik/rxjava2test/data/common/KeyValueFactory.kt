package com.igordanilchik.rxjava2test.data.common

/**
 * @author Igor Danilchik
 */
interface KeyValueFactory<KEY, VALUE> {
    fun getInstance(key: KEY): VALUE
}