package com.pokemoninfo.pokemonsapp.di

import com.pokemoninfo.pokemonsapp.data.database.LocalSource
import com.pokemoninfo.pokemonsapp.data.database.LocalSourceImpl
import com.pokemoninfo.pokemonsapp.data.repository.PokemonRepositoryImpl
import com.pokemoninfo.pokemonsapp.domain.PokemonRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindLocalDataSource(impl: LocalSourceImpl): LocalSource

    @Binds
    fun bindRepositoryImpl(impl: PokemonRepositoryImpl): PokemonRepository
}

