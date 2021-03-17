package com.example.interviewservice.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.interviewservice.database.entity.RecommendInfo

/**
 * @author qiuyunfei
 * @date 2021/3/16 0016
 * @description
 */
@Dao
interface RecommendAppDao {
    @Query("SELECT * FROM RecommendInfo")
    suspend fun getAllRecommendApp(): List<RecommendInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApp(info: List<RecommendInfo>)

    @Query("DELETE FROM RecommendInfo WHERE RecommendInfo.packageName=:packageName")
    suspend fun deleteApp(packageName: String):Int

    @Query("DELETE FROM RecommendInfo")
    suspend fun deleteAll()
}