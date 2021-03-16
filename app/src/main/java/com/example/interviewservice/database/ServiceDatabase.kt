package com.example.interviewservice.database

import android.database.sqlite.SQLiteQueryBuilder
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.interviewservice.ServiceApplication
import com.example.interviewservice.database.dao.RecommendAppDao
import com.example.interviewservice.database.entity.RecommendInfo

/**
 * @author qiuyunfei
 * @date 2021/3/16 0016
 * @description
 */
@Database(
    entities = [
        RecommendInfo::class
    ],
    version = 1
)
abstract class ServiceDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "service_database"
        private var INSTANCE: ServiceDatabase? = null

        @Synchronized
        fun get(): ServiceDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    ServiceApplication.getContext(),
                    ServiceDatabase::class.java, DB_NAME
                )
                    .build()
            }


            return INSTANCE!!
        }

        fun getRecommendAppDao(): RecommendAppDao = get().recommendAppDao()
    }

    abstract fun recommendAppDao(): RecommendAppDao
}