package com.goggxi.movies.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.goggxi.movies.models.Model

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalGlideComposeApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun SlidingPager(
    modifier: Modifier = Modifier,
    isTvShow: Boolean = false,
    movies: List<Model>,
    onClick: (Model) -> Unit,
) {
    val pagerState = rememberPagerState()

    HorizontalPager(pageCount = movies.size, state = pagerState) { page ->
        val movie = movies[page]
        Card(
            onClick = { onClick(movie) },
            modifier = Modifier
                .fillMaxSize()
                .height(240.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
        ) {
            Box {
                GlideImage(
                    model = "https://image.tmdb.org/t/p/w780${movie.backdropPath}",
                    contentDescription = movie.title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.0f),
                                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                                )
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                ) {
                    GlideImage(
                        model = "https://image.tmdb.org/t/p/w780${movie.posterPath}",
                        contentDescription = movie.title,
                        modifier = Modifier
                            .height(150.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Column(
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = if (isTvShow) movie.name else movie.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.background,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(bottom = 4.dp),
                        )
                        Text(
                            text = movie.overview,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.background,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                        )
                        Row(
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Icon(
                                tint = MaterialTheme.colorScheme.background,
                                contentDescription = "Star",
                                imageVector = Icons.Outlined.Star,
                                modifier = Modifier
                                    .padding(end = 4.dp)
                                    .size(24.dp),
                            )
                            Text(
                                text = movie.voteAverage.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.background,
                            )
                        }
                    }
                }
            }
        }
    }
}





