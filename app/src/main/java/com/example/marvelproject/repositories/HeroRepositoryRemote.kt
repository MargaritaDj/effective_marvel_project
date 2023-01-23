package com.example.marvelproject.repositories

import com.example.marvelproject.network.MarvelApiService
import com.example.marvelproject.network.dto.HeroDtoResponse
import com.example.marvelproject.network.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class HeroRepositoryRemote @Inject constructor(
    private val marvelApiService: MarvelApiService
) {
    suspend fun refreshHeroes(page: Int): Response<HeroDtoResponse> =
        withContext(Dispatchers.IO) {
            try{
                val response = marvelApiService.getHeroes(page*10)
                return@withContext Response.Success("Success", response)
            } catch (e: IOException){
                return@withContext Response.Error(e.message)
            }
        }
}

