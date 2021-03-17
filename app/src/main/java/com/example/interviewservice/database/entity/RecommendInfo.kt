package com.example.interviewservice.database.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 推荐的app信息
 * 导入文件格式eg:
 * {
 * "RecommendInfo":[{
 * "packageName":"com.tencent.mm",
 * "appName":"微信",
 * "icon":"https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3262171803,2318469631&fm=26&gp=0.jpg",
 * "version":"1.0.0"
 * }]
 * }
 */
@Entity
@Keep
data class RecommendInfo(
    @PrimaryKey(autoGenerate = false) val packageName: String,
    @ColumnInfo(name = "app_name") val appName: String,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "version") val version: String
)
