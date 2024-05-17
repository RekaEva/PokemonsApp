package com.pokemoninfo.pokemonsapp.features.pokemonlist.data.models

import com.google.gson.annotations.SerializedName

data class Other(
    val home: Home,
    @SerializedName("official-artwork") val officialArtworks: OfficialArtwork,
)