package com.example.marvelproject

import androidx.compose.ui.graphics.Color

data class Hero(
    val imageID : Int,
    val name : String,
    val colorBackground : Color,
    val imageLink : String,
    val info : String
)
