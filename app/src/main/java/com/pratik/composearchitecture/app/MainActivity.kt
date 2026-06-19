package com.pratik.composearchitecture.app

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.messaging.FirebaseMessaging
import com.pratik.composearchitecture.core.ui.theme.ComposeArchitectureTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main entry point for the application.
 *
 * This activity sets up the Compose theme and hosts the main application composable.
 *
 * @author Pratik Behera
 */


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val notificationPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNotification()
        enableEdgeToEdge()
        setContent {
            ComposeArchitectureTheme {
                ComposeArchitectureRoot()
            }
        }
        FirebaseMessaging.getInstance()
            .token
            .addOnSuccessListener { token ->
                Log.d("FCM", "TOKEN = $token")
            }
    }

    private fun setupNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            notificationPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }
    }
}
