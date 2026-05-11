package com.pratik.coredatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pratik.coredatabase.database.dao.PostDao
import com.pratik.coredatabase.database.entity.PostEntity

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}