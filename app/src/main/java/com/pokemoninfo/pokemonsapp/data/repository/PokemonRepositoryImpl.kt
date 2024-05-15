package com.pokemoninfo.pokemonsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pokemoninfo.pokemonsapp.data.network.PokemonApi
import com.pokemoninfo.pokemonsapp.data.network.PokemonPagingSource
import com.pokemoninfo.pokemonsapp.domain.PokemonRepository
import com.pokemoninfo.pokemonsapp.features.pokemondetails.data.mapper.PokemonDataToDomainMapper
import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models.Pokemon
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models.PokemonListResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
    private val mapper: PokemonDataToDomainMapper
) : PokemonRepository {

    override fun getPokemonList(): Flow<PagingData<PokemonListResult>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE),
            pagingSourceFactory = { PokemonPagingSource(loader = api) }
        ).flow
    }

    override suspend fun getPokemonDetails(name: String): Pokemon {
            val response = api.getPokemonDetailsByName(name)
            val pokemon = mapper.invoke(response)
            return pokemon


    }

    companion object {
        const val PAGE_SIZE = 20
    }

}