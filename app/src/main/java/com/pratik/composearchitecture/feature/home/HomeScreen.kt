package com.pratik.composearchitecture.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

/**
 * The primary screen for displaying a list of items.
 *
 * This composable observes the state from [HomeViewModel] and displays a loading
 * indicator or a list of items based on the current state.
 *
 * @param onEvent A callback to handle UI events such as item clicks.
 * @param viewModel The ViewModel that manages the state for this screen.
 * @author Pratik Behera
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onEvent: (HomeUiEvent) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isRefreshing,
        onRefresh = {
            viewModel.refreshPosts()
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        when {

            uiState.isLoading -> {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CircularProgressIndicator()
                }
            }

            uiState.error != null -> {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = uiState.error ?: "Unknown Error"
                    )
                    Button(
                        enabled = !uiState.isRefreshing,
                        onClick = {
                            viewModel.refreshPosts()
                        }
                    ) {
                        Text(text = "Retry")
                    }
                }
            }

            else -> {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {


                    items(uiState.items) { item ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            onClick = {
                                onEvent(HomeUiEvent.OpenDetails(item.title))
                            }
                        ) {

                            Text(
                                text = item.title,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                }
                PullRefreshIndicator(
                    refreshing = uiState.isRefreshing,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}
