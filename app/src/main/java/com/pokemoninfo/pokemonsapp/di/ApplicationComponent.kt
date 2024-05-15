package com.pokemoninfo.pokemonsapp.di

import com.pokemoninfo.pokemonsapp.MainActivity
import com.pokemoninfo.pokemonsapp.data.network.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}