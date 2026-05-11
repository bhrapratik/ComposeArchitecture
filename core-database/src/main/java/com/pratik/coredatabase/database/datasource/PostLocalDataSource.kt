package com.pratik.coredatabase.database.datasource

import com.pratik.coredatabase.database.AppDatabase
import com.pratik.coredatabase.database.entity.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostLocalDataSource @Inject constructor(
    private val database: AppDatabase
) {
    fun getAllPosts(): Flow<List<PostEntity>> = database.postDao().getAllPosts()
    suspend fun insertPosts(posts: List<PostEntity>) = database.postDao().insertPosts(posts)
}