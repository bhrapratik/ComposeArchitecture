package com.pratik.composearchitecture.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.pratik.composearchitecture.feature.notification.NotificationViewModel
import com.pratik.composearchitecture.navigation.AppNavHost
import com.pratik.composearchitecture.navigation.BottomBar

/**
 * The root composable for the application's UI.
 *
 * It sets up the [Scaffold] with a bottom navigation bar and the main navigation host.
 *
 * @author Pratik Behera
 */
@Composable
fun ComposeArchitectureRoot() {
    val navController = rememberNavController()

    val notificationViewModel: NotificationViewModel =
        hiltViewModel()

    val unreadCount by notificationViewModel.unreadCount
        .collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, unreadCount = unreadCount)
        },
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
        )
    }
}
