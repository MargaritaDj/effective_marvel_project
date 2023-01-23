package com.example.marvelproject.screens.heroes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.ItemSnapshotList
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.marvelproject.model.Hero
import com.example.marvelproject.orientation.ParamsOrientation
import com.example.marvelproject.screens.state.ErrorItem
import com.example.marvelproject.screens.state.LoadingItem
import com.example.marvelproject.screens.state.LoadingView
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo

@OptIn(ExperimentalSnapperApi::class, ExperimentalFoundationApi::class)
@Composable
fun LazyRowCards(navController: NavHostController, lazyListState: LazyListState,
                 layoutInfo: LazyListSnapperLayoutInfo, colorBackgroundHeroInt: MutableState<Int>,
    listHeroes: List<Hero>
                 //listHeroes: MutableState<List<Hero>>
                 // listHeroes: MutableState<ItemSnapshotList<Hero>>
){
    val shapeTriangleBackground = triangleBackground()
    val snappingLayout = remember(lazyListState) { SnapLayoutInfoProvider(lazyListState) }

    val paramsOrientation: ParamsOrientation = checkOrientation()
    val paddingCenterCard =
        (LocalConfiguration.current.screenWidthDp - paramsOrientation.widthCardHero) / 2

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
            .background(
                shape = shapeTriangleBackground,
                color = Color(colorBackgroundHeroInt.value)
            ),
        state = lazyListState,
        flingBehavior = rememberSnapFlingBehavior(snappingLayout),
        contentPadding = PaddingValues(horizontal = (paddingCenterCard.dp))
    )
    {
        /*itemsIndexed(
            listHeroes.value
        ) { index, item ->
            CardHero(
                index, item, paramsOrientation, lazyListState, navController,
                layoutInfo, paddingCenterCard
            )
        }*/

        itemsIndexed(
            listHeroes
        ) { index, item ->
            CardHero(
                index, item, paramsOrientation, lazyListState, navController,
                layoutInfo, paddingCenterCard
            )
        }

        /*listHeroes.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = listHeroes.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = listHeroes.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }*/

        /*itemsIndexed(
            //listHeroes.value
            listHeroes
        ) { index, item ->
            CardHero(
                index, item ?: Hero(), paramsOrientation, lazyListState, navController,
                layoutInfo, paddingCenterCard
            )
        }*/
    }
}
