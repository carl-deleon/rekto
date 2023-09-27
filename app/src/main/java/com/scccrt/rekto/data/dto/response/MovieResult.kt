package com.scccrt.rekto.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResult(
    val results: List<MovieResponse>,
    @SerialName("resultCount") val count: Int
)

@Serializable
data class MovieResponse(
    val trackId: String,
    val artistName: String,
    val trackName: String,
    val previewUrl: String,
    @SerialName("artworkUrl60") val artworkMiniUrl: String,
    @SerialName("artworkUrl100") val artworkLargeUrl: String,
    @SerialName("primaryGenreName") val primaryGenre: String,
    val collectionPrice: String,
    val trackPrice: String,
    val collectionHdPrice: String,
    val trackHdPrice: String,
    val currency: String,
    val shortDescription: String,
    val longDescription: String
)