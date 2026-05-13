package com.pratik.composearchitecture.feature.home.data.mapper

import com.pratik.composearchitecture.feature.home.domain.model.Post
import com.pratik.coredatabase.database.entity.PostEntity

/**
 * Maps a [PostEntity] (Database model) to a [Post] (Domain model).
 *
 * @author Pratik Behera
 */
fun PostEntity.toPost(): Post {
    return Post(
        id = id,
        title = title,
        body = body,
    )
}

/**
 * Maps a [Post] (Domain model) to a [PostEntity] (Database model).
 *
 * @author Pratik Behera
 */
fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        id = id,
        title = title,
        body = body,
    )
}
