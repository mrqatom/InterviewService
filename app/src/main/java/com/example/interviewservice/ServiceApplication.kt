package com.example.interviewservice

import android.app.Application
import android.content.Context

/**
 * @author qiuyunfei
 * @date 2021/3/16 0016
 * @description
 */
class ServiceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: ServiceApplication
            private set

        fun getContext(): Context = instance.applicationContext
    }
}