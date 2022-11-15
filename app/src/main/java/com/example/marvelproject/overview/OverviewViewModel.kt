package com.example.marvelproject.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.network.MarvelApi
import com.example.marvelproject.network.dto.HeroDtoResponse
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    val status = MutableLiveData<String>()
    val heroesListResponse = MutableLiveData<HeroDtoResponse>()

    init {
        getHeroes()
    }

    private fun getHeroes() {
        viewModelScope.launch {
            try {
                heroesListResponse.value = MarvelApi.retrofitService.getHeroes()
                status.value = "Success"
            } catch (e: Exception) {
                status.value = "Error: ${e.message}"
            }
        }
    }
}