package com.igordanilchik.rxjava2test.flows.location.builder

import android.app.Application
import android.content.Context
import com.igordanilchik.rxjava2test.flows.location.model.ILocationModel
import com.igordanilchik.rxjava2test.flows.location.model.LocationModel
import com.igordanilchik.rxjava2test.flows.location.presenter.LocationPresenter
import com.igordanilchik.rxjava2test.common.mvp.SchedulersSet
import com.vanniktech.rxpermission.RealRxPermission
import dagger.Module
import dagger.Provides
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider

/**
 * @author Igor Danilchik
 */
@Module
class LocationModule {

    @Provides
    fun presenter(
            schedulersSet: SchedulersSet,
            model: ILocationModel
    ): LocationPresenter = LocationPresenter(
            schedulersSet,
            model
    )

    @Provides
    fun model(
            locationProvider: ReactiveLocationProvider,
            permissionProvider: RealRxPermission
    ): ILocationModel =
            LocationModel(
                    locationProvider,
                    permissionProvider
            )

    @Provides
    fun locationProvider(context: Context): ReactiveLocationProvider =
            ReactiveLocationProvider(context)

    @Provides
    fun permissionProvider(application: Application): RealRxPermission =
            RealRxPermission.getInstance(application)

}