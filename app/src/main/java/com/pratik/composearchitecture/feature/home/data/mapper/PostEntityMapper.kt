package com.pratik.composearchitecture.feature.home.data.mapper

import com.pratik.composearchitecture.feature.home.domain.model.Post
import com.pratik.coredatabase.database.entity.PostEntity

fun PostEntity.toPost(): Post {

    return Post(
        id = id,
        title = title,
        body = body
    )
}

fun Post.toPostEntity(): PostEntity {

    return PostEntity(
        id = id,
        title = title,
        body = body
    )
}