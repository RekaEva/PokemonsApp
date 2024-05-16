package com.pokemoninfo.pokemonsapp.features.pokemondetails.data.mapper

import com.pokemoninfo.pokemonsapp.features.pokemondetails.data.models.PokemonDetails
import com.pokemoninfo.pokemonsapp.features.pokemondetails.domain.models.Pokemon
import com.pokemoninfo.pokemonsapp.uiutils.formatName
import javax.inject.Inject

class PokemonDataToDomainMapper @Inject constructor() {
    operator fun invoke(dto: PokemonDetails) = Pokemon(
        id = dto.id,
        name = dto.name.formatName(),
        height = dto.height.toString(),
        weight = dto.weight.toString(),
        imageUrl = dto.sprites.other.officialArtwork.frontDefault,
        abilities = dto.abilities.map { item->
            item.ability.name.formatName()
        },
        types = dto.types.map { item->
            item.type.name.uppercase()
        }
    )
}