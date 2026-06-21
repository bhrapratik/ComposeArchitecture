package com.pratik.composearchitecture.feature.notification

import com.pratik.composearchitecture.feature.notification.model.Notification

data class NotificationUiModel(
    val isLoading: Boolean = false,
    val notifications: List<Notification> = emptyList(),
    val error: String? = null
)
