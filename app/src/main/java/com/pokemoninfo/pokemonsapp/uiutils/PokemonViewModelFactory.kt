package com.pokemoninfo.pokemonsapp.uiutils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.usecase.GetPokemonDetails
import com.pokemoninfo.pokemonsapp.features.pokemondetails.presentation.viewmodel.PokemonDetailsViewModel
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.usecase.GetPokemonList
import com.pokemoninfo.pokemonsapp.features.pokemonlist.presentation.viewmodel.PokemonsListViewModel
import javax.inject.Inject

class PokemonViewModelFactory @Inject constructor(
    private val listUseCase: GetPokemonList,
    private val detailsUseCase: GetPokemonDetails
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass == PokemonsListViewModel::class.java) {
            return PokemonsListViewModel(listUseCase) as T
        } else if (modelClass == PokemonDetailsViewModel::class.java) {
            return PokemonDetailsViewModel(detailsUseCase) as T
        }
        throw RuntimeException(" Unknown view model $modelClass")
    }
}