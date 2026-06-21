package com.pratik.corenetwork.model.request

data class DeviceRegistrationRequest(
    val userId: String = "user123",
    val fcmToken : String
)
