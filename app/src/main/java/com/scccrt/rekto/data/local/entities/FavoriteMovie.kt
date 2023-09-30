package com.scccrt.rekto.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteMovie(
    @PrimaryKey val trackId: String,
    val trackName: String,
    val shortDescription: String?,
    val longDescription: String?,
    val artworkMini: String?,
    val artworkLarge: String?,
    val currency: String,
    val collectionPrice: String,
    val trackPrice: String,
    val genre: String,
    val previewUrl: String?,
    val releaseDate: String,
    val dateAdded: String
)