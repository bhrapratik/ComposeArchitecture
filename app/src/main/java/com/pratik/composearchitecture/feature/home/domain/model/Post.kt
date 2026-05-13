package com.pratik.composearchitecture.feature.home.domain.model

/**
 * Domain model representing a post.
 *
 * This model is used across the presentation and domain layers of the application.
 *
 * @property id The unique identifier for the post.
 * @property title The title of the post.
 * @property body The content of the post.
 * @author Pratik Behera
 */
data class Post(
    val id: Int,
    val title: String,
    val body: String,
)
