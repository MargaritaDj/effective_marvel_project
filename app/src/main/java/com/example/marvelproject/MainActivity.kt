package com.example.marvelproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.marvelproject.model.Hero
import com.example.marvelproject.navigation.AppNavigation
import com.example.marvelproject.navigation.Routes
import com.example.marvelproject.overview.GetHeroesEvent
import com.example.marvelproject.overview.MainViewModel
import com.example.marvelproject.screens.info.InfoHero
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val mainModel: MainViewModel by viewModels()
        super.onCreate(savedInstanceState)
        //mainModel.send(GetHeroesEvent())

        setContent {
            println("SET CONTENT")
            val listHeroes = rememberSaveable { mutableStateOf(listOf<Hero>()) }
            val error = rememberSaveable { mutableStateOf(false) }
            //val listHeroes = rememberSaveable { mutableStateOf(listOf<Hero>()) }
           // val idHeroPush = rememberSaveable { mutableStateOf("") }
           // val error = rememberSaveable { mutableStateOf(false) }

           // val listBase = mainModel.heroesPager.collectAsLazyPagingItems().itemSnapshotList
          //  val listHeroes = listBase.items

          //  idHeroPush.value = savedInstanceState?.getString("id") ?: ""

            //val list = MutableLiveData(listBase.items)
            //listHeroes.value = listBase

           /* mainModel.stateAllHeroes.asLiveData().observe(this) { state ->
                if (state.error) {
                    error.value = true
                } else {
                    listHeroes.value = state.allHeroes
                }
            }*/

            AppNavigation(listHeroes.value, error.value, this)

        }
    }
}







