package com.pratik.coredatabase.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pratik.coredatabase.database.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<PostEntity>>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)
}