package com.example.marvelproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.marvelproject.database.HeroApplication
import com.example.marvelproject.navigation.AppNavigation
import com.example.marvelproject.model.Hero
import com.example.marvelproject.overview.DatabaseViewModel
import com.example.marvelproject.overview.OverviewViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = OverviewViewModel()
        val viewModelDB = DatabaseViewModel((application as HeroApplication).repository)
        super.onCreate(savedInstanceState)
        setContent {
            val listHeroes = rememberSaveable { mutableStateOf(listOf<Hero>()) }
            val isError = rememberSaveable { mutableStateOf(false) }

            viewModel.heroesListResponse.observe(this) {
                listHeroes.value = viewModel.heroesListResponse.value?.data?.results
                    ?.map{ it.toHero(this) } ?: listOf()
                viewModelDB.deleteAll()
                for(hero in listHeroes.value){
                    viewModelDB.insert(hero)
                }
            }
            viewModel.status.observe(this) {
                if(!viewModel.status.value.equals("Success")){
                    isError.value = true
                }
            }
            viewModelDB.allHeroes.observe(this) { heroes ->
                println("Size = " + heroes.size)
                if(isError.value && heroes.isNotEmpty() && listHeroes.value.isEmpty()){
                    listHeroes.value = heroes ?: listOf()
                    println("List: " + listHeroes.value.size)
                    isError.value = false
                }
            }
            AppNavigation(listHeroes.value)
        }
    }
}







