package com.pratik.coredatabase.database.di

import android.content.Context
import androidx.room.Room
import com.pratik.coredatabase.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides database-related dependencies.
 *
 * This module is responsible for providing the singleton instance of [AppDatabase].
 *
 * @author Pratik Behera
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides the singleton instance of [AppDatabase].
     *
     * @param context The application context.
     * @return The configured Room database instance.
     */
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "compose_architecture.db"
        ).build()
    }
}
