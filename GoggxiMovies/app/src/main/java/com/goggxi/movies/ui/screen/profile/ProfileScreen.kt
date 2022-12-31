package com.goggxi.movies.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goggxi.movies.ui.theme.GoggxiMoviesTheme
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateToWeb: (title: String, url: String) -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Profile", style = MaterialTheme.typography.titleLarge
                )
            },
            modifier = modifier
                .padding(top = 10.dp, bottom = 10.dp, start = 0.dp, end = 0.dp)
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        )
    }) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = com.goggxi.movies.R.drawable.rifkan),
                contentDescription = null,
                modifier = modifier
                    .height(200.dp)
                    .width(200.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Moh Rifkan",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "goggxi@gmail.com",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 10.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                IconButtonCircle(onClick = { navigateToWeb("github", "goggxi") }) {
                    FaIcon(
                        faIcon = FaIcons.Github,
                        size = 24.dp,
                        tint = MaterialTheme.colorScheme.background,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButtonCircle(onClick = { navigateToWeb("instagram", "moh_rifkan") }) {
                    FaIcon(
                        faIcon = FaIcons.Instagram,
                        size = 24.dp,
                        tint = MaterialTheme.colorScheme.background,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButtonCircle(onClick = { navigateToWeb("facebook", "Rifkan.iphank") }) {
                    FaIcon(
                        faIcon = FaIcons.Facebook,
                        size = 24.dp,
                        tint = MaterialTheme.colorScheme.background,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                IconButtonCircle(onClick = { navigateToWeb("linkedin", "goggxi") }) {
                    FaIcon(
                        faIcon = FaIcons.Linkedin,
                        size = 24.dp,
                        tint = MaterialTheme.colorScheme.background,
                    )
                }
            }
        }
    }
}

@Composable
fun IconButtonCircle(
    modifier: Modifier = Modifier, onClick: () -> Unit, content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(50.dp),
        shape = CircleShape,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.background
        )
    ) {
        content()
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfileScreenPreview() {
    GoggxiMoviesTheme {
        ProfileScreen(navigateToWeb = { _, _ -> })
    }
}