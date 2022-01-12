package com.olamachia.simpleblogapp.cache

import com.olamachia.simpleblogapp.util.EntityMapper
import org.w3c.dom.Comment
import org.w3c.dom.Entity

class CommentCacheMapper: EntityMapper<CommentCacheEntity, Comment> {

    override fun mapFromEntity(entity: CommentCacheEntity): Comment {
        return Comment(
            postId = entity.postId,
            id = entity.id,
            name = entity.name,
            email = entity.email,
            body = entity.body
        )
    }

    override fun mapToEntity(domainModel: Comment): CommentCacheEntity {
        return CommentCacheEntity(
            postId = domainModel.postId,
            id = domainModel.id,
            name = domainModel.name,
            email = domainModel.email,
            body = domainModel.body
        )
    }

    fun mapFromEntityList(entities : List<CommentCacheEntity>) : List<Comment> {
        return entities.map { mapFromEntity(it) }
    }
}