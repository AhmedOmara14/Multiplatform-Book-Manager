package com.plcoding.bookpedia.book.presentation.book_details

import com.plcoding.bookpedia.book.domain.models.Book
import com.plcoding.bookpedia.core.presentation.UiText

data class BookDetailsState(
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false,
    val book: Book? = null,
    val errorMessage: UiText? = null
)