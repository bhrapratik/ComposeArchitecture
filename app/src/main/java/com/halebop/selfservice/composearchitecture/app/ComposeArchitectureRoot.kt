package com.halebop.selfservice.composearchitecture.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.halebop.selfservice.composearchitecture.navigation.AppNavHost
import com.halebop.selfservice.composearchitecture.navigation.BottomBar

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

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }) { paddingValues ->
        AppNavHost(
            navController = navController, modifier = Modifier.padding(paddingValues)
        )
    }
}
