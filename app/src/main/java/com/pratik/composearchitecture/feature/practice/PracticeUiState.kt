package com.pratik.composearchitecture.feature.practice

import com.pratik.composearchitecture.feature.practice.model.Clinic

/**
 * UI State for the Practice screen.
 *
 * @property searchQuery The current search query string.
 * @property isLoading Indicates if data is currently being loaded.
 * @property error An optional error message if loading fails.
 * @property clinics The full list of clinics fetched from the repository.
 * @property filteredClinics The list of clinics after applying the search filter.
 * @author Pratik Behera
 */
data class PracticeUiState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val clinics: List<Clinic> = emptyList(),
    val filteredClinics: List<Clinic> = emptyList()
)
