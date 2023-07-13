package com.twoup.personalfinance.features.note.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.intercept.Interceptor
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.model.ImageResult
import com.seiko.imageloader.model.NullRequestData
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
fun ImageItem(
    url: String,
    modifier: Modifier
) {
//    Box(Modifier.aspectRatio(1f), Alignment.Center) {
        val request = remember(url,) {
            ImageRequest {
                data(url)
                addInterceptor(NullDataInterceptor)
            }
        }
        val painter = rememberAsyncImagePainter(request)
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier,
        )
//    }
}

object NullDataInterceptor : Interceptor {

    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val data = chain.request.data
        if (data === NullRequestData || data is String && data.isEmpty()) {
            return ImageResult.Painter(
                request = chain.request,
                painter = EmptyPainter,
            )
        }
        return chain.proceed(chain.request)
    }

    private object EmptyPainter : Painter() {
        override val intrinsicSize: Size get() = Size.Unspecified
        override fun DrawScope.onDraw() {}
    }
}