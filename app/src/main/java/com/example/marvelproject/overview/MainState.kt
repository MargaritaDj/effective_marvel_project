package com.example.marvelproject.overview

import com.example.marvelproject.model.Hero

data class MainState(
    val allHeroes: List<Hero>,
    val heroById: Hero,
    val error: Boolean
)
