package com.pratik.composearchitecture.feature.notification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pratik.composearchitecture.core.common.toFormattedDate
import com.pratik.composearchitecture.feature.notification.model.Notification
import javax.inject.Inject

/**
 * A screen that displays the user's profile information.
 *
 * Currently, this is a placeholder screen showing a simple label.
 *
 * @author Pratik Behera
 */
@Composable
fun NotificationScreen(viewModel: NotificationViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    NotificationScreenContent(uiState, onNotificationClick = viewModel::markAsRead)


}

@Composable
fun NotificationScreenContent(state: NotificationUiModel, onNotificationClick: (Long) -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.error != null -> {
                Text(text = "Error: ${state.error}")
            }

            else -> {
                NotificationList(state, onNotificationClick = onNotificationClick)
            }
        }
    }
}

@Composable
fun NotificationList(state: NotificationUiModel, onNotificationClick: (Long) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.notifications, key = { it.id }) { notification ->

            NotificationItem(notification, onNotificationClick = { onNotificationClick(it) })

        }
    }
}

@Composable
fun NotificationItem(
    notification: Notification,
    onNotificationClick: (Long) -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            )
            .clickable(onClick = { onNotificationClick(notification.id) })
    ) {

        Row(
            modifier = Modifier.padding(16.dp)
        ) {

            if (!notification.isRead) {

                Box(
                    modifier = Modifier
                        .size(10.dp)
                ) {
                    Badge()
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )
            }

            Column {

                Text(
                    text = notification.title,
                    fontWeight =
                        if (!notification.isRead) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = notification.body
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = notification.createdAt.toFormattedDate(),
                    style =
                        MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun EmptyInbox() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = "No notifications yet"
        )
    }
}
