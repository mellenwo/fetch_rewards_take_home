package com.example.fetch_rewards_take_home.di

import com.example.fetch_rewards_take_home.database.CacheMapper
import com.example.fetch_rewards_take_home.database.UserDao
import com.example.fetch_rewards_take_home.network.UserApi
import com.example.fetch_rewards_take_home.network.UserMapper
import com.example.fetch_rewards_take_home.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao,
        userApi: UserApi,
        userMapper: UserMapper,
        cacheMapper: CacheMapper,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): UserRepository {
        return UserRepository(
            userDao,
            userApi,
            userMapper,
            cacheMapper,
            ioDispatcher
        )
    }

}