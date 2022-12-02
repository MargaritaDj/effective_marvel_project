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
import androidx.navigation.compose.rememberNavController
import com.example.marvelproject.model.Hero
import com.example.marvelproject.navigation.AppNavigation
import com.example.marvelproject.navigation.Routes
import com.example.marvelproject.overview.GetHeroesEvent
import com.example.marvelproject.overview.MainViewModel
import com.example.marvelproject.screens.info.InfoHero
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var pushBroadcastReceiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        val mainModel: MainViewModel by viewModels()
        super.onCreate(savedInstanceState)
        mainModel.send(GetHeroesEvent())
      /*  FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if(task.isSuccessful){
                println(task.result)
            }
        }*/

        var idHeroPush = ""
        pushBroadcastReceiver = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val extras = intent?.extras
                if(extras != null){
                    idHeroPush = extras.getString("id") ?: ""
                }
            }
        }


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
            AppNavigation(listHeroes.value, error.value, idHeroPush, this)

        }
    }

    override fun onDestroy() {
        unregisterReceiver(pushBroadcastReceiver)
        super.onDestroy()
    }
}







