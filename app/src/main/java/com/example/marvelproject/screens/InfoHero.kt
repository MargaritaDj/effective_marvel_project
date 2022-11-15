package com.example.marvelproject.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun InfoHero(navController: NavHostController, indexHero: Int?) {
    /*
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
           /*
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
            )*/
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

            /*
            Text(
                text = hero.info,
                color = Color.White,
                fontSize = sizeInfo.sp,
                overflow = TextOverflow.Clip,
                softWrap = true
            )*/
        }
    }*/
}
