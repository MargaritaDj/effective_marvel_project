package com.example.marvelproject.di

import android.content.Context
import com.example.marvelproject.repositories.HeroRepositoryLocal
import com.example.marvelproject.database.HeroRoomDatabase
import com.example.marvelproject.network.MarvelApiService
import com.example.marvelproject.repositories.HeroRepositoryRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRepositoryLocal(@ApplicationContext context: Context): HeroRepositoryLocal {
        val database by lazy { HeroRoomDatabase.getDatabase(context)}
        val repositoryLocal by lazy { HeroRepositoryLocal(database.heroDao()) }

        return repositoryLocal
    }

    @Provides
    @Singleton
    fun provideRepositoryRemote(marvelApiService: MarvelApiService): HeroRepositoryRemote {
        val repositoryRemote by lazy { HeroRepositoryRemote(marvelApiService) }
        return repositoryRemote
    }
}
