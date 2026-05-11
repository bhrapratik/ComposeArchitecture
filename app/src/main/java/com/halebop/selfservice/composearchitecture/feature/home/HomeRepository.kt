package com.halebop.selfservice.composearchitecture.feature.home

import com.halebop.selfservice.composearchitecture.core.network.ApiService
import javax.inject.Inject

/**
 * Repository class that provides data for the home feature.
 *
 * It abstracts the data source (API) from the ViewModel.
 *
 * @property apiService The [ApiService] used to fetch data from the network.
 * @author Pratik Behera
 */
class HomeRepository @Inject constructor(private val apiService: ApiService) {
    /**
     * Fetches a list of post titles from the network.
     *
     * @return A list of strings representing the post titles.
     */
    suspend fun getPosts(): List<String> {
        val posts = apiService.getPosts()
        return posts.map { post -> post.title }
    }
}
