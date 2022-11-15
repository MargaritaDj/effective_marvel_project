package com.example.marvelproject.network.dto

import com.squareup.moshi.Json

data class Data(
    @Json(name = "count") val count: String,
    @Json(name = "limit") val limit: String,
    @Json(name = "offset") val offset: String,
    @Json(name = "results") val results: List<Result>,
)