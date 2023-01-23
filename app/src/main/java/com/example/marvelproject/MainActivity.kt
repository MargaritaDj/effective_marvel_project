package com.example.marvelproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.asLiveData
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
            mainModel.stateAllHeroes.asLiveData().observe(this) { state ->
                if (state.error) {
                    error.value = true
                } else {
                    listHeroes.value = state.allHeroes
                }
            }
            AppNavigation(listHeroes.value, error.value, this)

        }
    }

   /* @Composable
    fun splashScreen(){
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(R.drawable.logo_placeholder),
                contentDescription = "splash",
                contentScale = ContentScale.Fit
            )
        }
    }*/
}







