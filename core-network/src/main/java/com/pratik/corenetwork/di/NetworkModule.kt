package com.pratik.corenetwork.di

import android.content.Context
import com.pratik.corenetwork.api.ClinicApiService
import com.pratik.corenetwork.api.MockClinicApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides clinic-related network dependencies.
 *
 * This module is responsible for providing the [ClinicApiService] implementation.
 *
 * @author Pratik Behera
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides the [ClinicApiService] implementation.
     * In this case, it provides a [MockClinicApiService].
     *
     * @param context The application context used by the mock service to access assets.
     */
    @Provides
    @Singleton
    fun provideClinicApiService(@ApplicationContext context: Context): ClinicApiService {
        return MockClinicApiService(context)
    }
}
