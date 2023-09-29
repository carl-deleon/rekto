package com.scccrt.rekto.data.model

import com.scccrt.rekto.data.dto.response.MovieResponse
import com.scccrt.rekto.data.local.entities.MovieEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Currency
import java.util.GregorianCalendar
import java.util.Locale

data class Movie(
    val trackId: String,
    val artistName: String,
    val trackName: String,
    val description: Description,
    val artwork: Artwork,
    val price: Price,
    val priceHd: Price,
    val primaryGenre: String,
    val previewUrl: String?,
    val releaseDate: String
) {

    val displayTitle: String
        get() {
            val sdf = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val year = sdf.parse(releaseDate)
                ?.let { GregorianCalendar().apply { time = it } }
                ?.get(Calendar.YEAR)

            return year?.let { "$trackName (${year})" } ?: trackName
        }
}

data class Artwork(
    val miniArtworkUrl: String? = null,
    val largeArtworkUrl: String? = null
)

data class Price(
    val collectionPrice: Double,
    val trackPrice: Double,
    val currencyCode: String
) {
    val displayPrice: String get() = "${Currency.getInstance(currencyCode).symbol} $trackPrice"
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
        primaryGenre = primaryGenre,
        previewUrl = previewUrl,
        releaseDate = releaseDate
    )
}

fun Movie.toEntity() = MovieEntity(
    trackId = trackId,
    artistName = artistName,
    trackName = trackName,
    shortDescription = description.short,
    longDescription = description.long,
    artworkMini = artwork.miniArtworkUrl,
    artworkLarge = artwork.largeArtworkUrl,
    currency = price.currencyCode,
    collectionPrice = price.collectionPrice.toString(),
    trackPrice = price.trackPrice.toString(),
    collectionHdPrice = priceHd.collectionPrice.toString(),
    trackHdPrice = priceHd.collectionPrice.toString(),
    genre = primaryGenre,
    previewUrl = previewUrl,
    releaseDate = releaseDate
)

fun MovieEntity.toMovie() = Movie(
    trackId = trackId,
    artistName = artistName,
    trackName = trackName,
    description = Description(shortDescription, longDescription),
    artwork = Artwork(artworkMini, artworkLarge),
    price = Price(collectionPrice.toDouble(), trackPrice.toDouble(), currency),
    priceHd = Price(collectionHdPrice.toDouble(), trackHdPrice.toDouble(), currency),
    primaryGenre = genre,
    previewUrl = previewUrl,
    releaseDate = releaseDate
)

fun moviePreview() = Movie(
    trackId = "1",
    artistName = "Sample Artist Name",
    trackName = "Sample Track Name",
    description = Description(short = "Short Description", long = "Long Description"),
    artwork = Artwork(miniArtworkUrl = null, largeArtworkUrl = null),
    price = Price(100.00, 100.00, "AUD"),
    priceHd = Price(100.00, 100.00, "AUD"),
    primaryGenre = "Genre",
    previewUrl = "",
    releaseDate = "2023-06-01T07:00:00Z"
)