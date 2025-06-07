package com.omaradev.bookpedia.book.presentation.book_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.omaradev.bookpedia.book.domain.models.Book
import com.omaradev.bookpedia.book.presentation.components.AppImage
import com.omaradev.bookpedia.core.presentation.LightBlue
import com.omaradev.bookpedia.core.presentation.SandYellow
import kotlin.math.round

@Composable
fun BookListItem(
    onBookClick: (Book) -> Unit,
    deleteItem: () -> Unit,
    book: Book,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(32.dp), color = LightBlue.copy(alpha = .1f)
            ).clickable {
                onBookClick(book)
            }.clip(RoundedCornerShape(32.dp)).padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AppImage(imageUrl = book.imageUrl)

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            book.authors.firstOrNull()?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            book.averageRating?.let { rating ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${round(rating * 10) / 10.0}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = SandYellow
                    )
                }
            }

        }

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.align(Alignment.CenterVertically).size(25.dp).clickable {
                deleteItem()
            }
        )
    }
}