package com.example.marvelproject.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelproject.model.Hero
import javax.inject.Inject

class HeroPagingSource(
    private val repositoryLocal: HeroRepositoryLocal,
    private val repositoryRemote: HeroRepositoryRemote
): PagingSource<Int, Hero>(){
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        println("PAGE LOAD")
        return try {
            val nextPage = params.key ?: 0
            println("NEXT PAGE: $nextPage")

            val result = repositoryRemote.refreshHeroes(nextPage)
            val listResponse = result.data?.data?.results?.map { it.toHero() } ?: listOf()
            println("RESULT: $listResponse")

            for (hero in listResponse) {
                repositoryLocal.insertHero(hero)
            }
            println("ADDED DB $listResponse")

            LoadResult.Page(
                data = result.data?.data?.results?.map { it.toHero() } ?: listOf(),
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = result.data?.data?.offset?.toInt()?.div(10)?.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}