package com.example.marvelproject.network.dto

import com.example.marvelproject.model.Hero
import com.squareup.moshi.Json

data class Result(
    @Json(name = "description") val description: String,
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
) {

    fun toHero(): Hero {
        val path = thumbnail.path.substring(4)
        val extension = thumbnail.extension
        val url = "https$path.$extension"

        val hero = Hero(
            id = id,
            name = name,
            pathImage = url
        )

        if(description != ""){
            hero.description = description
        }

        return hero
    }
}
