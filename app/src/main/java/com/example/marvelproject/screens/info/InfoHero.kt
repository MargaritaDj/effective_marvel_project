package com.example.marvelproject.screens.info

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.asLiveData
import coil.compose.AsyncImage
import com.example.marvelproject.R
import com.example.marvelproject.navigation.Routes
import com.example.marvelproject.model.Hero
import com.example.marvelproject.overview.GetHeroByIdEvent
import com.example.marvelproject.overview.MainViewModel
import com.example.marvelproject.ui.theme.DarkGrey

@Composable
fun InfoHero(navController: NavHostController, id: String?) {

    val hero: MutableState<Hero?> = rememberSaveable {
        mutableStateOf(null)
    }

    val mainModel: MainViewModel = hiltViewModel()
    mainModel.send(GetHeroByIdEvent(id ?: ""))

    mainModel.stateHeroById.asLiveData().observeForever { state ->
        hero.value = state.heroById
    }

    if (hero.value != null) {
        if (LocalConfiguration.current.orientation != Configuration.ORIENTATION_LANDSCAPE) {
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
                        model = hero.value?.pathImage,
                        contentDescription = hero.value?.name,
                       // placeholder = painterResource(R.drawable.logo_placeholder),
                       // error = painterResource(R.drawable.hero),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(
                            LocalConfiguration.current.screenWidthDp.dp,
                            LocalConfiguration.current.screenHeightDp.dp
                        )
                    )
                }

                PrintDescriptionAboutHero(navController, hero.value, id)
            }
        } else {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = DarkGrey)
            ) {
                Box{
                    AsyncImage(
                        model = hero.value?.pathImage,
                        contentDescription = hero.value?.name,
                       // placeholder = painterResource(R.drawable.logo_placeholder),
                       // error = painterResource(R.drawable.hero),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(
                            LocalConfiguration.current.screenWidthDp.dp / 2,
                            LocalConfiguration.current.screenHeightDp.dp
                        )
                    )
                    Box(
                        modifier = Modifier
                            .size(100.dp, 100.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(Routes.ListHeroes.route + "/$id")
                            }
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
                PrintDescriptionAboutHeroLandscape(hero.value)
            }
        }
    }
}

@Composable
fun PrintDescriptionAboutHero(navController: NavHostController, hero: Hero?, id: String?) {

    val fontWeightTitle = 800
    val sizeTitle = 40
    val sizeInfo = 20

    val offsetXY = 2f
    val blurRadius = 10f

    Box(
        modifier = Modifier
            .size(100.dp, 100.dp),
        contentAlignment = Alignment.TopStart
    ) {
        IconButton(
            onClick = {
                navController.navigate(Routes.ListHeroes.route + "/$id")
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
                text = hero?.name ?: "",
                color = Color.White,
                fontSize = sizeTitle.sp,
                fontWeight = FontWeight(fontWeightTitle),
                style = TextStyle(
                    shadow = Shadow(
                        Color.Black,
                        Offset(offsetXY, offsetXY),
                        blurRadius
                    )
                )
            )

            Text(
                text = hero?.description ?: "",
                color = Color.White,
                fontSize = sizeInfo.sp,
                overflow = TextOverflow.Clip,
                softWrap = true,
                style = TextStyle(
                    shadow = Shadow(
                        Color.Black,
                        Offset(offsetXY, offsetXY),
                        blurRadius
                    )
                )
            )
        }
    }
}

@Composable
fun PrintDescriptionAboutHeroLandscape(hero: Hero?) {
    val fontWeightTitle = 800
    val sizeTitle = 40
    val sizeInfo = 20

    val offsetXY = 2f
    val blurRadius = 10f

    Box(
        modifier = Modifier
            .padding(start = 20.dp, bottom = 30.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = hero?.name ?: "",
                color = Color.White,
                fontSize = sizeTitle.sp,
                fontWeight = FontWeight(fontWeightTitle),
                style = TextStyle(
                    shadow = Shadow(
                        Color.Black,
                        Offset(offsetXY, offsetXY),
                        blurRadius
                    )
                )
            )

            Text(
                text = hero?.description ?: "",
                color = Color.White,
                fontSize = sizeInfo.sp,
                overflow = TextOverflow.Clip,
                softWrap = true,
                style = TextStyle(
                    shadow = Shadow(
                        Color.Black,
                        Offset(offsetXY, offsetXY),
                        blurRadius
                    )
                )
            )
        }
    }
}
