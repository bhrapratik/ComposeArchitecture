package com.halebop.selfservice.composearchitecture.feature.home

/**
 * Represents the UI state for the Home screen.
 *
 * @property isLoading Indicates if the screen is currently loading data.
 * @property title The title to be displayed on the screen.
 * @property items The list of strings to be displayed in the list.
 * @author Pratik Behera
 */
data class HomeUiState(
    val isLoading: Boolean = false,
    val title: String = "Home Screen",
    val items: List<String> = emptyList()
)
