package com.example.marvelproject.overview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.marvelproject.repositories.HeroRepositoryLocal
import com.example.marvelproject.model.Hero
import com.example.marvelproject.repositories.HeroPagingSource
import com.example.marvelproject.repositories.HeroRepositoryRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
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

    val heroesPager = Pager(PagingConfig(pageSize = 10)){
        HeroPagingSource(repositoryLocal, repositoryRemote)
    }.flow.cachedIn(viewModelScope)

    init {
        println("INIT")
       // send(GetHeroesEvent(0))
        dataLocalHeroes.observeForever { heroes ->
            allHeroesDatabase.value = heroes
        }
    }

    fun send(event: MainEvent){
        println("SEND")
        when(event) {
            is GetHeroesEvent -> {
                getHeroes(event.page)
            }

            is GetHeroByIdEvent -> {
                getHeroById(event.id)
            }
        }
    }

    private fun getHeroes(page: Int) {
        println("VIEWMODEL")
     /*   viewModelScope.launch {
            try {
                println("PAGE: $page")
                val result = repositoryRemote.refreshHeroes(page)
                if (result.message.equals("Success")) {
                    val list = result.data?.data?.results?.map { it.toHero() } ?: listOf()
                    stateAllHeroes.value = MainState(list, stateAllHeroes.value.heroById,
                        stateAllHeroes.value.error)
                   // println("RESULT: $list")
                  //  repositoryLocal.deleteAll()
                    for (hero in stateAllHeroes.value.allHeroes) {
                        repositoryLocal.insertHero(hero)
                    }
                    println("ADDED DB " + stateAllHeroes.value.allHeroes )
                } else {
                    println("VIEWMODEL DB: ${allHeroesDatabase.value}")
                    var error = false
                    if(allHeroesDatabase.value!!.isEmpty()){
                        error = true
                    }
                    stateAllHeroes.value = MainState(allHeroesDatabase.value ?: listOf(),
                        stateAllHeroes.value.heroById, error)
                }
            } catch (e: IOException) {
                println(e.message)
            }

        }*/

    }

    private fun getHeroById(id: String) {
        println("GET BY ID: $id")
        viewModelScope.launch {
            stateHeroById.value = MainState(
                stateHeroById.value.allHeroes,
                repositoryLocal.getHeroById(id),
                stateHeroById.value.error)
        }
    }

}
