package com.pratik.corenetwork.di

import com.pratik.corenetwork.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Hilt module that provides network-related dependencies.
 *
 * This module is responsible for configuring and providing [Retrofit], [OkHttpClient],
 * and the [ApiService].
 *
 * @author Pratik Behera
 */
@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    /**
     * Provides a [HttpLoggingInterceptor] for logging network requests and responses.
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provides a configured [OkHttpClient].
     *
     * @param loggingInterceptor The interceptor used for logging.
     */
    @Provides
    @Singleton
    fun provideOkHttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Provides a [Retrofit] instance configured with a base URL and Gson converter.
     *
     * @param okHttpClient The HTTP client to be used by Retrofit.
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides the [ApiService] implementation.
     *
     * @param retrofit The Retrofit instance used to create the service.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
