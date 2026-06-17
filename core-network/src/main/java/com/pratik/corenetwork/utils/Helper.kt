package com.pratik.corenetwork.utils

import android.content.Context
import com.pratik.corenetwork.model.response.ClinicDto
import kotlinx.serialization.json.Json

/**
 * Utility function to load a list of clinics from a JSON file located in the assets folder.
 *
 * @param context The context used to access the assets.
 * @param fileName The name of the JSON file to read.
 * @return A list of [ClinicDto] objects, or an empty list if an error occurs during reading or parsing.
 * @author Pratik Behera
 */
fun getClinicsFromJson(
    context: Context,
    fileName: String
): List<ClinicDto> {
    return runCatching {
        val json = context.assets
            .open(fileName)
            .bufferedReader()
            .use { it.readText() }

        Json.decodeFromString<List<ClinicDto>>(json)
    }.getOrElse {
        emptyList()
    }
}
