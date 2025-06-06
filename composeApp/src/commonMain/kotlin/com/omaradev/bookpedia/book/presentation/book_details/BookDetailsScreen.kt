package com.omaradev.bookpedia.book.presentation.book_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.book_error_2
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BookDetailsScreen(
    viewModel: BookDetailsViewModel = koinViewModel(),
    id: String,
    navigateUp: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getBookDetails(id)
    }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()


    BookDetailsScreenContent(
        uiState = uiState,
        onAction = {
            when (it) {
                is BookDetailsActions.onBackClick -> {
                    navigateUp()
                }

                is BookDetailsActions.onFavoriteClick -> {}
            }
        }
    )
}

@Composable
fun BookDetailsScreenContent(
    uiState: State<BookDetailsState>,
    onAction: (BookDetailsActions) -> Unit
) {
    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.padding(16.dp).size(40.dp)
                .background(
                    color = Color.Gray.copy(.1f),
                    shape = CircleShape
                ).clip(CircleShape).clickable {
                    onAction(BookDetailsActions.onBackClick)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                alignment = Alignment.Center,
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null
            )
        }


        val painter = rememberAsyncImagePainter(
            model = uiState.value.book?.imageUrl,
            onSuccess = {
                imageLoadResult = Result.success(it.painter)
            },
            onError = {
                imageLoadResult = Result.failure(it.result.throwable)
            }
        )
        imageLoadResult?.onFailure {
            Image(
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(Res.drawable.book_error_2),
                contentDescription = null
            )
        }
        imageLoadResult?.onSuccess {
            Image(
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
                painter = it,
                contentDescription = null
            )
        }

    }
}
