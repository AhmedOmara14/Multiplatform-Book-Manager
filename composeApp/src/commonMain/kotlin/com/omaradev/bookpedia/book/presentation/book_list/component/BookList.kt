package com.omaradev.bookpedia.book.presentation.book_list.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.omaradev.bookpedia.book.domain.models.Book
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay

@Composable
fun BookList(
    books: List<Book> = emptyList(),
    onBookClick: (Book) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    var allItems by remember { mutableStateOf(persistentListOf<Book>()) }
    val removingItemIds = remember { mutableStateOf(setOf<String>()) }
    val lazyListState = rememberLazyListState()

    LaunchedEffect(Unit) {
        val initialItems = books.toPersistentList()
        allItems = initialItems
    }

    LazyColumn(
        modifier = modifier.fillMaxSize().padding(8.dp),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = allItems,
            key = { it.id }
        ) { book ->
            val isBeingRemoved = book.id in removingItemIds.value
            val animatedAlpha by animateFloatAsState(
                targetValue = if (isBeingRemoved) 0f else 1f,
                animationSpec = tween(durationMillis = 400)
            )
            val animatedScale by animateFloatAsState(
                targetValue = if (isBeingRemoved) 0.0f else 1f,
                animationSpec = tween(durationMillis = 400)
            )

            LaunchedEffect(isBeingRemoved) {
                if (isBeingRemoved) {
                    delay(300)
                    allItems = allItems.remove(book)
                    removingItemIds.value -= book.id
                }
            }

            if (animatedAlpha > 0.01f) {
                BookListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            alpha = animatedAlpha
                            scaleX = animatedScale
                            scaleY = animatedScale
                        },
                    book = book,
                    onBookClick = onBookClick,
                    deleteItem = {
                        removingItemIds.value = removingItemIds.value + book.id
                    },
                )
            }

        }
    }
}