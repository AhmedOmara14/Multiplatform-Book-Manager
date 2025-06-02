package com.plcoding.bookpedia.book.presentation.book_list

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
import com.plcoding.bookpedia.book.domain.models.Book
import com.plcoding.bookpedia.book.presentation.book_list.component.BookSearchBar
import com.plcoding.bookpedia.core.presentation.DarkBlue
import com.plcoding.bookpedia.core.presentation.DesertWhite
import com.plcoding.bookpedia.core.presentation.SandYellow
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
                is BookListActions.onTabSelected -> {}
            }
            viewModel.onAction(it)
        })
}

@Composable
fun BookDetailScreenContent(
    state: BookListState, onAction: (BookListActions) -> Unit
) {
    val keyBoardOptions = androidx.compose.ui.platform.LocalSoftwareKeyboardController.current
    val pagerState = rememberPagerState { 2 }
    val scope = rememberCoroutineScope()
    val searchResultState = rememberLazyListState()
    val favoriteResultState = rememberLazyListState()

    LaunchedEffect(state.searchedBookList) {
        searchResultState.animateScrollToItem(0)
    }

    LaunchedEffect(state.favoriteBookList) {
        favoriteResultState.animateScrollToItem(0)
    }

    Column(
        modifier = Modifier.fillMaxSize().background(DarkBlue).statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BookSearchBar(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp),
            query = state.searchQuery,
            onQueryChange = {
                onAction(BookListActions.onSearchQueryChange(it))
            },
            onImeSearch = {
                keyBoardOptions?.hide()
            })

        Card(
            colors = CardDefaults.cardColors(
                containerColor = SandYellow, contentColor = DarkBlue
            ), modifier = Modifier.padding(top = 16.dp).fillMaxSize().background(
                color = DesertWhite, shape = RoundedCornerShape(
                    topStart = 50.dp,
                    topEnd = 50.dp,
                )
            ), shape = RoundedCornerShape(
                topStart = 50.dp,
                topEnd = 50.dp,
            )
        ) {
            Column(
                modifier = Modifier.background(Color.White).fillMaxSize()
            ) {
                TabRow(
                    selectedTabIndex = state.selectedIndex,
                    containerColor = Color.White,
                    contentColor = SandYellow,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                            color = SandYellow
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Tab(
                        selected = pagerState.currentPage == 0,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onAction(BookListActions.onTabSelected(0))
                            scope.launch {
                                pagerState.animateScrollToPage(0)
                            }
                        },
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.LightGray
                    ) {
                        Text(
                            text = "Searched",
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Tab(
                        selected = pagerState.currentPage == 1,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onAction(BookListActions.onTabSelected(1))
                            scope.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        },
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.LightGray
                    ) {
                        Text(
                            text = "Favorite",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    when (it) {
                        0 -> {
                            Box(
                                modifier = Modifier.fillMaxSize().padding(16.dp),
                                contentAlignment = androidx.compose.ui.Alignment.Center
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

                        1 -> {
                            Box(
                                modifier = Modifier.fillMaxSize().padding(16.dp),
                                contentAlignment = androidx.compose.ui.Alignment.Center
                            ) {
                                if (state.favoriteBookList.isEmpty())
                                    Text(
                                        modifier = Modifier.fillMaxSize(),
                                        text = "No books found",
                                        textAlign = TextAlign.Center,
                                        style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                                    )
                                else
                                    BookList(
                                        books = state.favoriteBookList,
                                        onBookClick = {
                                            onAction(BookListActions.onBookClick(it))
                                        }, scrollState = favoriteResultState
                                    )
                            }
                        }
                    }
                }
            }
        }
    }
}

