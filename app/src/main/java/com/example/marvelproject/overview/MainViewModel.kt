package com.example.marvelproject.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.marvelproject.repositories.HeroRepositoryLocal
import com.example.marvelproject.model.Hero
import com.example.marvelproject.repositories.HeroRepositoryRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryLocal: HeroRepositoryLocal,
    private val repositoryRemote: HeroRepositoryRemote
) : ViewModel() {
    var stateAllHeroes = MutableStateFlow(MainState(listOf(), Hero(), false))
    val stateHeroById = MutableStateFlow(stateAllHeroes.value)

    private var dataLocalHeroes : LiveData<List<Hero>> = repositoryLocal.allHeroes.asLiveData()
    private var allHeroesDatabase = MutableLiveData<List<Hero>>(listOf())

    init {
        dataLocalHeroes.observeForever { heroes ->
            allHeroesDatabase.value = heroes
        }
    }

    fun send(event: MainEvent){
        when(event) {
            is GetHeroesEvent -> {
                getHeroes()
            }

            is GetHeroByIdEvent -> {
                getHeroById(event.id)
            }
        }
    }

    private fun getHeroes() {
        viewModelScope.launch {
            try {
                val result = repositoryRemote.refreshHeroes()
                if (result.message.equals("Success")) {
                    println(allHeroesDatabase.value?.size)
                    val list = result.data?.data?.results?.map { it.toHero() } ?: listOf()
                    stateAllHeroes.value = MainState(list, stateAllHeroes.value!!.heroById,
                        stateAllHeroes.value!!.error)
                    repositoryLocal.deleteAll()
                    for (hero in stateAllHeroes.value!!.allHeroes) {
                        repositoryLocal.insertHero(hero)
                    }
                } else {
                    var error = false
                    if(allHeroesDatabase.value!!.isEmpty()){
                        error = true
                    }
                    stateAllHeroes.value = MainState(allHeroesDatabase.value ?: listOf(),
                        stateAllHeroes.value!!.heroById, error)
                }
            } catch (e: IOException) {
                println(e.message)
            }

        }
    }

    private fun getHeroById(id: String) {
        viewModelScope.launch {
            val result = repositoryRemote.getHeroById(id)
            if (result.message.equals("Success")) {
                stateHeroById.value = MainState(stateHeroById.value!!.allHeroes,
                    result.data?.data?.results?.get(0)?.toHero() ?: Hero(), stateHeroById.value!!.error)
            } else {
            stateHeroById.value = MainState(stateHeroById.value!!.allHeroes,
                repositoryLocal.getHeroById(id), stateHeroById.value!!.error)
            }
        }
    }

}
