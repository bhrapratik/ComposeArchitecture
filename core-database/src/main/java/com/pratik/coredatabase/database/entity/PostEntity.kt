package com.pratik.coredatabase.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Database entity representing a post stored in the local database.
 *
 * @property id The unique identifier for the post.
 * @property title The title of the post.
 * @property body The content of the post.
 * @author Pratik Behera
 */
@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String,
)
