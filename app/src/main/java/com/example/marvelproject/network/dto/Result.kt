package com.example.marvelproject.network.dto

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.marvelproject.network.model.Hero
import com.squareup.moshi.Json

data class Result(
    @Json(name = "description") val description: String,
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
) {

    fun toHero(context: Context): Hero {
        val path = thumbnail.path.substring(4)
        val extension = thumbnail.extension
        val url = "https$path.$extension"

        val hero = Hero(
            id = id,
            name = name,
            description = description,
            pathImage = url)

        getColorBackgroundImage(context, url, hero)

        return hero
    }


    private fun getColorBackgroundImage(context: Context, urlImage: String, hero: Hero){
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(urlImage)
            .target(
                onSuccess = { res ->
                    val bitmap = (res as BitmapDrawable).bitmap
                    Palette.from(bitmap).generate { palette ->
                        val colorArgb = palette?.darkVibrantSwatch?.rgb ?: hero.colorBackground.toArgb()
                        hero.colorBackground = Color(colorArgb)
                    }
                }
            )
            .allowHardware(false)
            .build()
        loader.enqueue(request)
    }


}