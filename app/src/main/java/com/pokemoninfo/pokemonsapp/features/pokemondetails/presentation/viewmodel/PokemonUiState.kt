package com.pokemoninfo.pokemonsapp.features.pokemondetails.presentation.viewmodel

import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models.PokemonDetails

data class PokemonUiState(
    val pokemonDetails: PokemonDetails? = null,
    val error: Exception? = null,
    val isLoading: Boolean = false
)