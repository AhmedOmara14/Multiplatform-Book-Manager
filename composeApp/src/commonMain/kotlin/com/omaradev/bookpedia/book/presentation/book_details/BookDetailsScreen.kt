package com.omaradev.bookpedia.book.presentation.book_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.book_error_2
import coil3.compose.rememberAsyncImagePainter
import com.omaradev.bookpedia.book.presentation.book_details.component.AuthorItem
import com.omaradev.bookpedia.book.presentation.book_details.component.AuthorItemShimmer
import com.omaradev.bookpedia.book.presentation.components.AppImage
import com.omaradev.bookpedia.book.presentation.components.shimmerEffect
import com.omaradev.bookpedia.core.presentation.DarkBlue
import com.omaradev.bookpedia.core.presentation.SandYellow
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
    val isLoading = uiState.value.isLoading

    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Blurred background image
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val painter = rememberAsyncImagePainter(
                model = uiState.value.book?.imageUrl,
                onSuccess = {
                    imageLoadResult = Result.success(it.painter)
                },
                onError = {
                    imageLoadResult = Result.failure(it.result.throwable)
                }
            )
            imageLoadResult?.let { imageLoadResult ->
                Image(
                    painter = if (imageLoadResult.isFailure) painterResource(Res.drawable.book_error_2) else painter,
                    contentDescription = "Blurred Placeholder",
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .blur(16.dp),
                    contentScale = ContentScale.Crop,
                    alpha = 0.6f
                )
            }
        }

        // Back button
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .background(
                    color = Color.Gray.copy(.1f),
                    shape = CircleShape
                )
                .clip(CircleShape)
                .clickable {
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

        Card(
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            shape = RoundedCornerShape(
                topStart = 35.dp,
                topEnd = 35.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp)
                .background(
                    shape = RoundedCornerShape(
                        topStart = 35.dp,
                        topEnd = 35.dp
                    ),
                    color = Color.White
                )
        ) {
            LazyColumn(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (
                            isLoading
                        ) {
                            Box(
                                modifier = Modifier.then(
                                    if (isLoading) Modifier.size(110.dp)
                                        .shimmerEffect() else Modifier
                                )
                            )
                        } else
                            AppImage(
                                uiState.value.book?.imageUrl ?: ""
                            )
                    }
                }

                // Title and Rating Row
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (isLoading) {
                            Spacer(
                                modifier = Modifier.height(24.dp)
                                    .then(if (isLoading) Modifier.shimmerEffect() else Modifier)
                                    .fillMaxWidth(0.7f)
                            )
                        } else {
                            Text(
                                modifier = Modifier.weight(2f),
                                text = uiState.value.book?.title ?: "",
                                color = DarkBlue,
                                style = MaterialTheme.typography.headlineMedium,
                            )
                        }

                        Row(
                            modifier = Modifier
                                .weight(.5f)
                                .padding(start = 16.dp)
                                .then(if (isLoading) Modifier.shimmerEffect() else Modifier),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            if (isLoading) {
                                Spacer(modifier = Modifier.size(24.dp)) // Placeholder for rating
                            } else {
                                Text(
                                    text = "4.5",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = SandYellow
                                )
                            }
                        }
                    }
                }

                // Description
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .then(if (isLoading) Modifier.shimmerEffect() else Modifier)
                    ) {
                        if (isLoading) {
                            Spacer(modifier = Modifier.height(16.dp).fillMaxWidth())
                            Spacer(modifier = Modifier.height(16.dp).fillMaxWidth(0.8f))
                            Spacer(modifier = Modifier.height(16.dp).fillMaxWidth(0.6f))
                        } else {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = uiState.value.book?.description ?: "",
                                color = DarkBlue.copy(.6f),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

                // Authors
                item {
                    if (uiState.value.book?.authors?.isNotEmpty() == true || isLoading) {
                        if (isLoading) {
                            Spacer(
                                modifier = Modifier.height(40.dp).padding(vertical = 8.dp)
                                    .fillMaxWidth()
                                    .then(if (isLoading) Modifier.shimmerEffect() else Modifier)
                                    .fillMaxWidth(0.7f)
                            )
                        } else
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = "Author",
                                color = DarkBlue,
                                style = MaterialTheme.typography.headlineSmall
                            )

                        LazyRow(modifier = Modifier.fillMaxWidth()) {
                            if (isLoading) {
                                items(3) { // Show a few shimmer author items
                                    AuthorItemShimmer() // Create a shimmer version of AuthorItem
                                }
                            } else {
                                items(uiState.value.book?.authors!!) {
                                    AuthorItem(author = it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

