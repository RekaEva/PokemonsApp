package com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.usecase

import androidx.paging.PagingData
import com.pokemoninfo.pokemonsapp.domain.PokemonRepository
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models.PokemonForList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    private val repository: PokemonRepository
) {
    fun getPokemonList(): Flow<PagingData<PokemonForList>> {
        return repository.getPokemonList()
    }

}