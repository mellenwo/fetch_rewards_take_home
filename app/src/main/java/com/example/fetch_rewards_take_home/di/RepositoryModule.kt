package com.example.fetch_rewards_take_home.di

import com.example.fetch_rewards_take_home.network.UserApi
import com.example.fetch_rewards_take_home.network.UserMapper
import com.example.fetch_rewards_take_home.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        userApi: UserApi,
        userMapper: UserMapper
    ): UserRepository {
        return UserRepository(userApi, userMapper)
    }

}