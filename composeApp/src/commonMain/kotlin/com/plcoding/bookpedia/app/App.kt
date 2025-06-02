package com.plcoding.bookpedia.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.plcoding.bookpedia.book.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.book.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = BookRoute.BookGraph
        ) {
            navigation<BookRoute.BookGraph>(
                startDestination = BookRoute.BookList
            ) {
                composable<BookRoute.BookList> {
                    BookListScreenRoot {
                        navController.navigate(BookRoute.BookDetails(it.id))
                    }
                }

                composable<BookRoute.BookDetails> { navBackStackEntry ->
                    val args = navBackStackEntry.toRoute<BookRoute.BookDetails>()

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Book Details: ${args.bookId}",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }

                }
            }
        }
    }

}