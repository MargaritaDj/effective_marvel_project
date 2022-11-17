package com.example.marvelproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModelProvider
import com.example.marvelproject.navigation.AppNavigation
import com.example.marvelproject.network.model.Hero
import com.example.marvelproject.overview.OverviewViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContent {
            val listHeroes = rememberSaveable { mutableStateOf(listOf<Hero>()) }
            val isError = rememberSaveable { mutableStateOf(false) }

            viewModel.heroesListResponse.observe(this) {
                listHeroes.value = viewModel.heroesListResponse.value?.data?.results
                    ?.map{ it.toHero(this) } ?: listOf()
            }
            viewModel.status.observe(this) {
                if(!viewModel.status.value.equals("Success")){
                    isError.value = true
                }
            }
            AppNavigation(listHeroes.value, isError.value)
        }
    }
}







