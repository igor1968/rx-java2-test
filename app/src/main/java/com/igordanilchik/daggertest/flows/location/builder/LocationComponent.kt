package com.igordanilchik.daggertest.flows.location.builder

import com.igordanilchik.daggertest.flows.location.presenter.LocationPresenter
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [LocationModule::class])
interface LocationComponent {
    fun presenter(): LocationPresenter
}