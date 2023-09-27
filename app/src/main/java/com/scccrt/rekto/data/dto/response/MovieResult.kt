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
    val trackId: Long,
    val artistName: String,
    val trackName: String,
    @SerialName("artworkUrl60") val artworkMiniUrl: String,
    @SerialName("artworkUrl100") val artworkLargeUrl: String,
    @SerialName("primaryGenreName") val primaryGenre: String,
    val collectionPrice: Double? = null,
    val trackPrice: Double? = null,
    val collectionHdPrice: Double? = null,
    val trackHdPrice: Double? = null,
    val currency: String,
    val shortDescription: String? = null,
    val longDescription: String? = null
)