package com.scccrt.rekto.ui.feature.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FavoriteButton(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onToggle: (Boolean) -> Unit = {}
) {
    var checked by remember { mutableStateOf(isChecked) }

    IconToggleButton(
        checked = checked,
        onCheckedChange = {
            checked = !checked
            onToggle(checked)
        }
    ) {
        Icon(
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (checked) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }
}