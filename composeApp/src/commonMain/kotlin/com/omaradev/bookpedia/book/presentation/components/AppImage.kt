package com.omaradev.bookpedia.book.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.book_error_2
import coil3.compose.SubcomposeAsyncImage
import com.omaradev.bookpedia.core.presentation.DarkBlue
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppImage(imageUrl: String) {
    Box(
        modifier = Modifier
            .size(110.dp)
            .clip(CircleShape)
            .background(Color.Transparent),
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = "Book Cover Image",
            modifier = Modifier.fillMaxSize(),
            loading = {
                CircularProgressIndicator(
                    color = DarkBlue,
                    modifier = Modifier.size(24.dp).padding(24.dp)
                )
            },
            error = {
                Image(
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth().background(DarkBlue.copy(.1f)).padding(16.dp),
                    painter = painterResource(Res.drawable.book_error_2),
                    contentDescription = null
                )
            },
            contentScale = ContentScale.Crop
        )
    }
}