package com.igordanilchik.rxjava2test.di.common.data

import com.igordanilchik.rxjava2test.data.common.KeyValueFactory
import dagger.Lazy

/**
 * @author Igor Danilchik
 */
class BehaviorFactory<T>(
    private val behaviorMapLazy: Lazy<Map<Int, T>>
) : KeyValueFactory<Int, T> {

    override fun getInstance(key: Int): T =
        behaviorMapLazy.get()[key] ?: throw NoSuchElementException("Behavior with type $key not found")
}