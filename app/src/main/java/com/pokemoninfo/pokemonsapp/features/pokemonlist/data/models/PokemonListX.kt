package com.pokemoninfo.pokemonsapp.features.pokemonlist.data.models

import com.google.gson.annotations.SerializedName

data class PokemonListX(
    @SerializedName("results") val pokeList: List<PokemonListResult>
)

