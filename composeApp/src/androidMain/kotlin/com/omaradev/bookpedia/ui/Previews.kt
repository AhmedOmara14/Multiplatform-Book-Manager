package com.omaradev.bookpedia.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.omaradev.bookpedia.book.domain.models.Book
import com.omaradev.bookpedia.book.presentation.book_details.BookDetailsScreenContent
import com.omaradev.bookpedia.book.presentation.book_list.BookDetailScreenContent
import com.omaradev.bookpedia.book.presentation.book_list.BookListState

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun PreviewBookDetailsScreenContent() {
    BookDetailsScreenContent(
        uiState = androidx.compose.runtime.mutableStateOf(
            com.omaradev.bookpedia.book.presentation.book_details.BookDetailsState(
                book = Book(
                    id = "",
                    title = "title",
                    imageUrl = "https://covers.openlibrary.org/b/id/8157718.jpg",
                    authors = listOf("author1", "author2"),
                    description = "Description",
                    languages = emptyList(),
                    firstPublishYear = "firstPublishDate",
                    averageRating = null,
                    ratingCount = null,
                    numPages = null,
                    numEditions = null
                ),
                isLoading = true
            )
        ),
        onAction = {

        }

    )
}

@Preview
@Composable
private fun PreviewBookDetailScreenContent() {
    BookDetailScreenContent(
        state = BookListState(
            searchedBookList = listOf(
                Book(
                    id = "ss",
                    title = "title",
                    imageUrl = "https://covers.openlibrary.org/b/id/8157718.jpg",
                    authors = listOf("author1", "author2"),
                    description = "Description",
                    languages = emptyList(),
                    firstPublishYear = "firstPublishDate",
                    averageRating = null,
                    ratingCount = null,
                    numPages = null,
                    numEditions = null
                ),
                Book(
                    id = "hhh",
                    title = "title",
                    imageUrl = "https://covers.openlibrary.org/b/id/8157718.jpg",
                    authors = listOf("author1", "author2"),
                    description = "Description",
                    languages = emptyList(),
                    firstPublishYear = "firstPublishDate",
                    averageRating = null,
                    ratingCount = null,
                    numPages = null,
                    numEditions = null
                )
            )
        ),
        onAction = {}
    )
}