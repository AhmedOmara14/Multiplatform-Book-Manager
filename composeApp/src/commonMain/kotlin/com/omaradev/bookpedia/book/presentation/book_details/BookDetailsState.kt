package com.omaradev.bookpedia.book.presentation.book_details

import com.omaradev.bookpedia.book.domain.models.Book
import com.omaradev.bookpedia.core.presentation.UiText

data class BookDetailsState(
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val book: Book? = null,
    val errorMessage: UiText? = null
)