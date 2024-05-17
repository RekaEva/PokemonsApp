package com.pokemoninfo.pokemonsapp.uiutils

import com.pokemoninfo.pokemonsapp.R

enum class Types {
    BUG_TYPE,
    DARK_TYPE,
    DRAGON_TYPE,
    ELECTRIC_TYPE,
    FAIRY_TYPE,
    FIGHTING_TYPE,
    FIRE_TYPE,
    FLYING_TYPE,
    GHOST_TYPE,
    GRASS_TYPE,
    GROUND_TYPE,
    ICE_TYPE,
    NORMAL_TYPE,
    POISON_TYPE,
    PSYCHIC_TYPE,
    ROCK_TYPE,
    STEEL_TYPE,
    WATER_TYPE,
    UNKNOWN_TYPE;

    fun getImageResource(): Int {
        return when (this) {
            BUG_TYPE -> R.drawable.bug_type
            DARK_TYPE -> R.drawable.dark_type
            DRAGON_TYPE -> R.drawable.dragon_type
            ELECTRIC_TYPE -> R.drawable.electric_type
            FAIRY_TYPE -> R.drawable.fairy_type
            FIGHTING_TYPE -> R.drawable.fighting_type
            FIRE_TYPE -> R.drawable.fire_type
            FLYING_TYPE -> R.drawable.flying_type
            GHOST_TYPE -> R.drawable.ghost_type
            GRASS_TYPE -> R.drawable.grass_type
            GROUND_TYPE -> R.drawable.ground_type
            ICE_TYPE -> R.drawable.ice_type
            NORMAL_TYPE -> R.drawable.normal_type
            POISON_TYPE -> R.drawable.poison_type
            PSYCHIC_TYPE -> R.drawable.psychic_type
            ROCK_TYPE -> R.drawable.rock_type
            STEEL_TYPE -> R.drawable.steel_type
            WATER_TYPE -> R.drawable.water_type
            UNKNOWN_TYPE -> R.drawable.unknown_type
        }

    }
}