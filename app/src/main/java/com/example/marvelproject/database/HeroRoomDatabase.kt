package com.example.marvelproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marvelproject.model.Hero

@Database(entities = [Hero::class], version = 1, exportSchema = false)
public abstract class HeroRoomDatabase: RoomDatabase(){
    abstract fun heroDao(): HeroDao

    companion object {
        @Volatile
        private var INSTANCE: HeroRoomDatabase? = null

        fun getDatabase(context: Context): HeroRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroRoomDatabase::class.java,
                    "heroes_table"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
