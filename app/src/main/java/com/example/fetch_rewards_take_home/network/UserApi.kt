package com.example.fetch_rewards_take_home.network

import com.example.fetch_rewards_take_home.model.User
import retrofit2.http.GET

interface UserApi {

    @GET("hiring.json")
    suspend fun getData(): List<UserObjectResponse>

}