package com.pratik.coredatabase.database.di

import android.content.Context
import androidx.room.Room
import com.pratik.coredatabase.database.AppDatabase
import com.pratik.coredatabase.database.dao.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

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