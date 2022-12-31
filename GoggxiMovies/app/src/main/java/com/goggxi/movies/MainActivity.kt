package com.goggxi.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.goggxi.movies.ui.screen.detail.DetailViewModel
import com.goggxi.movies.ui.screen.home.HomeViewModel
import com.goggxi.movies.ui.theme.GoggxiMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            GoggxiMoviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    GoggxiMoviesApp(
                        modifier = Modifier,
                        homeViewModel = homeViewModel,
                        detailViewModel = detailViewModel
                    )
                }
            }
        }
    }
}