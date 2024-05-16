package com.pokemoninfo.pokemonsapp.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pokemoninfo.pokemonsapp.features.pokemonlist.data.mapper.PokemonDataToDomainMapperForList
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models.PokemonForList


class PokemonPagingSource(
    private val loader: PokemonApi,
    private val mapper : PokemonDataToDomainMapperForList
) : PagingSource<Int, PokemonForList>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonForList> {
        val page = params.key ?: 0
        val offset = params.loadSize * page
        return try {
            val response = loader.getPokemonList(limit = params.loadSize, offset = offset)
            val pokelist = response.pokeList.map { item->
                val fullPokemon = loader.getPokemonListDetailsByName(item.name)
                mapper(fullPokemon)
            }
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

    override fun getRefreshKey(state: PagingState<Int, PokemonForList>): Int? {
        return state.anchorPosition
    }

}