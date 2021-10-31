package com.example.fetch_rewards_take_home.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userCacheEntity: UserCacheEntity): Long

    @Query("SELECT * FROM users")
    suspend fun get(): List<UserCacheEntity>

}