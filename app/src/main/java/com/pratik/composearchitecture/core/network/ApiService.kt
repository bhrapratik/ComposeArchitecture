package com.pratik.composearchitecture.core.network

import com.pratik.composearchitecture.core.network.model.response.PostDto
import retrofit2.http.GET

/**
 * Retrofit service interface for network API calls.
 *
 * Defines the endpoints for fetching data from the remote server.
 *
 * @author Pratik Behera
 */
interface ApiService {

    /**
     * Fetches a list of posts from the API.
     *
     * @return A list of [PostDto] objects.
     */
    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}
