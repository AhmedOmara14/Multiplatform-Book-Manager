package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.book.domain.models.Book

sealed interface BookListActions {
    data class onSearchQueryChange(val querySearch: String) : BookListActions
    data class onBookClick(val book: Book) : BookListActions
    data class onTabSelected(val index: Int) : BookListActions
}