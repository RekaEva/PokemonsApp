package com.pokemoninfo.pokemonsapp.features.pokemonlist.data.mapper

import com.pokemoninfo.pokemonsapp.features.pokemonlist.data.models.PokemonListDetails
import com.pokemoninfo.pokemonsapp.features.pokemonlist.domain.models.PokemonForList
import com.pokemoninfo.pokemonsapp.uiutils.formatName
import javax.inject.Inject


class PokemonDataToDomainMapperForList @Inject constructor(){
    operator fun invoke(dto : PokemonListDetails) = PokemonForList(
        id = dto.id,
        name = dto.name.formatName(),
        imageUrl = dto.sprites.other.home.frontDefault,
        types = dto.types.map { item->
            item.type.name.uppercase()
        }
    )
}
