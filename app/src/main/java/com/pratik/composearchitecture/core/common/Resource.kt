package com.pratik.composearchitecture.core.common

/**
 * A generic class that holds a value with its loading status.
 *
 * This is commonly used to wrap data fetched from a repository to represent
 * different states of the data retrieval process (Success, Error, Loading).
 *
 * @param T The type of the data being wrapped.
 * @author Pratik Behera
 */
sealed interface Resource<out T> {

    /**
     * Represents a successful operation with the resulting [data].
     *
     * @property data The data returned by the operation.
     */
    data class Success<T>(
        val data: T
    ) : Resource<T>

    /**
     * Represents a failed operation with an optional [message].
     *
     * @property message A description of the error that occurred.
     */
    data class Error(
        val message: String?
    ) : Resource<Nothing>

    /**
     * Represents an ongoing operation (e.g., fetching data).
     */
    data object Loading : Resource<Nothing>
}
