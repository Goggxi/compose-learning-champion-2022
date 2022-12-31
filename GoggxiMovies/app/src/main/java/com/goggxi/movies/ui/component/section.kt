package com.goggxi.movies.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
    seeAll: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .wrapContentSize(Alignment.CenterStart)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = { seeAll() },
                modifier = Modifier
                    .padding(end = 16.dp)
                    .wrapContentSize(Alignment.CenterEnd)
            ) {
                Text(text = "See All")
            }
        }
        content()
    }
}