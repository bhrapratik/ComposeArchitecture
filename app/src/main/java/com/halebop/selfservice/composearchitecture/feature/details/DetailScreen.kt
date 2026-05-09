package com.halebop.selfservice.composearchitecture.feature.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A screen that displays details for a specific item.
 *
 * @param item The identifier or name of the item to display.
 * @author Pratik Behera
 */
@Composable
fun DetailsScreen(
    item: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Selected Item")
        Text(text = item)
    }
}
