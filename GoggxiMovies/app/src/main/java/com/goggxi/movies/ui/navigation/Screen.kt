package com.goggxi.movies.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object Detail : Screen("home/{id}/{is_tv}") {
        fun createRoute(id: Long, isTv: Boolean = false) = "home/$id/$isTv"
    }

    object WebView : Screen("profile/web/{title}/{url}") {
        fun createRoute(title: String, url: String) = "profile/web/$title/$url"
    }
}