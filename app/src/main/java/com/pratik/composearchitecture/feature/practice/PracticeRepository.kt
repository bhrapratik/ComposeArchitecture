package com.pratik.composearchitecture.feature.practice

import com.pratik.composearchitecture.core.common.Resource
import com.pratik.composearchitecture.feature.practice.model.Clinic
import com.pratik.corenetwork.api.ClinicApiService
import com.pratik.corenetwork.model.response.ClinicDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class that provides data for the practice feature.
 *
 * It interacts with the [ClinicApiService] to fetch and process clinic data.
 *
 * @property clinicApiService The service used to fetch clinic data.
 * @author Pratik Behera
 */
class PracticeRepository @Inject constructor(private val clinicApiService: ClinicApiService) {

    /**
     * Fetches all clinics, sorts them (urgent first, then by distance), and emits them as a [Resource].
     *
     * @return A [Flow] of [Resource] containing a list of [Clinic] objects.
     */
    fun getAllClinics(): Flow<Resource<List<Clinic>>> = flow {
        emit(Resource.Loading)
        runCatching {
            val clinicsList = clinicApiService.getClinics().map { dto ->
                dto.toClinic()

            }
            val sortedClinics = clinicsList.sortedWith(
                compareByDescending<Clinic> { it.isUrgent }.thenBy { it.distanceMeters }
            )

            emit(Resource.Success(sortedClinics))
        }.onFailure { error ->
            emit(Resource.Error(error.message))
        }
    }


    /**
     * Extension function to map a [ClinicDto] (Network model) to a [Clinic] (Domain model).
     */
    fun ClinicDto.toClinic(): Clinic {
        return Clinic(
            id = this.id ?: "",
            clinicName = this.clinic_name ?: "",
            distanceMeters = this.distance_meters ?: 0.0,
            isUrgent = this.classification == "URGENT"
        )
    }
}
