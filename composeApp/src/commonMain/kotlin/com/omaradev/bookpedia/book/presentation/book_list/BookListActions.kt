package com.omaradev.bookpedia.book.presentation.book_list

import com.omaradev.bookpedia.book.domain.models.Book

sealed interface BookListActions {
    data class onSearchQueryChange(val querySearch: String) : BookListActions
    data class onBookClick(val book: Book) : BookListActions
}