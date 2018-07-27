package com.igordanilchik.daggertest.common.di

import com.igordanilchik.daggertest.dto.CatalogueMapper
import com.igordanilchik.daggertest.dto.ICatalogueMapper
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