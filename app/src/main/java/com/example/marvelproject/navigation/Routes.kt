package com.example.marvelproject.navigation

sealed class Routes(val route: String) {
    object ListHeroes : Routes("ListHeroes")
    object InfoCurrentHero: Routes("InfoCurrentHero")
}
