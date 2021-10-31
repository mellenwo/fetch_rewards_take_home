package com.example.fetch_rewards_take_home.database

import com.example.fetch_rewards_take_home.model.User
import com.example.fetch_rewards_take_home.util.EntityMapper
import javax.inject.Inject

class CacheMapper
    @Inject constructor() : EntityMapper<UserCacheEntity, User>{

    override fun mapFromEntity(entity: UserCacheEntity): User {
        return User(
            id = entity.id,
            listId = entity.listId,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: User): UserCacheEntity {
        return UserCacheEntity(
            id = domainModel.id,
            listId = domainModel.listId,
            name = domainModel.name
        )
    }

    fun mapFromEntityList(entities: List<UserCacheEntity>): List<User> {
        return entities.map { mapFromEntity(it) }
    }

}