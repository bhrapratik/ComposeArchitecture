package com.pratik.composearchitecture.feature.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratik.composearchitecture.core.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(private val notificationRepository: NotificationRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(NotificationUiModel(isLoading = true))
    val uiState: StateFlow<NotificationUiModel> = _uiState.asStateFlow()

    init {
        getAllNotification()
    }
    val unreadCount =
        notificationRepository
            .getUnReadCount()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = 0
            )

    fun getAllNotification() {
        viewModelScope.launch {
            notificationRepository.getAllNotification().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.value = NotificationUiModel(isLoading = true)
                    }

                    is Resource.Success -> {
                        _uiState.value =
                            NotificationUiModel(notifications = resource.data, isLoading = false)
                    }

                    is Resource.Error -> {
                        _uiState.value =
                            NotificationUiModel(error = resource.message, isLoading = false)
                    }
                }
            }
        }
    }

    fun markAsRead(notificationId: Long) {
        viewModelScope.launch {
            notificationRepository.markAsRead(notificationId)
        }
    }
}
