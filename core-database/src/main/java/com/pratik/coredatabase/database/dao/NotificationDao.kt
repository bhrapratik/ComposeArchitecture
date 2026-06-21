package com.pratik.coredatabase.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pratik.coredatabase.database.entity.NotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notifications ORDER BY createdAt DESC")
     fun getAllNotifications(): Flow<List<NotificationEntity>>

    @Query(
        """
        UPDATE notifications
        SET isRead = 1
        WHERE id = :id
    """
    )
    suspend fun markAsRead(id: Long)

    @Query("""
    SELECT COUNT(*)
    FROM notifications
    WHERE isRead = 0
""")
    fun getUnreadCount(): Flow<Int>


    @Insert
    suspend fun insertNotification(notifications: NotificationEntity)


}
