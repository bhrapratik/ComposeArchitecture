package com.halebop.selfservice.composearchitecture.feature.home

import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeRepository @Inject constructor() {
        /**
        * Simulates fetching a list of items from a data source.
        *
        * @return A list of strings representing the items.
        */
        suspend fun getItems(): List<String> {
            delay(2000)
            // In a real application, this would fetch data from a database or network.
            return listOf(
                "Jetpack Compose",
                "MVVM Architecture",
                "StateFlow",
                "Navigation Compose",
                "Clean Architecture"
            )
        }
}