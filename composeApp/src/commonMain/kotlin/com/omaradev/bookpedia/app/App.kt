package com.omaradev.bookpedia.app

import androidx.compose.foundation.layout.Arrangement
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
import com.omaradev.bookpedia.book.presentation.book_details.BookDetailsScreen
import com.omaradev.bookpedia.book.presentation.book_list.BookListScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

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

                    BookDetailsScreen(
                        id = args.bookId
                    )
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