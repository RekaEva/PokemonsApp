package com.pokemoninfo.pokemonsapp.features.pokemondetails.data.models

import com.google.gson.annotations.SerializedName
import com.pokemoninfo.pokemonsapp.features.pokemondetails.data.models.OfficialArtwork

data class Other(
    @SerializedName("official-artwork") val officialartwork: OfficialArtwork,
)