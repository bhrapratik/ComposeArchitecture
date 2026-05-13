package com.pratik.coredatabase.database.datasource

import com.pratik.coredatabase.database.AppDatabase
import com.pratik.coredatabase.database.entity.PostEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Local data source for post-related database operations.
 *
 * This class acts as a wrapper around the Room database, providing a cleaner API
 * for accessing and modifying post data locally.
 *
 * @property database The [AppDatabase] instance.
 * @author Pratik Behera
 */
class PostLocalDataSource @Inject constructor(
    private val database: AppDatabase
) {
    /**
     * Retrieves all posts from the local database.
     *
     * @return A [Flow] of the list of [PostEntity] objects.
     */
    fun getAllPosts(): Flow<List<PostEntity>> = database.postDao().getAllPosts()

    /**
     * Inserts a list of posts into the local database.
     *
     * @param posts The list of [PostEntity] objects to insert.
     */
    suspend fun insertPosts(posts: List<PostEntity>) = database.postDao().insertPosts(posts)
}
