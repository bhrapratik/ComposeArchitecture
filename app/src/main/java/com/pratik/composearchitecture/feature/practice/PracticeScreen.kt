package com.pratik.composearchitecture.feature.practice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Entry point for the Practice screen.
 *
 * It initializes the [PracticeViewModel] and collects its state.
 *
 * @param viewModel The ViewModel that manages the state for this screen.
 * @author Pratik Behera
 */
@Composable
fun PracticeScreen(viewModel: PracticeViewModel = hiltViewModel()) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    PracticeScreenContent(state, onSearchQueryChange = viewModel::updateSearchQuery)

}

/**
 * The content of the Practice screen, displaying a loading indicator, error message,
 * or the list of clinics based on the state.
 *
 * @param state The current [PracticeUiState].
 * @param onSearchQueryChange Callback when the search query changes.
 */
@Composable
fun PracticeScreenContent(state: PracticeUiState, onSearchQueryChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text(text = "Error : ${state.error}")
            }

            else -> {
                ClinicList(state, onSearchQueryChange = onSearchQueryChange)
            }
        }

    }
}

/**
 * Displays a list of clinics with a search bar at the top.
 *
 * @param state The current [PracticeUiState].
 * @param onSearchQueryChange Callback when the search query changes.
 */
@Composable
fun ClinicList(state: PracticeUiState, onSearchQueryChange: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            OutlinedTextField(
                onValueChange = onSearchQueryChange,
                value = state.searchQuery,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Search") })
        }
        items(state.filteredClinics, key = { it.id }) { clinic ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics(mergeDescendants = true) {}) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = clinic.clinicName, style = MaterialTheme.typography.titleMedium)
                        if (clinic.isUrgent) {
                            SuggestionChip(
                                onClick = {}, label = {
                                    Text(
                                        text = "Urgent",
                                        style = MaterialTheme.typography.labelSmall
                                    )
                                },
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = MaterialTheme.colorScheme.errorContainer,
                                    labelColor = MaterialTheme.colorScheme.error
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Distance ${clinic.distanceMeters} meters",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
