package com.halebop.selfservice.composearchitecture.core.network

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
 * Hilt module that provides networking-related dependencies.
 *
 * This module configures and provides instances of [Retrofit], [OkHttpClient],
 * and [ApiService] as singletons available throughout the application.
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
     * Provides an [OkHttpClient] configured with a logging interceptor.
     */
    @Provides
    @Singleton
    fun provideOkHttp(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Provides a [Retrofit] instance configured with a base URL and JSON converter.
     */
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides an implementation of [ApiService].
     */
    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
