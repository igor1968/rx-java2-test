package com.igordanilchik.rxjava2test.common.di

import com.igordanilchik.rxjava2test.dto.CatalogueMapper
import com.igordanilchik.rxjava2test.dto.ICatalogueMapper
import dagger.Module
import dagger.Provides

/**
 * @author Igor Danilchik
 */
@Module
class MapperModule {

    @Provides
    fun mapper(): ICatalogueMapper = CatalogueMapper()

}