package com.igordanilchik.rxjava2test.data.catalogue.behavior

import com.igordanilchik.rxjava2test.data.catalogue.local.CatalogueLocalStorage
import com.igordanilchik.rxjava2test.data.common.Constants.TimeStampType
import com.igordanilchik.rxjava2test.data.timestamp.local.TimeStampLocalStorage

/**
 * @author Igor Danilchik
 */
class CatalogueClearingCacheBehavior(
    private val catalogueLocalStorage: CatalogueLocalStorage,
    private val timeStampLocalStorage: TimeStampLocalStorage
) : CatalogueClearingBehavior {

    override fun clear() {
        catalogueLocalStorage.clear()
        timeStampLocalStorage.removeAllKeys(TimeStampType.CATLOGUE)
    }
}