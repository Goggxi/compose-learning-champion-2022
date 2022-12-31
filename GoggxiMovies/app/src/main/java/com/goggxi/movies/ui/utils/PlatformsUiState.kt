package com.goggxi.movies.ui.utils

sealed class PlatformsUiState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : PlatformsUiState<T>(data)
    class Error<T>(message: String, data: T? = null) : PlatformsUiState<T>(data, message)
    class Loading<T>(data: T? = null) : PlatformsUiState<T>(data)
    class Empty<T>(data: T? = null) : PlatformsUiState<T>(data)
}