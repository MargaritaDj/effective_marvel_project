package com.example.marvelproject.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.network.MarvelApi
import com.example.marvelproject.network.dto.HeroDtoResponse
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class OverviewViewModel : ViewModel() {

    val status = MutableLiveData<String>()
    val heroesListResponse = MutableLiveData<HeroDtoResponse>()
    val heroResponse = MutableLiveData<HeroDtoResponse>()

    init {
        getHeroes()
    }

    private fun getHeroes() {
        viewModelScope.launch {
            try {
                heroesListResponse.value = MarvelApi.retrofitService.getHeroes()
                status.value = "Success"
            } catch (e: UnknownHostException) {
                status.value = "Error: ${e.message}"
                println(status.value)
            }
        }
    }

    fun getHeroById(idHero: String?) {
        viewModelScope.launch {
            heroResponse.value = MarvelApi.retrofitService.getHeroById(idHero)
        }
    }
}
