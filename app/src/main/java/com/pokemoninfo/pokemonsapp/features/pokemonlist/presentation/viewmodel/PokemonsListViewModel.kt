package com.pokemoninfo.pokemonsapp.features.pokemonlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.usecase.GetPokemonList
import kotlinx.coroutines.delay
import javax.inject.Inject


class PokemonsListViewModel @Inject constructor(
    private val pokemonList: GetPokemonList
) : ViewModel() {


    val state = pokemonList
        .getPokemonList()
        .cachedIn(viewModelScope)

    suspend fun returnListImgLink(url: String): String {
        val id = url.split("/").last { it.isNotEmpty() }
        delay(1000)
        return "https://raw.githubusercontent.com/PokeAPI" +
                "/sprites/master/sprites/pokemon/other/home/${id}.png"
    }



}