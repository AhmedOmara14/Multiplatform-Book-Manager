package com.plcoding.bookpedia.book.presentation.book_list

import com.plcoding.bookpedia.book.domain.models.Book
import com.plcoding.bookpedia.core.presentation.UiText

data class BookListState(
    val selectedIndex: Int = 0,
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val searchedBookList: List<Book> = emptyList(),
    val favoriteBookList: List<Book> = emptyList(),
    val errorMessage: UiText? = null
)