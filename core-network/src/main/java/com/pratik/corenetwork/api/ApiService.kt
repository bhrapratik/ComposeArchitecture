package com.pratik.corenetwork.api

import com.pratik.corenetwork.model.response.PostDto
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
     * @return A list of [com.pratik.corenetwork.model.response.PostDto] objects.
     */
    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}