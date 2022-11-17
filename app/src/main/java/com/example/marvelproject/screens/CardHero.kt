package com.example.marvelproject.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.marvelproject.R
import com.example.marvelproject.navigation.Routes
import com.example.marvelproject.network.model.Hero
import com.example.marvelproject.orientation.ParamsOrientation
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import kotlin.math.absoluteValue

@ExperimentalSnapperApi
@Composable
fun CardHero(
    index: Int, item: Hero, paramsOrientation: ParamsOrientation, lazyListState: LazyListState,
    navController: NavHostController, layoutInfo: LazyListSnapperLayoutInfo, paddingCenterCard: Int
) {

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .size(paramsOrientation.widthCardHero.dp, paramsOrientation.heightCardHero.dp)
            .graphicsLayer {
                val value =
                    1 - lazyListState.layoutInfo.normalizedItemPosition(
                        index,
                        paddingCenterCard
                    ).absoluteValue *
                            paramsOrientation.scaleCardHero

                scaleX = value
                scaleY = value
            }
            .clickable {
                if (index == layoutInfo.currentItem?.index) {
                    navController.navigate(Routes.InfoCurrentHero.route + "/${item.id}")
                }
            }
    )
    {
        val offsetXY = 2f
        val blurRadius = 10f

        AsyncImage(
            model = item.pathImage,
            contentDescription = item.name,
            placeholder = painterResource(R.drawable.logo_placeholder),
            error = painterResource(R.drawable.hero),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier.padding(start = 10.dp, bottom = 20.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = item.name,
                color = Color.White,
                fontSize = paramsOrientation.fontSizeAboutHero.sp,
                fontWeight = FontWeight(paramsOrientation.fontWeightTitle),
                style = TextStyle(shadow = Shadow(Color.Black,
                    Offset(offsetXY, offsetXY), blurRadius))
            )
        }
    }
}
