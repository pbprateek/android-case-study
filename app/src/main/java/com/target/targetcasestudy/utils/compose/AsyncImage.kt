package com.target.targetcasestudy.utils.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.target.targetcasestudy.R

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String?,
    placeholder: Painter? = ColorPainter(Color.LightGray),
    error: Painter? = ColorPainter(Color.LightGray),
    contentScale: ContentScale = ContentScale.Fit
) {

    coil.compose.AsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        placeholder = placeholder,
        error = error
    )

}