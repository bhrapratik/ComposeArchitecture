package com.pratik.composearchitecture.feature.home

import com.pratik.composearchitecture.feature.home.data.mapper.toPost
import com.pratik.composearchitecture.feature.home.data.mapper.toPostEntity
import com.pratik.composearchitecture.feature.home.domain.model.Post
import com.pratik.coredatabase.database.datasource.PostLocalDataSource
import com.pratik.corenetwork.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository class that provides data for the home feature.
 *
 * It abstracts the data source (API) from the ViewModel.
 *
 * @property apiService The [ApiService] used to fetch data from the network.
 * @author Pratik Behera
 */
class HomeRepository @Inject constructor(private val apiService: ApiService, private val localDataSource: PostLocalDataSource) {
    /**
     * Fetches a list of post titles from the network.
     *
     * @return A list of strings representing the post titles.
     */
    fun getPosts(): Flow<List<Post>> {
        return  localDataSource
            .getAllPosts()
            .map {entities->
                entities.map {entity->
                    entity.toPost()

                }

            }
    }

    suspend fun refreshPosts() {
        val postsFromApi = apiService
            .getPosts()
            .map { dto->
                dto.toPost()
            }
        localDataSource.insertPosts(
            postsFromApi.map {post->
                post.toPostEntity()

            })
    }
}
