package com.pratik.corenetwork.api

import com.pratik.corenetwork.model.request.DeviceRegistrationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface NotificationApiService {

    @POST("notification/register")
    suspend fun registerToken(@Body request: DeviceRegistrationRequest)
}
