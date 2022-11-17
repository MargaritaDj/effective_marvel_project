package com.example.marvelproject.network.model

import androidx.compose.ui.graphics.Color
import com.example.marvelproject.ui.theme.DarkRed
import com.squareup.moshi.Json

data class Hero(
    val id: String,
    val name : String,
    var description : String = "I am $name",
    val pathImage : String,
    var colorBackground : Color = DarkRed
)
