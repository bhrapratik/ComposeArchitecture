package com.halebop.selfservice.composearchitecture.feature.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel for the [HomeScreen].
 *
 * It manages the UI state for the home feature, including the list of items
 * to be displayed.
 *
 * @author Pratik Behera
 */
class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            items = listOf(
                "Jetpack Compose",
                "MVVM Architecture",
                "StateFlow",
                "Navigation Compose",
                "Clean Architecture"
            )
        )
    )
    
    /**
     * The UI state of the home screen as a [StateFlow].
     */
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}
