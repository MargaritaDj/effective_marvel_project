package com.example.marvelproject.network

import com.example.marvelproject.network.dto.HeroDtoResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.ZoneOffset


private const val BASE_URL =
    "https://gateway.marvel.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface MarvelApiService{
    @GET("v1/public/characters")
    suspend fun getHeroes(
        @Query("limit") limit : Int = 30,
        @Query("ts") ts : String = "1",
        @Query("apikey") apikey : String = "436bc8f71067511b49988b64e64f2200",
        @Query("hash") hash : String = "617aa3b6e273108093ffe440c9e08d19",
    ): HeroDtoResponse
}

object MarvelApi{
    val retrofitService : MarvelApiService by lazy {retrofit.create(MarvelApiService::class.java)}
}

