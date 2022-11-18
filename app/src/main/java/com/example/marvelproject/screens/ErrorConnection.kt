package com.example.marvelproject.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelproject.orientation.ParamsOrientation

@Composable
fun ErrorConnection() {
    Box(
        modifier = Modifier
            .padding(start = 15.dp, top = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        val font = 800
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Red, fontSize = 35.sp,
                    fontWeight = FontWeight(font)
                )
                ) {
                    append("Error: ")
                }
                withStyle(style = SpanStyle(color = Color.White, fontSize = 35.sp,
                    fontWeight = FontWeight(font)
                )
                ) {
                    append("Failed to fetch data. Check internet connection.")
                }
            }
        )
    }
}
