package com.pratik.composearchitecture.feature.home.data.mapper

import com.pratik.composearchitecture.feature.home.domain.model.Post
import com.pratik.corenetwork.model.response.PostDto

/**
 * Maps a [PostDto] (Network model) to a [Post] (Domain model).
 *
 * @author Pratik Behera
 */
fun PostDto.toPost(): Post {
    return Post(
        id = this.id,
        title = this.title,
        body = this.body
    )
}
