package com.pokemoninfo.pokemonsapp.features.pokemondetails.data.models

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork,
)