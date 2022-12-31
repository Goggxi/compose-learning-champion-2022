package com.goggxi.movies.models

import com.google.gson.annotations.SerializedName


data class ResponseModel<T>(
    @SerializedName("page") val page: Long,

    @SerializedName("results") val results: T,

    @SerializedName("total_results") val totalResults: Long,

    @SerializedName("total_pages") val totalPages: Long
)
