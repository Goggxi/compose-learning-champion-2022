package com.goggxi.movies.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.goggxi.movies.models.Model
import com.goggxi.movies.ui.theme.GoggxiMoviesTheme


@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Model,
    onClick: (Model) -> Unit
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .height(240.dp)
            .padding(top = 16.dp, start = 4.dp, end = 4.dp, bottom = 16.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = { onClick(movie) }
    ) {
        GlideImage(
            model = "https://image.tmdb.org/t/p/w780${movie.posterPath}",
            contentDescription = movie.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    GoggxiMoviesTheme {
        MovieItem(
            movie = Model(
                id = 1,
                title = "Movie Title",
                name = "Movie Name",
                overview = "Movie Overview",
                posterPath = "/vC324sdfcS313vh9QXwijLIHPJp.jpg",
                backdropPath = "/rQGBjWNveVeF8f2PGRtS85w9o9r.jpg",
                releaseDate = "2021-09-29",
                voteAverage = 8.5,
                voteCount = 1000,
                popularity = 1000.0,
                genreIDS = listOf(1, 2, 3)
            )
        ) {}
    }
}

