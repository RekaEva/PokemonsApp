package com.pokemoninfo.pokemonsapp.domain

import androidx.paging.PagingData
import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models.Pokemon
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models.PokemonForList
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<PokemonForList>>
    suspend fun getPokemonDetails(name: String): Pokemon
}
