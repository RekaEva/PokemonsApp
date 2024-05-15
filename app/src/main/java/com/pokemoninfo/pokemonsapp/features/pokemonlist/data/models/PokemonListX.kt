package com.pokemoninfo.pokemonsapp.features.pokemonlist.data.models

import com.google.gson.annotations.SerializedName

data class PokemonListX(
    val count: Int,
    val next: String,
    val previous: Any,
    @SerializedName("results") val pokelist: List<PokemonListResult>
)

