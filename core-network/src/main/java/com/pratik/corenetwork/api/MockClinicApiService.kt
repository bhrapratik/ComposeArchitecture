package com.pratik.corenetwork.api

import android.content.Context
import com.pratik.corenetwork.model.response.ClinicDto
import com.pratik.corenetwork.utils.getClinicsFromJson

/**
 * Mock implementation of [ClinicApiService] that reads data from a local JSON file.
 *
 * @property context The application context used to access assets.
 * @author Pratik Behera
 */
class MockClinicApiService(private val context: Context) : ClinicApiService {

    /**
     * Fetches a list of clinics from a mock JSON file in the assets.
     *
     * @return A list of [ClinicDto] objects.
     */
    override suspend fun getClinics(): List<ClinicDto> {
        return getClinicsFromJson(context, "clinics.json")
    }
}
