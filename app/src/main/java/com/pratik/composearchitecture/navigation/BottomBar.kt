package com.pratik.composearchitecture.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue

/**
 * A bottom navigation bar that allows users to switch between main sections of the app.
 *
 * @param navController The navigation controller used to handle navigation between sections.
 * @author Pratik Behera
 */
@Composable
fun BottomBar(
    navController: NavHostController
) {
    val items = listOf(
        Screen.Home,
        Screen.Profile
    )

    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->

            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                },
                icon = {
                    when (screen) {
                        Screen.Home -> {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home"
                            )
                        }

                        Screen.Profile -> {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile"
                            )
                        }

                        else -> {}
                    }
                },
                label = {
                    Text(text = screen.route)
                }
            )
        }
    }
}
