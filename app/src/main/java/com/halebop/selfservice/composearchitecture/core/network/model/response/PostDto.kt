package com.halebop.selfservice.composearchitecture.core.network.model.response

/**
 * Data Transfer Object (DTO) representing a post received from the API.
 *
 * @property id The unique identifier for the post.
 * @property title The title of the post.
 * @property body The content/body of the post.
 * @author Pratik Behera
 */
data class PostDto(
    val id: Int,
    val title: String,
    val body: String
)
