package com.pratik.coredatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pratik.coredatabase.database.dao.NotificationDao
import com.pratik.coredatabase.database.dao.PostDao
import com.pratik.coredatabase.database.entity.NotificationEntity
import com.pratik.coredatabase.database.entity.PostEntity

/**
 * The Room database for this app.
 *
 * It provides access to the DAOs for various entities.
 *
 * @author Pratik Behera
 */
@Database(entities = [PostEntity::class, NotificationEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Returns the DAO for the [PostEntity] table.
     */
    abstract fun postDao(): PostDao


    abstract fun notificationDao(): NotificationDao
}
