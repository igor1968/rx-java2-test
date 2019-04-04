package com.igordanilchik.rxjava2test.data.common.annotations

/**
 * @author Igor Danilchik
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class CapByteDef(
    vararg val value: Byte = [],
    val flag: Boolean = false
)