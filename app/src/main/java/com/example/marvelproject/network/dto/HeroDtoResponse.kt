package com.example.marvelproject.network.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class HeroDtoResponse(
    @Json(name = "data") val data : Data,
)
