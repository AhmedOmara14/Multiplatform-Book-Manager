package com.plcoding.bookpedia.book.presentation.book_details

import com.plcoding.bookpedia.book.domain.models.Book

sealed interface BookDetailsActions {
    data object onBackClick : BookDetailsActions
    data class onFavoriteClick(val book: Book) : BookDetailsActions
}