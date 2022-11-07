package com.example.marvelproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvelproject.screens.InfoHero
import com.example.marvelproject.screens.ListAllHeroes

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.ListHeroes.route + "/{index}"){

        composable(Routes.ListHeroes.route + "/{index}"){ backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")
            ListAllHeroes(navController, index?.toInt())}

        composable(Routes.InfoCurrentHero.route + "/{index}"){ backStackEntry ->
            val index = backStackEntry.arguments?.getString("index")
            InfoHero(navController, index?.toInt())}
    }
}
