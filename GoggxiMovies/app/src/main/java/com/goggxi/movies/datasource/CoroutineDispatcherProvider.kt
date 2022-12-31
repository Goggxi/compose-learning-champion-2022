package com.goggxi.movies.datasource

import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProvider {
    fun io() = Dispatchers.IO
    fun default() = Dispatchers.Default
    fun main() = Dispatchers.Main
}