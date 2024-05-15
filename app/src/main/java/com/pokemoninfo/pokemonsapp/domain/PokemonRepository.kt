package com.pokemoninfo.pokemonsapp.domain

import androidx.paging.PagingData
import com.pokemoninfo.pokemonsapp.features.pokemondetails.data.models.PokemonDetails
import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models.Pokemon
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models.PokemonListResult
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<PokemonListResult>>
    suspend fun getPokemonDetails(name: String): Pokemon
}
