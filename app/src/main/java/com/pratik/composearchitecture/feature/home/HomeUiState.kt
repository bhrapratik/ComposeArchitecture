package com.pratik.composearchitecture.feature.home

/**
 * Represents the UI state for the Home screen.
 *
 * @property isLoading Indicates if the screen is currently loading data.
 * @property items The list of strings to be displayed in the list.
 * @property error An optional error message to be displayed if data loading fails.
 * @author Pratik Behera
 */
data class HomeUiState(
    val isLoading: Boolean = false,
    val items: List<String> = emptyList(),
    val error: String? = null
)
