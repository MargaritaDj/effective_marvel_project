package com.example.marvelproject.network

import com.example.marvelproject.network.dto.HeroDtoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApiService{
    @GET("v1/public/characters")
    suspend fun getHeroes(
        @Query("limit") limit : Int = 40
    ): HeroDtoResponse

    @GET("/v1/public/characters/{id}")
    suspend fun getHeroById(
        @Path("id") id: String?
    ): HeroDtoResponse
}
