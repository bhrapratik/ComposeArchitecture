package com.pratik.coredatabase.database.datasource

import com.pratik.coredatabase.database.AppDatabase
import com.pratik.coredatabase.database.entity.NotificationEntity
import com.pratik.coredatabase.database.entity.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotificationLocalDataSource
@Inject
constructor(
    private val database: AppDatabase,
) {

     fun getAllNotifications(): Flow<List<NotificationEntity>> =
        database.notificationDao().getAllNotifications()


    suspend fun saveNotification(title: String, body: String) =
        database.notificationDao().insertNotification(
            NotificationEntity(
                title = title,
                body = body,
                createdAt = System.currentTimeMillis(),
                isRead = false
            )
        )

    suspend fun markAsRead(id: Long) =database.notificationDao().markAsRead(id)

    fun getUnreadCount(): Flow<Int> {
        return  database.notificationDao().getUnreadCount()
    }


}
