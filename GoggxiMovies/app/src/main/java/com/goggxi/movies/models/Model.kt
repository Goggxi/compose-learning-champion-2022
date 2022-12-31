package com.goggxi.movies.models

import com.google.gson.annotations.SerializedName

data class Model(
    @SerializedName("id") val id: Long,

    @SerializedName("title") val title: String,

    @SerializedName("name") val name: String,

    @SerializedName("poster_path") val posterPath: String?,

    @SerializedName("overview") val overview: String,

    @SerializedName("release_date") val releaseDate: String,

    @SerializedName("genre_ids") val genreIDS: List<Long>,

    @SerializedName("backdrop_path") val backdropPath: String?,

    @SerializedName("popularity") val popularity: Double,

    @SerializedName("vote_count") val voteCount: Long,

    @SerializedName("vote_average") val voteAverage: Double
)
