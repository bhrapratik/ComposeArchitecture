package com.pratik.coredatabase.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pratik.coredatabase.database.entity.PostEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for the [PostEntity] table.
 *
 * Provides methods for performing database operations on posts.
 *
 * @author Pratik Behera
 */
@Dao
interface PostDao {

    /**
     * Retrieves all posts from the database.
     *
     * @return A [Flow] emitting the list of [PostEntity] objects whenever the data changes.
     */
    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<PostEntity>>

    /**
     * Inserts a list of posts into the database.
     * Replaces existing entries if there is a primary key conflict.
     *
     * @param posts The list of [PostEntity] objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)
}
