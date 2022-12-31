package com.goggxi.movies.ui.screen.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.goggxi.movies.models.Model
import com.goggxi.movies.ui.component.MovieItem
import com.goggxi.movies.ui.component.SearchBar
import com.goggxi.movies.ui.component.Section
import com.goggxi.movies.ui.component.SlidingPager
import com.goggxi.movies.ui.utils.PlatformsUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    navigateToDetail: (id: Long, isTv: Boolean) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = {},
            actions = { SearchBar() },
            modifier = modifier
                .padding(top = 10.dp, bottom = 10.dp, start = 0.dp, end = 0.dp)
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        )
    }) { innerPadding ->
        BuildList(
            modifier = modifier.padding(innerPadding),
            homeViewModel = homeViewModel,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
fun BuildList(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    navigateToDetail: (id: Long, isTv: Boolean) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            Section(title = "Popular Movie", content = {
                BuildPopularMovie(
                    homeViewModel = homeViewModel,
                    navigateToDetail = navigateToDetail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            }) {}
        }
        item {
            Section(title = "Now Playing", content = {
                BuildNowPlayingMovie(
                    homeViewModel = homeViewModel,
                    navigateToDetail = navigateToDetail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                )
            }) {}
        }
        item {
            Section(title = "Popular TV Show", content = {
                BuildPopularTv(
                    homeViewModel = homeViewModel,
                    navigateToDetail = navigateToDetail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                )
            }) {}
        }
        item {
            Section(title = "TV Airing Today", content = {
                BuildTVAiringToday(
                    homeViewModel = homeViewModel,
                    navigateToDetail = navigateToDetail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                )
            }) {}
        }
    }
}

@Composable
fun BuildPopularMovie(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    navigateToDetail: (id: Long, isTv: Boolean) -> Unit,
) {
    when (val state = homeViewModel.popularUiState.collectAsState().value) {
        is PlatformsUiState.Empty -> {
            CardItemWithoutSuccess {
                Text(
                    text = "Empty Popular Movies",
                    modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Loading -> {
            CardItemWithoutSuccess {
                CircularProgressIndicator()
            }
        }
        is PlatformsUiState.Error -> {
            CardItemWithoutSuccess {
                Text(
                    text = "Error ${state.message}",
                    modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Success<List<Model>> -> {
            SlidingPager(modifier = modifier,
                movies = state.data ?: emptyList(),
                onClick = { navigateToDetail(it.id, false) })
        }
    }
}

@Composable
fun BuildNowPlayingMovie(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    navigateToDetail: (id: Long, isTv: Boolean) -> Unit,
) {
    when (val state = homeViewModel.nowPlaying.collectAsState().value) {
        is PlatformsUiState.Empty -> {
            CardItemWithoutSuccess {
                Text(
                    text = "Empty Now Playing Movies",
                    modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Loading -> {
            CardItemWithoutSuccess {
                CircularProgressIndicator()
            }
        }
        is PlatformsUiState.Error -> {
            CardItemWithoutSuccess {
                Text(
                    text = "Error ${state.message}",
                    modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Success<List<Model>> -> {
            LazyRow(modifier = modifier) {
                item { Spacer(modifier = Modifier.width(12.dp)) }
                val movies = state.data ?: emptyList()
                items(movies.size) { index ->
                    val movie = movies[index]
                    MovieItem(movie = movie) { navigateToDetail(it.id, false) }
                }
                item { Spacer(modifier = Modifier.width(12.dp)) }
            }
        }
    }
}

@Composable
fun BuildPopularTv(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    navigateToDetail: (id: Long, isTv: Boolean) -> Unit,
) {
    when (val state = homeViewModel.tvPopular.collectAsState().value) {
        is PlatformsUiState.Empty -> {
            CardItemWithoutSuccess {
                Text(
                    text = "Empty Popular TV", modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Loading -> {
            CardItemWithoutSuccess {
                CircularProgressIndicator()
            }
        }
        is PlatformsUiState.Error -> {
            CardItemWithoutSuccess {
                Text(
                    text = "Error ${state.message}",
                    modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Success<List<Model>> -> {
            SlidingPager(
                modifier = modifier,
                movies = state.data ?: emptyList(),
                onClick = { navigateToDetail(it.id, true) },
                isTvShow = true,
            )
        }
    }
}

@Composable
fun BuildTVAiringToday(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    navigateToDetail: (id: Long, isTv: Boolean) -> Unit,
) {
    when (val state = homeViewModel.tvAiringToday.collectAsState().value) {
        is PlatformsUiState.Empty -> {
            CardItemWithoutSuccess {
                Text(
                    text = "Empty Airing Today TV",
                    modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Loading -> {
            CardItemWithoutSuccess {
                CircularProgressIndicator()
            }
        }
        is PlatformsUiState.Error -> {
            CardItemWithoutSuccess {
                Text(
                    text = "Error ${state.message}",
                    modifier = modifier.wrapContentSize(Alignment.Center)
                )
            }
        }
        is PlatformsUiState.Success<List<Model>> -> {
            LazyRow(modifier = modifier) {
                item { Spacer(modifier = Modifier.width(12.dp)) }
                val movies = state.data ?: emptyList()
                items(movies.size) { index ->
                    val movie = movies[index]
                    MovieItem(movie = movie) { navigateToDetail(it.id, true) }
                }
                item { Spacer(modifier = Modifier.width(12.dp)) }
            }
        }
    }
}

@Composable
fun CardItemWithoutSuccess(
    modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Box(
            contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()
        ) {
            content()
        }
    }
}


