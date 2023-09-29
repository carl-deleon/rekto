package com.scccrt.rekto.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "search_history", indices = [Index(value = ["query"], unique = true)])
data class SearchQuery(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var query: String
)