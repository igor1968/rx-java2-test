package com.igordanilchik.daggertest.flows.location.model

import android.location.Location
import com.vanniktech.rxpermission.Permission
import rx.Observable

/**
 * @author Igor Danilchik
 */
interface ILocationModel {
    val lastKnownLocation: Observable<Location>
    val updatableLocation: Observable<Location>
    fun getAddress(location: Location): Observable<String>
    fun requestPermissions(): Observable<Permission>
}