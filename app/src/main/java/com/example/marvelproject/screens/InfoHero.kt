package com.example.marvelproject.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.marvelproject.overview.OverviewViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.marvelproject.R
import com.example.marvelproject.database.HeroApplication
import com.example.marvelproject.database.HeroRepository
import com.example.marvelproject.navigation.Routes
import com.example.marvelproject.model.Hero

@Composable
fun InfoHero(navController: NavHostController, id: String?) {

    val viewModel = OverviewViewModel()
    viewModel.getHeroById(id)
    val hero: MutableState<Hero?> = remember {
        mutableStateOf(null)
    }

    viewModel.heroResponse.observeForever {
        hero.value = viewModel.heroResponse.value?.data?.results?.map { it.toHero(null) }?.first()
    }

    if (hero.value != null) {
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
                    placeholder = painterResource(R.drawable.logo_placeholder),
                    error = painterResource(R.drawable.hero),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(
                        LocalConfiguration.current.screenWidthDp.dp,
                        LocalConfiguration.current.screenHeightDp.dp
                    )
                )
            }
            PrintDescriptionAboutHero(navController, hero.value, id)
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
                style = TextStyle(shadow = Shadow(Color.Black, Offset(offsetXY, offsetXY), blurRadius))
            )

            Text(
                text = hero?.description ?: "",
                color = Color.White,
                fontSize = sizeInfo.sp,
                overflow = TextOverflow.Clip,
                softWrap = true,
                style = TextStyle(shadow = Shadow(Color.Black, Offset(offsetXY, offsetXY), blurRadius))
            )
        }
    }
}
