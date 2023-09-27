package com.scccrt.rekto.data.model

import com.scccrt.rekto.data.dto.response.MovieResponse
import java.util.Currency

data class Movie(
    val trackId: String,
    val artistName: String,
    val trackName: String,
    val description: Description,
    val previewUrl: String,
    val artwork: Artwork,
    val price: Price,
    val priceHd: Price,
    val primaryGenre: String,
)

data class Artwork(
    val miniArtworkUrl: String? = null,
    val largeArtworkUrl: String? = null
)

data class Price(
    val collectionPrice: String,
    val trackPrice: String,
    val currencyCode: String
) {
    val displayPrice: String get() = "$trackPrice ${Currency.getInstance(currencyCode).symbol}"
}

data class Description(
    val short: String,
    val long: String
)

fun MovieResponse.toMovie(): Movie {
    return Movie(
        trackId = trackId,
        artistName = artistName,
        trackName = trackName,
        description = Description(shortDescription, longDescription),
        previewUrl = previewUrl,
        artwork = Artwork(
            miniArtworkUrl = artworkMiniUrl,
            largeArtworkUrl = artworkLargeUrl
        ),
        price = Price(
            collectionPrice = collectionPrice,
            trackPrice = trackPrice,
            currencyCode = currency
        ),
        priceHd = Price(
            collectionPrice = collectionHdPrice,
            trackPrice = trackHdPrice,
            currencyCode = currency
        ),
        primaryGenre = primaryGenre
    )
}