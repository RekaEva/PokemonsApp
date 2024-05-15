package com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models

data class Pokemon (
    val id: Int,
    val name: String,
    val height: String,
    val weight: String,
    val imageUrl: String,
    val types: List<String>,
    val abilities: List<String>,
)