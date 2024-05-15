package com.pokemoninfo.pokemonsapp.features.pokemondetails.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pokemoninfo.pokemonsapp.R
import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models.PokemonDetails
import com.pokemoninfo.pokemonsapp.features.pokemondetails.presentation.viewmodel.PokemonDetailsViewModel
import com.pokemoninfo.pokemonsapp.uiutils.errorMessageBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailsScreen(
    pokemonDetailsViewModel: PokemonDetailsViewModel,
    name: String?,
) {
    val uiState by pokemonDetailsViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if (name != null) {
            pokemonDetailsViewModel.getPokemonDetails(name)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(R.string.list_title))
                }
            }
            )
        }
    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(50.dp),
                    )
                }
            } else if (uiState.pokemonDetails != null) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    PokemonDataCard(uiState.pokemonDetails)
                }
            } else uiState.error?.let {
                if (name != null) {
                    errorMessageBox(pokemonDetailsViewModel.getPokemonDetails(name))
                }
            }
        }

    }
}


@Composable
fun PokemonDataCard(pokemon: PokemonDetails?) {

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        )
    ) {
        Column {
            if (pokemon != null) {
                AsyncImage(
                    model = pokemon.sprites.other.officialartwork.front_default,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = pokemon.name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 15.dp)
                )
                Text(
                    text = stringResource(id = R.string.height)
                            + pokemon.height.toString(),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = stringResource(id = R.string.weight)
                            + pokemon.weight.toString(),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(5.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    stringResource(id = R.string.ability),
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.Underline
                )
                pokemon.abilities.forEachIndexed { index, item ->
                    Text(text = "${index + 1}) ${item.ability.name}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
