package com.example.marvelproject.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelproject.model.Hero
import com.example.marvelproject.screens.info.InfoHero
import com.example.marvelproject.screens.heroes.ListAllHeroes

@Composable
fun AppNavigation(listHeroes: List<Hero>, error: Boolean, context: Context){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ListHeroes.route + "/{id}"){

        composable(Routes.ListHeroes.route + "/{id}"){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            ListAllHeroes(listHeroes, navController, id, error, context)
        }

        composable(Routes.InfoCurrentHero.route + "/{id}"){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            InfoHero(navController, id)
        }
    }
}
