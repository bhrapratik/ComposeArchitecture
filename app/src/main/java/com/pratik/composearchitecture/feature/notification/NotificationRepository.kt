package com.pratik.composearchitecture.feature.notification

import com.pratik.composearchitecture.core.common.Resource
import com.pratik.composearchitecture.feature.notification.model.Notification
import com.pratik.coredatabase.database.datasource.NotificationLocalDataSource
import com.pratik.coredatabase.database.entity.NotificationEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class NotificationRepository @Inject constructor(private val localDataSource: NotificationLocalDataSource) {

    suspend fun saveNotification(title: String, body: String) {
        runCatching {
            localDataSource.saveNotification(title, body)
        }.onFailure {
            println("Error saving notification: $it")
        }
    }

    fun getUnReadCount(): Flow<Int> {
        return localDataSource.getUnreadCount()
    }

    suspend fun markAsRead(
        notificationId: Long
    ) {
        localDataSource.markAsRead(notificationId)
    }

    fun getAllNotification(): Flow<Resource<List<Notification>>> {

        return localDataSource
            .getAllNotifications()
            .map<List<NotificationEntity>, Resource<List<Notification>>> { entities ->

                Resource.Success(
                    entities.map {
                        it.toNotification()
                    }
                )
            }
            .onStart {
                emit(Resource.Loading)
            }
            .catch {
                emit(Resource.Error(it.message))
            }
    }

    fun NotificationEntity.toNotification(): Notification {
        return Notification(
            id = this.id,
            title = this.title,
            body = this.body,
            createdAt = this.createdAt,
            isRead = this.isRead
        )
    }
}
