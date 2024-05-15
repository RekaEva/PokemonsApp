package com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("official-artwork") val officialartwork: OfficialArtwork,
)