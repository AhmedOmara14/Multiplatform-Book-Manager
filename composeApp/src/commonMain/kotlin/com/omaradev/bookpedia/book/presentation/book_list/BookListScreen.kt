package com.omaradev.bookpedia.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.omaradev.bookpedia.book.domain.models.Book
import com.omaradev.bookpedia.book.presentation.book_list.component.BookList
import com.omaradev.bookpedia.book.presentation.book_list.component.BookSearchBar
import com.omaradev.bookpedia.core.presentation.BackgroundColor
import com.omaradev.bookpedia.core.presentation.DarkBlue
import com.omaradev.bookpedia.core.presentation.DesertWhite
import com.omaradev.bookpedia.core.presentation.SandYellow
import kotlinx.coroutines.launch
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
    val searchResultState = rememberLazyListState()

    LaunchedEffect(state.searchedBookList) {
        searchResultState.animateScrollToItem(0)
    }

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
                        },
                        scrollState = searchResultState
                    )
            }
        }
    }
}

