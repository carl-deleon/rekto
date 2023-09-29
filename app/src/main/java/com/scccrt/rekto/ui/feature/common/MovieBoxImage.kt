package com.scccrt.rekto.ui.feature.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.scccrt.rekto.R

@Composable
fun MovieBoxImage(
    url: String? = "",
    @DrawableRes placeholder: Int = R.drawable.movie_placeholder,
    modifier: Modifier,
    crossfade: Boolean = true,
    contentScale: ContentScale = ContentScale.Fit,
    contentDescription: String = ""
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .size(Size.ORIGINAL)
            .crossfade(crossfade)
            .build(),
        placeholder = painterResource(id = placeholder),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun RoundedImagePreview() {
    MovieBoxImage(
        url = "",
        placeholder = R.drawable.movie_placeholder,
        modifier = Modifier.size(dimensionResource(id = R.dimen.artwork_large))
    )
}