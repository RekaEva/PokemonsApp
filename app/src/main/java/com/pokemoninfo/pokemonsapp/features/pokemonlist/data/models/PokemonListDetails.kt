package com.pokemoninfo.pokemonsapp.features.pokemonlist.data.models

data class PokemonListDetails(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
)