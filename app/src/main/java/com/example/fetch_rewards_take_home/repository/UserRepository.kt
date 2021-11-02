package com.example.fetch_rewards_take_home.repository

import com.example.fetch_rewards_take_home.database.CacheMapper
import com.example.fetch_rewards_take_home.database.UserDao
import com.example.fetch_rewards_take_home.model.User
import com.example.fetch_rewards_take_home.network.UserApi
import com.example.fetch_rewards_take_home.network.UserMapper
import com.example.fetch_rewards_take_home.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class UserRepository(
    private val userDao: UserDao,
    private val api: UserApi,
    private val userMapper: UserMapper,
    private val cacheMapper: CacheMapper,
) {

    suspend fun getUsers(): Flow<DataState<List<User>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val networkUsers = api.getData()
            val users = userMapper.mapFromEntityList(networkUsers)
            for (user in users) {
                userDao.insert(cacheMapper.mapToEntity(user))
            }
            val cachedUsers = userDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedUsers)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}