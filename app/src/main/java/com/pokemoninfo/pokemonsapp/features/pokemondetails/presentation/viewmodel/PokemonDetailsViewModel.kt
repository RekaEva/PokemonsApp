package com.pokemoninfo.pokemonsapp.features.pokemondetails.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.usecase.GetPokemonDetails
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor(
    private val pokemonDetails: GetPokemonDetails
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonUiState())
    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()

    fun getPokemonDetails(name: String) {
        viewModelScope.launch {
            try {
                _uiState.emit(_uiState.value.copy(isLoading = true))
                val data = pokemonDetails.getPokemonDetails(name)
                _uiState.update { currentState ->
                    currentState.copy(
                        pokemonDetails = data,
                        isLoading = false
                    )
                }
            } catch (errorMessage: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        error = errorMessage,
                        isLoading = false
                    )
                }
            }
        }
    }
}