package com.scccrt.rekto.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scccrt.rekto.data.local.dao.SearchHistoryDao
import com.scccrt.rekto.data.local.entities.SearchQuery

@Database(
    entities = [SearchQuery::class],
    version = 1,
    exportSchema = false
)
abstract class RektoDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao
}