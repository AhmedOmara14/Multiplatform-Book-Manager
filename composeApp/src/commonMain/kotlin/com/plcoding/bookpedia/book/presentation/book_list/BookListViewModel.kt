package com.plcoding.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.bookpedia.book.domain.models.Book
import com.plcoding.bookpedia.book.domain.repository.BookRepository
import com.plcoding.bookpedia.core.domain.onError
import com.plcoding.bookpedia.core.domain.onSuccess
import com.plcoding.bookpedia.core.domain.toUiText
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookListState())
    val uiState = _uiState.onStart {
        if (cachedBooksList.isEmpty()){
            observeSearchQuery()
        }
    }.stateIn(
        viewModelScope,
        initialValue = _uiState.value,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(1)
    )

    private var cachedBooksList = emptyList<Book>()

    fun onAction(action: BookListActions) {
        when (action) {
            is BookListActions.onBookClick -> {

            }

            is BookListActions.onSearchQueryChange -> {
                _uiState.update {
                    it.copy(
                        searchQuery = action.querySearch
                    )
                }
            }

            is BookListActions.onTabSelected -> {
                _uiState.value.copy(
                    selectedIndex = action.index
                )
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun observeSearchQuery() {
        uiState.map {
            it.searchQuery
        }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _uiState.update {
                            it.copy(
                                searchedBookList = cachedBooksList,
                                errorMessage = null
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchBooks(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        bookRepository
            .searchBooks(query)
            .onSuccess { results->
                _uiState.update {
                    it.copy(
                        searchedBookList = results,
                        isLoading = false,
                    )
                }
            }
            .onError { remoteError->
                _uiState.update {
                    it.copy(
                        searchedBookList = emptyList(),
                        errorMessage = remoteError.toUiText(),
                        isLoading = false
                    )
                }
            }
    }
}