package com.pokemoninfo.pokemonsapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models.PokemonListResult

class PokemonPagingSource(
    private val loader: PokemonApi
) : PagingSource<Int, PokemonListResult>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListResult> {
        val page = params.key ?: 0
        val offset = params.loadSize * page
        return try {
            val response = loader.getPokemonList(limit = params.loadSize, offset = offset)
            val pokelist = response.pokelist
            val nextKey = if (pokelist.size < params.loadSize) null else page + 1
            LoadResult.Page(
                data = pokelist,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonListResult>): Int? {
        return state.anchorPosition
    }

}