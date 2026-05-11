package com.halebop.selfservice.composearchitecture.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for the [HomeScreen].
 *
 * It manages the UI state for the home feature, including the list of items
 * to be displayed.
 *
 * @author Pratik Behera
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(
        HomeUiState(
            isLoading = true,
        )
    )
    init {
        loadItems()
    }

    /**
     * The UI state of the home screen as a [StateFlow].
     */
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private fun loadItems() {
        viewModelScope.launch {
            val items = homeRepository.getItems()
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                items = items
            )
        }
    }
}
