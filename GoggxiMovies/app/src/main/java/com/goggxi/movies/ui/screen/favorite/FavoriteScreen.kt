package com.goggxi.movies.ui.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.goggxi.movies.ui.component.SearchBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favorite",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                modifier = modifier
                    .padding(top = 10.dp, bottom = 10.dp, start = 0.dp, end = 0.dp)
                    .fillMaxWidth()
                    .heightIn(min = 48.dp)
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "lock",
                tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
                modifier = modifier
                    .size(80.dp),
            )
            Text(
                text = "Favorite Screen Has Not Been Implemented Yet :)",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}