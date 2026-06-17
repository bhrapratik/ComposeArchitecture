package com.pratik.composearchitecture.feature.practice.model

/**
 * Domain model representing a Clinic.
 *
 * @property id Unique identifier for the clinic.
 * @property clinicName The name of the clinic.
 * @property distanceMeters The distance to the clinic in meters.
 * @property isUrgent Indicates if the clinic provides urgent care services.
 * @author Pratik Behera
 */
data class Clinic(
    val id: String,
    val clinicName: String,
    val distanceMeters: Double,
    val isUrgent: Boolean
)
