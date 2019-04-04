package com.igordanilchik.rxjava2test.data.service

import com.igordanilchik.rxjava2test.data.common.api.ClientApi

/**
 * @author Igor Danilchik
 */
class DefaultEndpointProvider: EndpointProvider {

    override fun shopEndpoint(): String = ClientApi.API_BASE_URL + ClientApi.API_BASE_PATH

}