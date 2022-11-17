package com.example.marvelproject.screens

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.LazyListLayoutInfo
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.marvelproject.R
import com.example.marvelproject.network.model.Hero
import com.example.marvelproject.orientation.ParamsOrientation
import com.example.marvelproject.orientation.ParamsOrientationLandscape
import com.example.marvelproject.orientation.ParamsOrientationPortrait
import com.example.marvelproject.ui.theme.DarkGrey
import com.example.marvelproject.ui.theme.DarkRed
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo

@Composable
fun ListAllHeroes(
    listHeroes: List<Hero>,
    navController: NavHostController,
    id: String?,
    isError: Boolean
) {
    if (listHeroes.isNotEmpty() || isError) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = DarkGrey)
        ) {
            Logo(R.drawable.marvel)
            Title("Choose your hero")
            ListHeroes(listHeroes, navController, id, isError)
        }
    }
}

@Composable
fun Logo(logoID: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = logoID),
            contentDescription = "logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(160.dp, 40.dp)
        )
    }
}

@Composable
fun Title(title: String) {
    val paramsOrientation: ParamsOrientation = checkOrientation()
    if (!paramsOrientation.printTitle)
        return

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 35.sp,
            fontWeight = FontWeight(paramsOrientation.fontWeightTitle)
        )
    }
}

@OptIn(ExperimentalSnapperApi::class, ExperimentalFoundationApi::class)
@Composable
fun ListHeroes(listHeroesResponse: List<Hero>, navController: NavHostController, id: String?, isError: Boolean)
{
    val shapeTriangleBackground = triangleBackground()
    val lazyListState: LazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)
    val snappingLayout = remember(lazyListState) { SnapLayoutInfoProvider(lazyListState) }

    val paramsOrientation: ParamsOrientation = checkOrientation()
    val paddingCenterCard = (LocalConfiguration.current.screenWidthDp - paramsOrientation.widthCardHero) / 2

    val colorBackgroundHeroInt = rememberSaveable {
        mutableStateOf(DarkRed.toArgb())
    }

    val listHeroes = rememberSaveable {
        mutableStateOf(listHeroesResponse)
    }

    val currentHero = rememberSaveable {
        var currentIndexHero = 0
        for (hero in listHeroes.value) {
            if (hero.id == id) {
                currentIndexHero = listHeroes.value.indexOf(hero)
            }
        }
        mutableStateOf(currentIndexHero)
    }

    LaunchedEffect(Unit) {
        lazyListState.scrollToItem(currentHero.value)
    }

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (listHeroes.value.isNotEmpty() && !lazyListState.isScrollInProgress) {
            val currentIndex = layoutInfo.currentItem?.index ?: 0

            colorBackgroundHeroInt.value =
                listHeroes.value[currentIndex].colorBackground.toArgb()

            currentHero.value = currentIndex
        }
    }

    if (isError) {
        ErrorConnection(paramsOrientation)
    }

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .background(shape = shapeTriangleBackground, color = Color(colorBackgroundHeroInt.value)),
        state = lazyListState,
        flingBehavior = rememberSnapFlingBehavior(snappingLayout),
        contentPadding = PaddingValues(horizontal = (paddingCenterCard.dp))
    )
    {
        itemsIndexed(
            listHeroes.value
        ) { index, item ->
            CardHero(index, item, paramsOrientation, lazyListState, navController,
                layoutInfo, paddingCenterCard)
        }
    }
}

fun LazyListLayoutInfo.normalizedItemPosition(key: Any, paddingCenterCard: Int): Float =
    visibleItemsInfo
        .firstOrNull { it.index == key }
        ?.let {
            val center = (viewportEndOffset + viewportStartOffset - it.size) / 2F
            (it.offset.toFloat() - center) / (center - paddingCenterCard)
        }
        ?: 0F

fun triangleBackground(): GenericShape {
    val partHeight = 0.3f
    val shapeTriangleBackground = GenericShape { size: Size, _ ->
        val width = size.width
        val height = size.height

        moveTo(0f, height)
        lineTo(width, height)
        lineTo(width, height * partHeight)
        close()
    }
    return shapeTriangleBackground
}

@Composable
fun checkOrientation(): ParamsOrientation {
    return when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            ParamsOrientationLandscape()
        }
        else -> {
            ParamsOrientationPortrait()
        }
    }
}



