package com.omaradev.bookpedia.book.presentation.book_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.bookpedia.book.domain.repository.BookRepository
import com.omaradev.bookpedia.core.domain.onError
import com.omaradev.bookpedia.core.domain.onSuccess
import com.omaradev.bookpedia.core.domain.toUiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookDetailsState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: BookDetailsActions) {
        when (action) {
            BookDetailsActions.onBackClick -> {}
            is BookDetailsActions.onFavoriteClick -> {}
        }
    }

    fun getBookDetails(id: String) = viewModelScope.launch {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        bookRepository
            .getBookDetails(id)
            .onSuccess { results ->
                _uiState.update {
                    it.copy(
                        book = results,
                        isLoading = false,
                    )
                }
            }
            .onError { remoteError ->
                _uiState.update {
                    it.copy(
                        book = null,
                        errorMessage = remoteError.toUiText(),
                        isLoading = false
                    )
                }
            }
    }
}