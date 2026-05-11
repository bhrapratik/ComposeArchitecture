package com.pratik.composearchitecture.navigation

/**
 * Represents the different screens/destinations in the application.
 *
 * Each screen has a unique [route] used by the Navigation component.
 *
 * @author Pratik Behera
 */
sealed class Screen(val route: String) {

    /**
     * The home screen destination.
     */
    data object Home : Screen("home")

    /**
     * The profile screen destination.
     */
    data object Profile : Screen("profile")

    /**
     * The details screen destination, which requires an [item] parameter.
     */
    data object Details : Screen("details/{item}") {
        /**
         * Creates a navigation route for the Details screen with the provided [item].
         */
        fun createRoute(item: String): String {
            return "details/$item"
        }
    }
}
