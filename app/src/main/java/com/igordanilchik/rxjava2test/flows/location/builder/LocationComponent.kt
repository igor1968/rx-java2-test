package com.igordanilchik.rxjava2test.flows.location.builder

import com.igordanilchik.rxjava2test.flows.location.presenter.LocationPresenter
import dagger.Subcomponent

/**
 * @author Igor Danilchik
 */
@Subcomponent(modules = [LocationModule::class])
interface LocationComponent {
    fun presenter(): LocationPresenter
}