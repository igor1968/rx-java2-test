package com.igordanilchik.rxjava2test.flows.location.builder

import android.app.Application
import android.content.Context
import com.igordanilchik.rxjava2test.common.mvp.SchedulersSet
import com.igordanilchik.rxjava2test.flows.location.model.ILocationModel
import com.igordanilchik.rxjava2test.flows.location.model.LocationModel
import com.igordanilchik.rxjava2test.flows.location.presenter.LocationPresenter
import com.vanniktech.rxpermission.RealRxPermission
import dagger.Module
import dagger.Provides
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider

/**
 * @author Igor Danilchik
 */
@Module
object LocationModule {

    @JvmStatic
    @Provides
    fun presenter(
        schedulersSet: SchedulersSet,
        model: ILocationModel
    ): LocationPresenter = LocationPresenter(
        schedulersSet,
        model
    )

    @JvmStatic
    @Provides
    fun model(
        locationProvider: ReactiveLocationProvider,
        permissionProvider: RealRxPermission
    ): ILocationModel =
        LocationModel(
            locationProvider,
            permissionProvider
        )

    @JvmStatic
    @Provides
    fun locationProvider(context: Context): ReactiveLocationProvider =
        ReactiveLocationProvider(context)

    @JvmStatic
    @Provides
    fun permissionProvider(application: Application): RealRxPermission =
        RealRxPermission.getInstance(application)
}