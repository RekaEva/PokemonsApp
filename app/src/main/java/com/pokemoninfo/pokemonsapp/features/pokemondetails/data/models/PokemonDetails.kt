package com.pokemoninfo.pokemonsapp.features.pokemondetails.data.models

data class PokemonDetails(
    val abilities: List<Ability>,
    val height: Int,
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val weight: Int,
    val types: List<Type>,
)