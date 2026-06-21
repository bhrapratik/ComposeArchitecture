package com.pratik.composearchitecture.feature.notification.model

data class Notification(
    val id: Long,
    val title: String,
    val body: String,
    val createdAt: Long,
    val isRead: Boolean,
)
