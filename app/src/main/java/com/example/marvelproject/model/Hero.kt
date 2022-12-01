package com.example.marvelproject.model

import android.os.Parcelable
import androidx.compose.ui.graphics.toArgb
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.marvelproject.ui.theme.DarkRed
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "heroes_table")
data class Hero(
    @PrimaryKey var id: String = "",
    var name : String = "",
    var description : String = "I am $name",
    var pathImage : String = "",
    var colorBackground : Int = DarkRed.toArgb()
): Parcelable
