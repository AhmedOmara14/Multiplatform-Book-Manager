package com.omaradev.bookpedia.book.presentation.book_details.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.bookpedia.book.presentation.components.shimmerEffect
import com.omaradev.bookpedia.core.presentation.DarkBlue

@Composable
fun AuthorItem(
    author: String
) {
    Card(
        modifier = Modifier.padding(horizontal = 4.dp).padding(bottom = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        shape = RoundedCornerShape(
            16.dp
        ),
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 10.dp),
            text = author,
            color = DarkBlue,
            fontSize = 16.sp
        )
    }

}

@Composable
fun AuthorItemShimmer() {
    Card(
        modifier = Modifier
            .padding(end = 8.dp, top = 8.dp)
            .shimmerEffect(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.2f))
    ) {
        Spacer(
            modifier = Modifier
                .width(80.dp)
                .height(24.dp)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}