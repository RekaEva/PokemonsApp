package com.pokemoninfo.pokemonsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.pokemoninfo.pokemonsapp.ScreensName.Companion.POKEMONS_LIST_SCREEN
import com.pokemoninfo.pokemonsapp.ScreensName.Companion.POKEMON_DETAILS_SCREEN
import com.pokemoninfo.pokemonsapp.di.App

import com.pokemoninfo.pokemonsapp.features.pokemondetails.presentation.screen.PokemonDetailsScreen
import com.pokemoninfo.pokemonsapp.features.pokemondetails.presentation.viewmodel.PokemonDetailsViewModel
import com.pokemoninfo.pokemonsapp.features.pokemonlist.presentation.screen.PokemonListScreen
import com.pokemoninfo.pokemonsapp.features.pokemonlist.presentation.viewmodel.PokemonsListViewModel
import com.pokemoninfo.pokemonsapp.uiutils.PokemonViewModelFactory
import com.pokemoninfo.pokemonsapp.uiutils.theme.PokemonsAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {


    @Inject
    lateinit var viewModelFactory: PokemonViewModelFactory

    private val pokemonsListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[PokemonsListViewModel::class.java]
    }
    private val pokemonDetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[PokemonDetailsViewModel::class.java]
    }
    private val component by lazy {
        (application as App).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            PokemonsAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = POKEMONS_LIST_SCREEN
                ) {
                    composable(POKEMONS_LIST_SCREEN) {
                        val data = pokemonsListViewModel.state.collectAsLazyPagingItems()
                        PokemonListScreen(
                            data = data,
                        ) {
                            navController.navigate("$POKEMON_DETAILS_SCREEN/$it")
                        }
                    }
                    composable("$POKEMON_DETAILS_SCREEN/{name}") { name ->
                        val pokemonName = name.arguments?.getString("name")
                        if (pokemonName != null) {
                            PokemonDetailsScreen(
                                pokemonDetailsViewModel = pokemonDetailsViewModel,
                                name = pokemonName.lowercase()
                            )
                        }
                    }
                }
            }
        }
    }
}

class ScreensName {
    companion object {
        const val POKEMONS_LIST_SCREEN = "pokemonsListScreen"
        const val POKEMON_DETAILS_SCREEN = "pokemonsDetailsScreen"
    }
}