package com.pratik.composearchitecture.app

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.pratik.composearchitecture.core.ui.theme.ComposeArchitectureTheme
import com.pratik.corenetwork.api.NotificationApiService
import com.pratik.corenetwork.model.request.DeviceRegistrationRequest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * The main entry point for the application.
 *
 * This activity sets up the Compose theme and hosts the main application composable.
 *
 * @author Pratik Behera
 */


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var notificationApiService: NotificationApiService

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

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            Log.d("FCM", "TOKEN = $token")
            CoroutineScope(Dispatchers.IO).launch {
                runCatching {
                    notificationApiService.registerToken(DeviceRegistrationRequest(fcmToken = token))
                } .onFailure { ex ->
                    Log.e("FCM", "Failed to register token", ex)
                }
            }
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
