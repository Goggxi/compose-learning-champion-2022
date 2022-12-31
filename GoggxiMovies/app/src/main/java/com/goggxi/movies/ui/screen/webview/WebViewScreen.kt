package com.goggxi.movies.ui.screen.webview

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen(
    modifier: Modifier = Modifier,
    title: String,
    url: String,
    navigateBack: () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                IconButton(onClick = { navigateBack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                    )
                }
            },
        )
    }, content = {
        MyContent(
            modifier = modifier.padding(it), url = if (title == "linkedin") {
                "https://www.linkedin.com/in/goggxi/"
            } else {
                "https://${title}.com/${url}"
            }
        )
    })
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun MyContent(
    modifier: Modifier = Modifier,
    url: String,
) {
    AndroidView(
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        },
        update = {
            it.loadUrl(url)
        },

        )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WebViewScreen(title = "WebView", url = "https://www.google.com", navigateBack = {})
}