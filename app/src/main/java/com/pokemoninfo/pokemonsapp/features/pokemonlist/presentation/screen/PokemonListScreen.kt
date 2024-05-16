package com.pokemoninfo.pokemonsapp.features.pokemonlist.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.pokemoninfo.pokemonsapp.R
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models.PokemonListResult
import com.pokemoninfo.pokemonsapp.features.pokemonlist.presentation.viewmodel.PokemonsListViewModel
import com.pokemoninfo.pokemonsapp.uiutils.errorMessageBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    data: LazyPagingItems<PokemonListResult>,
    pokemonsListViewModel: PokemonsListViewModel,
    onClickNav: (String) -> Unit
) {
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
        }) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (data.loadState.refresh) {
                is LoadState.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is LoadState.Error -> {
                    errorMessageBox(data.retry())
                }

                is LoadState.NotLoading -> {
                    LazyColumn {
                        items(data) { pokemon ->
                            if (pokemon != null) {
                                PokemonCard(pokemon, pokemonsListViewModel, onClickNav)
                            }
                        }
                        when (data.loadState.append) {
                            is LoadState.Error -> {
                                item {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    )
                                    {
                                        Text(
                                            text = stringResource(R.string.new_page_not_load),
                                            color = MaterialTheme.colorScheme.error
                                        )
                                        IconButton(
                                            onClick = { data.retry() }
                                        ) {
                                            Icon(
                                                Icons.Filled.Refresh,
                                                contentDescription = null
                                            )
                                        }
                                    }
                                }
                            }
                            LoadState.Loading -> {
                                item {
                                    CircularProgressIndicator()
                                }
                            }
                            is LoadState.NotLoading -> {}
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonCard(
    pokemon: PokemonListResult,
    pokemonsListViewModel: PokemonsListViewModel,
    onClickNav: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClickNav(pokemon.name) },
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    )
    {
        Box(modifier = Modifier.fillMaxSize())
        {
            Row(verticalAlignment = Alignment.CenterVertically) {
//                AsyncImage(
//                    model = urlImage,
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(150.dp)
//                        .padding(8.dp),
//                )
                Column {
                    Text(
                        stringResource(R.string.name),
                        fontSize = 20.sp,
                        style = TextStyle(textDecoration = TextDecoration.Underline)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        pokemon.name,
                        fontSize = 30.sp,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}
