package com.example.interviewservice.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.util.Log
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.interviewservice.database.ServiceDatabase


/**
 * @author qiuyunfei
 * @date 2021/3/16 0016
 * @description
 */
class RecommendAppProvider : ContentProvider() {
    companion object {
        private const val TAG = "RecommendAppProvider"

    }
    private val mDatabase: SupportSQLiteDatabase by lazy {
        ServiceDatabase.get()
            .openHelper
            .writableDatabase
    }
    override fun onCreate(): Boolean {
        Log.d(TAG, "onCreate: " + Thread.currentThread().name)
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d(TAG, "insert: " + Thread.currentThread().name)
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val builder = SQLiteQueryBuilder()
        builder.tables = "RecommendInfo"
        val query =
            builder.buildQuery(projection, selection, null, null, sortOrder, null)
        return mDatabase.query(query, selectionArgs)
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        Log.d(TAG, "update: " + Thread.currentThread().name)
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.d(TAG, "delete: " + Thread.currentThread().name)
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}