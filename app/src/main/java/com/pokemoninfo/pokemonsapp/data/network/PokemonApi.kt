package com.pokemoninfo.pokemonsapp.data.network

import com.pokemoninfo.pokemonsapp.features.pokemondetails.data.models.PokemonDetails
import com.pokemoninfo.pokemonsapp.features.pokemonlist.data.models.PokemonListDetails
import com.pokemoninfo.pokemonsapp.features.pokemonlist.data.models.PokemonListX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonListX


    @GET("pokemon/{name}")
    suspend fun getPokemonDetailsByName(@Path("name") name: String): PokemonDetails

    @GET("pokemon/{name}")
    suspend fun getPokemonListDetailsByName(@Path("name") name: String): PokemonListDetails
}