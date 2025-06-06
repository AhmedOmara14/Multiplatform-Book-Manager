package com.omaradev.bookpedia.book.presentation.book_list

import com.omaradev.bookpedia.book.domain.models.Book
import com.omaradev.bookpedia.core.presentation.UiText

data class BookListState(
    val isLoading: Boolean = false,
    val searchQuery: String = "harry potter",
    val searchedBookList: List<Book> = emptyList(),
    val errorMessage: UiText? = null
)