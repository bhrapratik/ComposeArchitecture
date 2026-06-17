package com.pratik.corenetwork.api

import com.pratik.corenetwork.model.response.ClinicDto

/**
 * Service interface for fetching clinic data.
 *
 * @author Pratik Behera
 */
interface ClinicApiService {

    /**
     * Fetches a list of clinics.
     *
     * @return A list of [ClinicDto] objects.
     */
    suspend fun getClinics() : List<ClinicDto>
}
