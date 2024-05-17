package com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.usecase

import com.pokemoninfo.pokemonsapp.domain.PokemonRepository
import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models.Pokemon
import javax.inject.Inject

class GetPokemonDetails @Inject constructor(private val repository: PokemonRepository) {
    suspend fun getPokemonDetails(name: String): Pokemon {
        return repository.getPokemonDetails(name)
    }
}

