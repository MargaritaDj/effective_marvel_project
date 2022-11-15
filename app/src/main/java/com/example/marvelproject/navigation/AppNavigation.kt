package com.example.marvelproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelproject.network.model.Hero
import com.example.marvelproject.screens.InfoHero
import com.example.marvelproject.screens.ListAllHeroes

@Composable
fun AppNavigation(listHeroes: List<Hero>, isError: Boolean){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ListHeroes.route + "/{index}"){

        composable(Routes.ListHeroes.route + "/{index}"){ backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")
            ListAllHeroes(listHeroes, navController, index?.toInt(), isError)}

        composable(Routes.InfoCurrentHero.route + "/{index}"){ backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")
            InfoHero(navController, index?.toInt())}
    }
}
