package com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models

data class PokemonForList(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<Int>,
)

