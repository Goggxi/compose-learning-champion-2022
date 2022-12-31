package com.goggxi.movies

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.goggxi.movies.ui.navigation.NavigationItem
import com.goggxi.movies.ui.navigation.Screen
import com.goggxi.movies.ui.screen.detail.DetailScreen
import com.goggxi.movies.ui.screen.detail.DetailViewModel
import com.goggxi.movies.ui.screen.favorite.FavoriteScreen
import com.goggxi.movies.ui.screen.home.HomeScreen
import com.goggxi.movies.ui.screen.home.HomeViewModel
import com.goggxi.movies.ui.screen.profile.ProfileScreen
import com.goggxi.movies.ui.screen.webview.WebViewScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoggxiMoviesApp(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    detailViewModel: DetailViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(modifier = modifier, bottomBar = {
        if (currentRoute != Screen.Detail.route && currentRoute != Screen.WebView.route) {
            BottomBar(navController)
        }
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(modifier = Modifier,
                    homeViewModel = homeViewModel,
                    navigateToDetail = { id, isTv ->
                        navController.navigate(Screen.Detail.createRoute(id = id, isTv = isTv))
                    })
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    modifier = Modifier,
                    navigateToWeb = { title, url ->
                        navController.navigate(Screen.WebView.createRoute(title = title, url = url))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("id") { type = NavType.LongType },
                    navArgument("is_tv") { type = NavType.BoolType }),
            ) {
                val id = it.arguments?.getLong("id")
                val isTv = it.arguments?.getBoolean("is_tv")
                if (isTv != null && id != null) {
                    DetailScreen(
                        id = id,
                        isTv = isTv,
                        detailViewModel = detailViewModel,
                        navigateBack = { navController.navigateUp() },
                    )
                }
            }
            composable(
                route = Screen.WebView.route,
                arguments = listOf(
                    navArgument("title") { type = NavType.StringType },
                    navArgument("url") { type = NavType.StringType }
                ),
            ) {
                val title = it.arguments?.getString("title")
                val url = it.arguments?.getString("url")
                if (title != null && url != null) {
                    WebViewScreen(
                        title = title,
                        url = url,
                        navigateBack = { navController.navigateUp() },
                    )
                }
            }

        }
    }
}


@Composable
private fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Outlined.Home,
                iconSelected = Icons.Rounded.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Outlined.Favorite,
                iconSelected = Icons.Rounded.Favorite,
                screen = Screen.Favorite
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Outlined.AccountCircle,
                iconSelected = Icons.Rounded.AccountCircle,
                screen = Screen.Profile
            ),
        )

        navigationItems.map { item ->
            NavigationBarItem(icon = {
                Icon(imageVector = if (currentRoute == item.screen.route) item.iconSelected else item.icon,
                    contentDescription = item.title,
                    tint = if (currentRoute == item.screen.route) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        with(MaterialTheme.colorScheme.onSurface) {
                            copy(alpha = 0.5f)
                        }
                    })
            }, label = {
                Text(text = item.title, color = if (currentRoute == item.screen.route) {
                    MaterialTheme.colorScheme.primary
                } else {
                    with(MaterialTheme.colorScheme.onSurface) {
                        copy(alpha = 0.5f)
                    }
                })
            }, selected = currentRoute == item.screen.route, onClick = {
                navController.navigate(item.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            })
        }
    }
}