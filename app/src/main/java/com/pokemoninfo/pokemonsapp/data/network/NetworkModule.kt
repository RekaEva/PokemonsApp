package com.pokemoninfo.pokemonsapp.data.network

import com.pokemoninfo.pokemonsapp.data.repository.PokemonRepositoryImpl
import com.pokemoninfo.pokemonsapp.domain.PokemonRepository
import com.pokemoninfo.pokemonsapp.features.pokemondetails.data.mapper.PokemonDataToDomainMapper
import com.pokemoninfo.pokemonsapp.features.pokemonlist.data.mapper.PokemonDataToDomainMapperForList
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideRepository(api: PokemonApi, mapper: PokemonDataToDomainMapper,
                          mapperForList: PokemonDataToDomainMapperForList): PokemonRepository {
        return PokemonRepositoryImpl(api, mapper, mapperForList)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(clientOkhttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(clientOkhttp)
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}

