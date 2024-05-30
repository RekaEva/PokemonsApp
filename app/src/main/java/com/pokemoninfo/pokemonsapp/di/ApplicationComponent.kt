package com.pokemoninfo.pokemonsapp.di

import android.content.Context
import com.pokemoninfo.pokemonsapp.MainActivity
import com.pokemoninfo.pokemonsapp.data.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, DataModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}

