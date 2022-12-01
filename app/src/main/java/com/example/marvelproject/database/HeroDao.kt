package com.example.marvelproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelproject.model.Hero
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {

    @Insert(entity = Hero::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHero(hero: Hero)

    @Query("SELECT * FROM heroes_table")
    fun getAllHeroes(): Flow<List<Hero>>

    @Query("SELECT * FROM heroes_table WHERE id=:id ")
    suspend fun getHeroById(id: String): Hero

    @Query("DELETE FROM heroes_table")
    suspend fun deleteAll()

}
