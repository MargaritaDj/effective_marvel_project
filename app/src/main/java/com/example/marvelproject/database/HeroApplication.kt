package com.example.marvelproject.database

import android.app.Application

class HeroApplication: Application() {
    val database by lazy {HeroRoomDatabase.getDatabase(this)}
    val repository by lazy {HeroRepository(database.heroDao())}
}
