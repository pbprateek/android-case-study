package com.target.targetcasestudy.core.utils.compose

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
    placeholder:Painter? = painterResource(id = R.drawable.ic_launcher_background),
    error: Painter? = painterResource(id = R.drawable.ic_launcher_background),
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

@Composable
fun SubCompAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = imageUrl,
        imageLoader = getAsyncImageLoader(LocalContext.current),
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}

fun getAsyncImageLoader(context: Context) =
    ImageLoader.Builder(context)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
        .memoryCache {
            MemoryCache.Builder(context)
                .maxSizePercent(0.3)
                .strongReferencesEnabled(true)
                .build()
        }.crossfade(true)
        .build()