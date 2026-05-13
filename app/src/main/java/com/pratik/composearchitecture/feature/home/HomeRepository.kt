package com.pratik.composearchitecture.feature.home

import com.pratik.composearchitecture.core.common.Resource
import com.pratik.composearchitecture.feature.home.data.mapper.toPost
import com.pratik.composearchitecture.feature.home.data.mapper.toPostEntity
import com.pratik.composearchitecture.feature.home.domain.model.Post
import com.pratik.coredatabase.database.datasource.PostLocalDataSource
import com.pratik.corenetwork.api.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository class that provides data for the home feature.
 *
 * It abstracts the data source (API and Local Database) from the ViewModel.
 *
 * @property apiService The [ApiService] used to fetch data from the network.
 * @property localDataSource The [PostLocalDataSource] used for local persistence.
 * @author Pratik Behera
 */
class HomeRepository
    @Inject
    constructor(
        private val apiService: ApiService,
        private val localDataSource: PostLocalDataSource,
    ) {
        /**
         * Fetches a list of posts, emitting loading, success, or error states.
         * It attempts to refresh the posts from the network and then observes the local database.
         *
         * @return A [Flow] of [Resource] containing a list of [Post] objects.
         */
        fun getPosts(): Flow<Resource<List<Post>>> =
            flow {
                emit(Resource.Loading)
                runCatching {
                    refreshPosts()
                    localDataSource.getAllPosts().map { entities ->
                        entities.map { entity ->
                            entity.toPost()
                        }
                    }.collect { posts ->
                        emit(Resource.Success(posts))
                    }
                }.onFailure { exception ->
                    emit(Resource.Error(exception.message))
                }
            }

        /**
         * Refreshes the posts by fetching them from the network and saving them to the local database.
         */
        suspend fun refreshPosts() {
            val postsFromApi =
                apiService.getPosts().map { dto ->
                    dto.toPost()
                }
            localDataSource.insertPosts(
                postsFromApi.map { post ->
                    post.toPostEntity()
                },
            )
        }
    }
