package com.igordanilchik.rxjava2test.data.common.annotations

/**
 * @author Igor Danilchik
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class CapStringDef(
    vararg val value: String = []
)