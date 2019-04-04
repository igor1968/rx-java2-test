package com.igordanilchik.rxjava2test.flows.location.view

import android.location.Location
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.igordanilchik.rxjava2test.common.mvp.view.AppBaseView
import com.igordanilchik.rxjava2test.flows.location.router.LocationRouter

/**
 * @author Igor Danilchik
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface LocationView : AppBaseView, LocationRouter {
    fun requestMap()
    fun showError(e: Throwable)
    fun updateMap(location: Location, address: String)
//    fun requestPermissions()
}