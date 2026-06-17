package com.pratik.composearchitecture.feature.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pratik.composearchitecture.core.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

/**
 * ViewModel for the Practice screen.
 *
 * It manages the UI state for the practice feature, handling search functionality
 * and data fetching from the [PracticeRepository].
 *
 * @property repository The repository used to fetch clinic data.
 * @author Pratik Behera
 */
@OptIn(FlowPreview::class)
@HiltViewModel
class PracticeViewModel @Inject constructor(private val repository: PracticeRepository) :
    ViewModel() {
    private var _uiState = MutableStateFlow(PracticeUiState(isLoading = true))

    /**
     * The UI state of the practice screen as a [StateFlow].
     */
    val uiState: StateFlow<PracticeUiState> = _uiState.asStateFlow()

    private var searchQuery = MutableStateFlow("")

    init {
        getAllClinics()
        observerSearchQuery()
    }

    /**
     * Observes the search query and filters the clinics list with a debounce.
     */
    private fun observerSearchQuery() {
        viewModelScope.launch {
            searchQuery
                .debounce(500.milliseconds)
                .distinctUntilChanged()
                .collectLatest { query ->
                    val filteredClinics = _uiState.value.clinics.filter { clinic ->
                        clinic.clinicName.contains(query, ignoreCase = true)
                    }
                    _uiState.update {
                        it.copy(
                            searchQuery = query,
                            filteredClinics = filteredClinics
                        )
                    }
                }
        }
    }

    /**
     * Fetches all clinics from the repository and updates the UI state.
     */
    private fun getAllClinics() {
        viewModelScope.launch {
            repository.getAllClinics().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = true,
                                error = null,
                            )
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = resource.message,
                            )
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = null,
                                clinics = resource.data,
                                filteredClinics = resource.data
                            )
                        }

                    }
                }
            }
        }
    }

    /**
     * Updates the search query, which triggers the filtering logic.
     *
     * @param query The new search query string.
     */
    fun updateSearchQuery(query: String) {
        searchQuery.value = query
        _uiState.update {
            it.copy(
                searchQuery = query,
            )
        }
    }

}
