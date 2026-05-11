package com.pratik.composearchitecture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pratik.composearchitecture.feature.details.DetailsScreen
import com.pratik.composearchitecture.feature.home.HomeScreen
import com.pratik.composearchitecture.feature.home.HomeUiEvent
import com.pratik.composearchitecture.feature.profile.ProfileScreen

/**
 * The main navigation host for the application.
 *
 * This composable manages the navigation graph and defines the destinations
 * available in the app. It uses [NavHost] to handle screen transitions based
 * on the current route.
 *
 * @param navController The navigation controller used to manage app navigation.
 * @param modifier The modifier to be applied to the NavHost.
 * @author Pratik Behera
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onEvent = { event ->
                    when (event) {
                        is HomeUiEvent.OpenDetails -> {
                            navController.navigate(
                                Screen.Details.createRoute(event.item)
                            )
                        }
                    }
                }
            )
        }
        composable(Screen.Details.route) { backStackEntry ->
            val item = backStackEntry.arguments
                ?.getString("item")
                .orEmpty()
            DetailsScreen(item = item)
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
