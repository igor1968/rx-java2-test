package com.igordanilchik.rxjava2test.data.common

/**
 * @author Igor Danilchik
 */
interface Mapper<F, T> {
    fun map(from: F): T
}