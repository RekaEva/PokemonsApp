package com.pokemoninfo.pokemonsapp.di

import android.app.Application

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(this)
    }
}