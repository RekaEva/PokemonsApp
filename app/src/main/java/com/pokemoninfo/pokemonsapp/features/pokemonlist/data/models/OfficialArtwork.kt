package com.pokemoninfo.pokemonsapp.features.pokemonlist.data.models

import com.google.gson.annotations.SerializedName

data class OfficialArtwork(
    @SerializedName("front_default") val imageUrl: String,
)