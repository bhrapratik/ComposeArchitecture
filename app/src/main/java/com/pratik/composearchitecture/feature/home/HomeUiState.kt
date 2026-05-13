package com.pratik.composearchitecture.feature.home

import com.pratik.composearchitecture.feature.home.domain.model.Post

/**
 * Represents the UI state for the Home screen.
 *
 * @property isLoading Indicates if the screen is currently loading data.
 * @property items The list of posts to be displayed on the screen.
 * @property error An optional error message to be displayed if data loading fails.
 * @author Pratik Behera
 */
data class HomeUiState(
    val isLoading: Boolean = false,
    val items: List<Post> = emptyList(),
    val error: String? = null
)
