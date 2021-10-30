package com.example.fetch_rewards_take_home.repository

import com.example.fetch_rewards_take_home.model.User
import com.example.fetch_rewards_take_home.network.UserApi
import com.example.fetch_rewards_take_home.network.UserMapper
import com.example.fetch_rewards_take_home.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class UserRepository(
    private val api: UserApi,
    private val userMapper: UserMapper
) {

    suspend fun getUsers(): Flow<DataState<List<User>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkUsers = api.getData()
            val users = userMapper.mapFromEntityList(networkUsers)
            emit(DataState.Success(users))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}