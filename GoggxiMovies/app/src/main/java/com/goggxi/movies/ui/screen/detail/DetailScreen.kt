package com.goggxi.movies.ui.screen.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.goggxi.movies.models.Model
import com.goggxi.movies.ui.utils.PlatformsUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = viewModel(),
    id: Long,
    isTv: Boolean,
    navigateBack: () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = if (isTv) "Detail TV" else "Detail Movie",
                style = MaterialTheme.typography.titleLarge
            )
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Favorite, contentDescription = "Favorite"
                )
            }
        }, navigationIcon = {
            IconButton(onClick = { navigateBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                )
            }
        })
    }) { innerPadding ->
        Surface(
            modifier = modifier.padding(innerPadding)
        ) {
            if (isTv) {
                detailViewModel.fetchTv(id)
                BuildTV(detailViewModel = detailViewModel)
                return@Surface
            } else {
                detailViewModel.fetchMovie(id)
                BuildMovie(detailViewModel = detailViewModel)
                return@Surface
            }
        }

    }
}

@Composable
fun BuildMovie(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = viewModel(),
) {
    when (val state = detailViewModel.movieUiState.collectAsState().value) {
        is PlatformsUiState.Empty -> {
            Box(
                contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
            ) {
                Text(
                    text = "Empty", modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
            ) {

                CircularProgressIndicator()
            }
        }
        is PlatformsUiState.Error -> {
            Box(
                contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
            ) {

                Text(
                    text = "Error ${state.message}",
                    modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Success<Model> -> {
            BuildDetail(model = state.data, modifier = modifier, isTv = false)
        }
    }
}

@Composable
fun BuildTV(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = viewModel(),
) {
    when (val state = detailViewModel.tvUiState.collectAsState().value) {
        is PlatformsUiState.Empty -> {
            Box(
                contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
            ) {
                Text(
                    text = "Empty", modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Loading -> {
            Box(
                contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
            ) {

                CircularProgressIndicator()
            }

        }
        is PlatformsUiState.Error -> {
            Box(
                contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
            ) {
                Text(
                    text = "Error ${state.message}",
                    modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Success<Model> -> {
            BuildDetail(model = state.data, modifier = modifier, isTv = true)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BuildDetail(
    modifier: Modifier = Modifier,
    model: Model?,
    isTv: Boolean = false,
) {
    val data = model ?: return
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        GlideImage(
            model = "https://image.tmdb.org/t/p/w780${data.backdropPath}",
            contentDescription = data.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Card(
                modifier = modifier
                    .width(160.dp)
                    .height(240.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                GlideImage(
                    model = "https://image.tmdb.org/t/p/w780${data.posterPath}",
                    contentDescription = data.title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = modifier
                    .padding(end = 16.dp, top = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = if (isTv) data.name else data.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        contentDescription = "Star",
                        imageVector = Icons.Outlined.Star,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(24.dp),
                    )
                    Text(
                        text = data.voteAverage.toString(),
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            }
        }
        Text(
            text = data.overview, modifier = modifier.padding(
                top = 16.dp,
                end = 16.dp,
                start = 16.dp,
            )
        )
    }
}