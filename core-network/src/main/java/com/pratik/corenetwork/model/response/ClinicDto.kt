package com.pratik.corenetwork.model.response

import kotlinx.serialization.Serializable


/**
 * Data Transfer Object (DTO) representing a clinic received from the API.
 *
 * @property id The unique identifier for the clinic.
 * @property clinic_name The name of the clinic.
 * @property distance_meters The distance to the clinic in meters.
 * @property classification The classification of the clinic (e.g., "URGENT").
 * @author Pratik Behera
 */
@Serializable
data class ClinicDto(
    val id: String?,
    val clinic_name: String?,
    val distance_meters: Double?,
    val classification: String?
)
