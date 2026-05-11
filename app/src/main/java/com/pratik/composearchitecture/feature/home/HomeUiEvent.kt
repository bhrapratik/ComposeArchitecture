package com.pratik.composearchitecture.feature.home

/**
 * Represents UI events that can occur on the Home screen.
 *
 * @author Pratik Behera
 */
sealed interface HomeUiEvent {
    /**
     * Event triggered when an item is clicked to open its details.
     *
     * @property item The item identifier.
     */
    data class OpenDetails(val item: String) : HomeUiEvent
}
