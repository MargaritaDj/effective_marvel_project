package com.example.marvelproject.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.database.HeroRepository
import com.example.marvelproject.model.Hero
import kotlinx.coroutines.launch

class DatabaseViewModel(private val repository: HeroRepository): ViewModel() {
    val allHeroes: LiveData<List<Hero>> = repository.allHeroes.asLiveData()

    fun insert(hero: Hero) = viewModelScope.launch {
        repository.insertHero(hero)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}
