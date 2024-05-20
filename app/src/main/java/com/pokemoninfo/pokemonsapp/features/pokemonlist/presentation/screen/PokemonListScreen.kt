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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.pokemoninfo.pokemonsapp.R
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models.PokemonForList
import com.pokemoninfo.pokemonsapp.uiutils.ErrorMessageBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    data: LazyPagingItems<PokemonForList>,
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
                    ErrorMessageBox(data.retry())
                }

                is LoadState.NotLoading -> {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2)
                    ) {
                        items(data.itemCount) { index ->
                            data[index]?.let {
                                PokemonCard(pokemon = it, onClickNav)
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
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        CircularProgressIndicator()
                                    }
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
    pokemon: PokemonForList,
    onClickNav: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClickNav(pokemon.name) },
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    )
    {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .padding(8.dp),
            )
            Column(
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    pokemon.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Row {
                    pokemon.types.forEachIndexed { index, item ->
                        AsyncImage(model = item, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}
