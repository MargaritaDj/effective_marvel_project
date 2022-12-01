package com.example.marvelproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.marvelproject.navigation.AppNavigation
import com.example.marvelproject.model.Hero
import com.example.marvelproject.overview.GetHeroesEvent
import com.example.marvelproject.overview.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val mainModel: MainViewModel by viewModels()
        super.onCreate(savedInstanceState)
        mainModel.send(GetHeroesEvent())
        setContent {
            val listHeroes = rememberSaveable { mutableStateOf(listOf<Hero>()) }
            val error = rememberSaveable { mutableStateOf(false) }
            mainModel.stateAllHeroes.observe(this) { state ->
                if (state.error) {
                    error.value = true
                } else {
                    listHeroes.value = state.allHeroes
                }
            }
            AppNavigation(listHeroes.value, error.value, this)

        }
    }
}







