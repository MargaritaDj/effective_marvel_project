package com.example.marvelproject.repositories

import com.example.marvelproject.database.HeroDao
import com.example.marvelproject.model.Hero
import kotlinx.coroutines.flow.Flow

class HeroRepositoryLocal(private val heroDao: HeroDao) {
    val allHeroes: Flow<List<Hero>> = heroDao.getAllHeroes()

    suspend fun getHeroById(id: String): Hero{
        return heroDao.getHeroById(id)
    }

    suspend fun insertHero(hero: Hero) {
        heroDao.insertHero(hero)
    }

    suspend fun deleteAll() {
        heroDao.deleteAll()
    }
}
