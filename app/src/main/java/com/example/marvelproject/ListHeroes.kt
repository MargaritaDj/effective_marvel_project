package com.example.marvelproject

import androidx.compose.ui.graphics.Color
import com.example.marvelproject.ui.theme.Blue
import com.example.marvelproject.ui.theme.DarkRed
import com.example.marvelproject.ui.theme.Emerald
import com.example.marvelproject.ui.theme.Red0605
import com.example.marvelproject.ui.theme.Purple


data class ListHeroes (
    val url: String = "https://raw.githubusercontent.com/MargaritaDj/effective_android/feature/lab-2/Marvel/app/src" +
            "/main/res/drawable/",

    val listHeroes: List<Hero> = listOf(
        Hero(R.drawable.deadpool, "Deadpool", DarkRed, url + "deadpool.jpg",
        "Come on! Someone has to die for real! I mean, what is this, a Marvel Comic?!"),

        Hero(R.drawable.iron_man, "Iron man", Color.Red, url + "iron_man.jpg",
        "I AM IRON MAN"),

        Hero(R.drawable.cap, "Captain America", Color.Blue, url + "cap.jpg",
        "A man out of time, Steve Rogers continues his fight while leading the Avengers"),

        Hero(R.drawable.spiderman, "Spiderman", Red0605, url + "spiderman.jpg",
        "Friendly Neighborhood Spider-man"),

        Hero(R.drawable.doctor_strange, "Doctor Strange", Emerald, url + "doctor_strange.jpg",
        "Doctor Strange possesses incredible abilities as the Mastic of the Mystic Arts"),

        Hero(R.drawable.thor, "Thor", Blue, url + "thor.jpg",
        "Thor Odinson wields the power of the ancient Asgardians"),

        Hero(R.drawable.thanos, "Thanos", Purple, url + "thanos.jpg",
        "The Mad Titan Thanos quests across the universe in search of the Infinity Stones")
    )
)
