package com.igordanilchik.rxjava2test.common.factory

import android.os.Bundle
import com.igordanilchik.rxjava2test.flows.catalogue.view.CatalogueFragment
import com.igordanilchik.rxjava2test.flows.location.view.LocationFragment
import com.igordanilchik.rxjava2test.flows.offer.view.OfferFragment
import com.igordanilchik.rxjava2test.flows.offers.view.OffersFragment

/**
 * @author Igor Danilchik
 */
object FragmentFactory {

    fun location(): LocationFragment = LocationFragment()

    fun categories(): CatalogueFragment =  CatalogueFragment()

    fun offers(bundle: Bundle): OffersFragment = OffersFragment().apply { arguments = bundle }

    fun offer(bundle: Bundle): OfferFragment = OfferFragment().apply { arguments = bundle }

}