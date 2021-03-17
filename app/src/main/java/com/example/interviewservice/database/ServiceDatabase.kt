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
                    .addCallback(CALLBACK)
                    .build()
            }


            return INSTANCE!!
        }

        val initRecommendInfo = arrayOf(
            arrayOf(
                "com.jianshu.haruki",
                "简书",
                "https://pic.17qq.com/uploads/qruectcchx.jpeg",
                "1.0.0"
            ), arrayOf(
                "com.zhihu.android",
                "知乎",
                "https://ss3.baidu.com/-fo3dSag_xI4khGko9WTAnF6hhy/baike/pic/item/1b4c510fd9f9d72a26f9db77df2a2834349bbb3f.jpg",
                "1.0.0"
            ), arrayOf(
                "com.luojilab.player",
                "得到",
                "https://ss0.baidu.com/-Po3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/86d6277f9e2f07080361fd58e224b899a901f206.jpg",
                "1.0.0"
            ), arrayOf(
                "com.flightmanager.view",
                "航班管家",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1310084190,3440202351&fm=26&gp=0.jpg",
                "1.0.0"
            ), arrayOf(
                "com.iflytek.inputmethod",
                "讯飞输入法",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=55616755,1069134233&fm=26&gp=0.jpg",
                "1.0.0"
            ), arrayOf(
                "com.meitu.meiyancamera",
                "美颜相机",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2771030822,2247883056&fm=26&gp=0.jpg",
                "1.0.0"
            ), arrayOf(
                "com.dp.android.elong",
                "艺龙旅行",
                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4277017437,2026531708&fm=26&gp=0.jpg",
                "1.0.0"
            ), arrayOf(
                "com.tencent.weread",
                "微信读书",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=87885162,4179658412&fm=26&gp=0.jpg",
                "1.0.0"
            ), arrayOf(
                "com.immomo.momo",
                "陌陌",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3551731065,3155712203&fm=11&gp=0.jpg",
                "1.0.0"
            ), arrayOf(
                "com.tencent.qqlive",
                "腾讯视频",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3829821006,1893451978&fm=26&gp=0.jpg",
                "1.0.0"
            )
        )

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                initRecommendInfo.forEach {
                    db.execSQL("INSERT INTO RecommendInfo (packageName, app_name, icon, version) VALUES ('${it[0]}', '${it[1]}', '${it[2]}', '${it[3]}')")
                }
            }
        }

        fun getRecommendAppDao(): RecommendAppDao = get().recommendAppDao()
    }

    abstract fun recommendAppDao(): RecommendAppDao
}