package com.example.interviewservice.database.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 应用图标、应用名称、版本、应用包名
 */
@Entity
data class RecommendInfo(
    @PrimaryKey(autoGenerate = false) val packageName: String,
    @ColumnInfo(name = "app_name") val appName: String,
    @ColumnInfo(name = "icon") val icon: String,
    @ColumnInfo(name = "version") val version: String
)