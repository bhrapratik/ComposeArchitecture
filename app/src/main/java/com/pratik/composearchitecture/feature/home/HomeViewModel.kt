package com.pratik.composearchitecture.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratik.composearchitecture.core.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the Home screen.
 *
 * It manages the UI state for the home feature, handling data fetching from the [HomeRepository]
 * and exposing it as a [StateFlow] of [HomeUiState].
 *
 * @property homeRepository The repository used to fetch post data and handle synchronization.
 * @author Pratik Behera
 */
@HiltViewModel
class HomeViewModel
    @Inject
    constructor(private val homeRepository: HomeRepository) : ViewModel() {
        private val _uiState =
            MutableStateFlow(
                HomeUiState(
                    isLoading = true,
                ),
            )

        init {
            getPosts()
        }

        /**
         * The UI state of the home screen as a [StateFlow].
         * Observers can collect from this flow to receive updates on the screen's state.
         */
        val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

        /**
         * Fetches posts from the repository and updates the [_uiState] accordingly.
         * It handles success, error, and loading states emitted by the repository.
         * This method is called during initialization to start observing data.
         */
        private fun getPosts() {
            viewModelScope.launch {
                homeRepository.getPosts().collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _uiState.value =
                                _uiState.value.copy(
                                    isLoading = false,
                                    items = resource.data,
                                    error = null,
                                )
                        }

                        is Resource.Error -> {
                            _uiState.value =
                                _uiState.value.copy(
                                    isLoading = false,
                                    error = resource.message,
                                )
                        }

                        is Resource.Loading -> {
                            _uiState.value =
                                _uiState.value.copy(
                                    isLoading = true,
                                    error = null,
                                )
                        }
                    }
                }
            }
        }

        /**
         * Triggers a manual refresh of the posts from the remote data source.
         * Updates [HomeUiState.isRefreshing] to provide visual feedback during the operation.
         */
        fun refreshPosts() {
            viewModelScope.launch {
                _uiState.value =
                    _uiState.value.copy(
                        isRefreshing = true,
                        error = null,
                    )
                runCatching {
                    homeRepository.refreshPosts()
                }.onFailure { exception ->
                    _uiState.value =
                        _uiState.value.copy(
                            isRefreshing = false,
                            error = exception.message,
                        )
                }.onSuccess {
                    _uiState.value =
                        _uiState.value.copy(
                            isRefreshing = false,
                            error = null,
                        )
                }
            }
        }
    }
