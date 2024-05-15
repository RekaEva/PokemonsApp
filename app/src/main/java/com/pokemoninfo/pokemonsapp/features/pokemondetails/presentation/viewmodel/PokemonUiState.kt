package com.pokemoninfo.pokemonsapp.features.pokemondetails.presentation.viewmodel

import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models.Pokemon

data class PokemonUiState(
    val pokemonDetails: Pokemon? = null,
    val error: Exception? = null,
    val isLoading: Boolean = false
)