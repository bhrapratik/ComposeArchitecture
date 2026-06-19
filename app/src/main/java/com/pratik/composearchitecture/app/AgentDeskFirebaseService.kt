package com.pratik.composearchitecture.app

import android.Manifest
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.pratik.composearchitecture.R


class AgentDeskFirebaseService : FirebaseMessagingService() {


    @Deprecated("Deprecated in Java")
    override fun onNewToken(token: String) {
        Log.d("FCM", "TOKEN = $token")
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onMessageReceived(message: RemoteMessage) {

        Log.d("FCM", "Title=${message.notification?.title}")
        Log.d("FCM", "Body=${message.notification?.body}")

        val title =
            message.notification?.title ?: "Notification"

        val body =
            message.notification?.body ?: ""

        showNotification(
            title = title,
            body = body
        )
    }

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun showNotification(
        title: String,
        body: String
    ) {
        // Open app when tapping notification

        val intent = Intent(this, MainActivity::class.java)

        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )

        val notification = NotificationCompat.Builder(this, "Notification")
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(this).notify(1, notification)


    }

}
