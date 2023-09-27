package com.scccrt.rekto.data.model

import com.scccrt.rekto.data.dto.response.MovieResponse
import java.util.Currency

data class Movie(
    val trackId: String,
    val artistName: String,
    val trackName: String,
    val description: Description,
    val artwork: Artwork,
    val price: Price,
    val priceHd: Price,
    val primaryGenre: String
)

data class Artwork(
    val miniArtworkUrl: String? = null,
    val largeArtworkUrl: String? = null
)

data class Price(
    val collectionPrice: Double,
    val trackPrice: Double,
    val currencyCode: String
) {
    val displayPrice: String get() = "$trackPrice ${Currency.getInstance(currencyCode).symbol}"
}

data class Description(
    val short: String?,
    val long: String?
)

fun MovieResponse.toMovie(): Movie {
    return Movie(
        trackId = trackId.toString(),
        artistName = artistName,
        trackName = trackName,
        description = Description(shortDescription, longDescription),
        artwork = Artwork(
            miniArtworkUrl = artworkMiniUrl,
            largeArtworkUrl = artworkLargeUrl
        ),
        price = Price(
            collectionPrice = collectionPrice ?: 0.0,
            trackPrice = trackPrice ?: 0.0,
            currencyCode = currency
        ),
        priceHd = Price(
            collectionPrice = collectionHdPrice ?: 0.0,
            trackPrice = trackHdPrice ?: 0.0,
            currencyCode = currency
        ),
        primaryGenre = primaryGenre
    )
}

fun moviePreview() = Movie(
    trackId = "1",
    artistName = "Sample Artist Name",
    trackName = "Sample Track Name",
    description = Description(short = "Short Description", long = ""),
    artwork = Artwork(miniArtworkUrl = null, largeArtworkUrl = null),
    price = Price(100.00, 100.00, "AUD"),
    priceHd = Price(100.00, 100.00, "AUD"),
    primaryGenre = "Genre"
)