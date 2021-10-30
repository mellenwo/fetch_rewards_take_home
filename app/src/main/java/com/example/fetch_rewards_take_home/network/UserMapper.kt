package com.example.fetch_rewards_take_home.network

import com.example.fetch_rewards_take_home.model.User
import com.example.fetch_rewards_take_home.util.EntityMapper
import javax.inject.Inject

class UserMapper
    @Inject constructor(): EntityMapper<UserObjectResponse, User> {

    override fun mapFromEntity(entity: UserObjectResponse): User {
        return User(
            id = entity.id,
            listId = entity.listId,
            name = entity.name
        )
    }

    override fun mapToEntity(domainModel: User): UserObjectResponse {
        return UserObjectResponse(
            id = domainModel.id,
            listId = domainModel.listId,
            name = domainModel.name
        )
    }

    fun mapFromEntityList(entities: List<UserObjectResponse>): List<User> {
        return entities.map { mapFromEntity(it) }
    }

}