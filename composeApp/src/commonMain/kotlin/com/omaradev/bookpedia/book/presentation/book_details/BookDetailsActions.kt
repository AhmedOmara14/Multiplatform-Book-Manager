package com.omaradev.bookpedia.book.presentation.book_details

import com.omaradev.bookpedia.book.domain.models.Book

sealed interface BookDetailsActions {
    data object onBackClick : BookDetailsActions
    data class onFavoriteClick(val book: Book) : BookDetailsActions
}