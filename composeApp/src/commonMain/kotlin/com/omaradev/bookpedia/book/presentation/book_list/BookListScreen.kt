package com.omaradev.bookpedia.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.omaradev.bookpedia.book.domain.models.Book
import com.omaradev.bookpedia.book.presentation.book_list.component.BookList
import com.omaradev.bookpedia.core.presentation.BackgroundColor
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = koinViewModel(), navigateToBookDetail: (Book) -> Unit = {}
) {
   val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    BookDetailScreenContent(
        state = uiState.value, onAction = {
            when (it) {
                is BookListActions.onBookClick -> {
                    navigateToBookDetail(it.book)
                }

                is BookListActions.onSearchQueryChange -> {}
            }
            viewModel.onAction(it)
        })

}

@Composable
fun BookDetailScreenContent(
    state: BookListState, onAction: (BookListActions) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundColor).statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (state.isLoading) CircularProgressIndicator()
        else {
            state.errorMessage?.let {
                Text(
                    text = state.errorMessage.asString(),
                    textAlign = TextAlign.Center,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
            } ?: run {
                if (state.searchedBookList.isEmpty())
                    Text(
                        modifier = Modifier.fillMaxSize(),
                        text = "No books found",
                        textAlign = TextAlign.Center,
                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                    )
                else
                    BookList(
                        books = state.searchedBookList,
                        onBookClick = {
                            onAction(BookListActions.onBookClick(it))
                        }
                    )
            }
        }
    }
}

