package com.example.marvelproject.database

import androidx.annotation.WorkerThread
import com.example.marvelproject.model.Hero
import kotlinx.coroutines.flow.Flow

class HeroRepository(private val heroDao: HeroDao) {
    val allHeroes: Flow<List<Hero>> = heroDao.getAllHeroes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertHero(hero: Hero) {
        heroDao.insertHero(hero)
    }

    suspend fun deleteAll(){
        heroDao.deleteAll()
    }
}
