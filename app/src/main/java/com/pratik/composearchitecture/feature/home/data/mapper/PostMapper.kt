package com.pratik.composearchitecture.feature.home.data.mapper

import com.pratik.composearchitecture.feature.home.domain.model.Post
import com.pratik.coredatabase.database.entity.PostEntity
import com.pratik.corenetwork.model.response.PostDto

fun PostDto.toPost(): Post {
    return Post(
        id = this.id,
        title = this.title,
        body = this.body
    )
}