package com.example.marvelproject.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.marvelproject.Hero
import com.example.marvelproject.ListHeroes
import com.example.marvelproject.R
import com.example.marvelproject.navigation.Routes

@Composable
fun InfoHero(navController: NavHostController, indexHero: Int?) {
    val hero = ListHeroes().listHeroes[indexHero ?: 0]

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = hero.imageLink,
                contentDescription = hero.name,
                placeholder = painterResource(R.drawable.logo_placeholder),
                error = painterResource(R.drawable.hero),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(
                    if (LocalConfiguration.current.orientation != Configuration.ORIENTATION_LANDSCAPE) {
                        LocalConfiguration.current.screenWidthDp.dp
                        LocalConfiguration.current.screenHeightDp.dp
                    } else {
                        LocalConfiguration.current.screenWidthDp.dp
                        400.dp
                    }
                )
            )
        }
        PrintDescriptionAboutHero(navController, hero, indexHero)

    }
}

@Composable
fun PrintDescriptionAboutHero(navController: NavHostController, hero: Hero, indexHero: Int?) {

    val fontWeightTitle = 800
    val sizeTitle = 40
    val sizeInfo = 20

    Box(
        modifier = Modifier
            .size(100.dp, 100.dp),
        contentAlignment = Alignment.TopStart
    ) {
        IconButton(
            onClick = {
                navController.navigate(Routes.ListHeroes.route + "/$indexHero")
            }
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = Color.White
            )
        }
    }

    Box(
        modifier = Modifier
            .padding(start = 20.dp, bottom = 30.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Column {
            Text(
                text = hero.name,
                color = Color.White,
                fontSize = sizeTitle.sp,
                fontWeight = FontWeight(fontWeightTitle)
            )

            Text(
                text = hero.info,
                color = Color.White,
                fontSize = sizeInfo.sp,
                overflow = TextOverflow.Clip,
                softWrap = true
            )
        }
    }
}
