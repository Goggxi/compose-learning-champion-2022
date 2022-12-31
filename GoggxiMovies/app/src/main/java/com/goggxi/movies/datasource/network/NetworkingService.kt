package com.goggxi.movies.datasource.network

import com.goggxi.movies.models.Model
import com.goggxi.movies.models.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkingService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "d8e957a69af1be921c9c4466f5661e87",
        @Query("page") page: Long = 1
    ): ResponseModel<List<Model>>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = "d8e957a69af1be921c9c4466f5661e87",
        @Query("page") page: Long = 1
    ): ResponseModel<List<Model>>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = "d8e957a69af1be921c9c4466f5661e87",
    ): Model

    @GET("tv/popular")
    suspend fun getTVPopular(
        @Query("api_key") apiKey: String = "d8e957a69af1be921c9c4466f5661e87",
        @Query("page") page: Long = 1
    ): ResponseModel<List<Model>>

    @GET("tv/on_the_air")
    suspend fun getTVOnAir(
        @Query("api_key") apiKey: String = "d8e957a69af1be921c9c4466f5661e87",
        @Query("page") page: Long = 1
    ): ResponseModel<List<Model>>

    @GET("tv/{tv_id}")
    suspend fun getTVDetail(
        @Path("tv_id") tvId: Long,
        @Query("api_key") apiKey: String = "d8e957a69af1be921c9c4466f5661e87",
    ): Model
}