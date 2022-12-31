package com.goggxi.movies.datasource

import com.goggxi.movies.datasource.network.NetworkingService
import javax.inject.Inject

class Repository @Inject constructor(private val networkingService: NetworkingService) {
    suspend fun getPopularMovies(page: Long) = networkingService.getPopularMovies(page = page)
    suspend fun getNowPlayingMovies(page: Long) = networkingService.getNowPlayingMovies(page = page)
    suspend fun getDetailMovie(id: Long) = networkingService.getMovieDetail(movieId = id)
    suspend fun getTVPopular(page: Long) = networkingService.getTVPopular(page = page)
    suspend fun getTVOnAir(page: Long) = networkingService.getTVOnAir(page = page)
    suspend fun getDetailTV(id: Long) = networkingService.getTVDetail(tvId = id)
}